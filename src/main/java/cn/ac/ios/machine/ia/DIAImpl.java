package cn.ac.ios.machine.ia;

import cn.ac.ios.words.APList;
import cn.ac.ios.words.Word;

public class DIAImpl extends IAImpl{

	public DIAImpl(APList iAps, APList oAps) {
		super(iAps, oAps);
	}
	
	@Override
	public Boolean isDeterministic() {
		return true;
	}
	
	@Override
	public State createState() {
		State state = makeState();
		states.add(state);
		return state;
	}
	
	@Override
	public int getSuccessor(int state, int letter){
		if(this.getState(state).isEnable(letter)){
			return this.getState(state).getSuccessors(letter).nextSetBit(0);
		} else {
			///System.out.println("letter not enable");
			return -1;
		}
	}
	private State makeState(){
		State newState = new DStateImpl(this, this.states.size());
		return newState;
	}

}
