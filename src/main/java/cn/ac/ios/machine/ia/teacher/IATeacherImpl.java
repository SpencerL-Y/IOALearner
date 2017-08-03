package cn.ac.ios.machine.ia.teacher;

import cn.ac.ios.machine.ia.DIAImpl;
import cn.ac.ios.machine.ia.InterfaceAutomaton;
import cn.ac.ios.words.APList;

public class IATeacherImpl implements IATeacher {
	
	public final InterfaceAutomaton learningTarget;
	public int currentState;
	public APList iAp;
	public APList oAp;
	
	
	public IATeacherImpl(DIAImpl target){
		this.learningTarget =  target;
		this.currentState = target.getInitial().getIndex();
		this.iAp = target.getInAPs();
		this.oAp = target.getOutAPs();
	}
	
	@Override
	public void reset() {
		this.currentState = this.learningTarget
								.getInitial()
								.getIndex();
	}

	@Override
	public int getOutput() {
		assert this.learningTarget
				   .getState(currentState)
				   .isOutputDetermined();
		int result = this.learningTarget
						 .getOutputLetter(currentState);
		this.step(result);
		return result;
	}

	@Override
	public int step(int letter) {
		this.currentState = this.learningTarget
								.getState(this.currentState)
								.getSuccessor(letter);
		return 0;
	}

	@Override
	public String equivalenceQuery(InterfaceAutomaton hypothesis) {
		// TODO Auto-generated method stub
		return null;
	}

}
