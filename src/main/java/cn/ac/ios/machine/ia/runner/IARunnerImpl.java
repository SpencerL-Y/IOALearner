package cn.ac.ios.machine.ia.teacher;

import cn.ac.ios.machine.ia.DIAImpl;
import cn.ac.ios.machine.ia.InterfaceAutomaton;
import cn.ac.ios.machine.ia.util.SimulationChecker;
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
	public void step(int letter) {
		this.currentState = this.learningTarget
								.getState(this.currentState)
								.getSuccessor(letter);
	}

	@Override
	public String equivalenceQuery(InterfaceAutomaton hypothesis) {
		String[] CE = {""};
		if(SimulationChecker.alternatingSimCheckWithDelta(learningTarget, hypothesis, CE)){
			return null;
		} else {
			return CE[0];
		}
	}

	@Override
	public int getCurrentState() {
		return this.currentState;
	}

}
