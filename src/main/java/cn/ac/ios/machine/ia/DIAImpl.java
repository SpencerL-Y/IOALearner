package cn.ac.ios.machine.ia;

import cn.ac.ios.words.APList;

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
	
	private State makeState(){
		State newState = new DStateImpl(this, this.states.size());
		return newState;
	}

}
