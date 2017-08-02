package cn.ac.ios.machine.ia.transducer;

import cn.ac.ios.machine.ia.DIAImpl;
import cn.ac.ios.machine.ia.InterfaceAutomaton;
import cn.ac.ios.words.APList;
import cn.ac.ios.words.Word;

public class Transducer {
	public InterfaceAutomaton  learningPurpose;
	public int currentState; 
	public APList iAp;
	public APList oAp;
	public Transducer (DIAImpl lp){
		this.learningPurpose = lp;
		this.currentState = lp.getInitial().getIndex();
		this.iAp = lp.getInAPs();
		this.oAp = lp.getOutAPs();
	}
	
	public void reset(){
		this.currentState = this.learningPurpose.getInitial().getIndex();
		this.resetTeacher();
	}
	
	public int step(int letter){
		assert letter > 0;
		if(letter < this.learningPurpose.getInApSize()){
			if(this.learningPurpose.getState(this.currentState).isEnable(letter)){
				this.currentState = this.learningPurpose.getState(this.currentState).getSuccessor(letter);
				return this.oAp.size()+2;//TODO: return the character +
			} else {
				return this.oAp.size()+1;//TODO: return the character -
			}
		} else {
			int outLetter = this.getOutputFromTeacher();
			this.currentState = this.learningPurpose.getState(this.currentState).getSuccessor(outLetter);
			return outLetter;
		}
	}
	
	public int getOutputFromTeacher(){
		//TODO: add implementations
		return 0;
	}
	
	public void resetTeacher(){
		;
	}
	
	public int getOutputLetter(Word word){
		for(int letterNr = 0; letterNr < word.length() - 1; letterNr++){
			this.step(word.getLetter(letterNr));
		}
		int returnLetter = this.step(word.getLetter(word.length()-1));
		this.reset();
		return returnLetter;
	}
}
