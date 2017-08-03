package cn.ac.ios.machine.ia.transducer;

import cn.ac.ios.machine.ia.DIAImpl;
import cn.ac.ios.machine.ia.InterfaceAutomaton;
import cn.ac.ios.machine.ia.teacher.IATeacher;
import cn.ac.ios.machine.ia.teacher.IATeacherImpl;
import cn.ac.ios.machine.mealy.MealyMachine;
import cn.ac.ios.machine.mealy4ia.MMForIA;
import cn.ac.ios.words.APList;
import cn.ac.ios.words.Word;

public class Transducer {
	public InterfaceAutomaton  learningPurpose;
	public int currentState; 
	public IATeacher teacher;
	public APList iAp;
	public APList oAp;
	public Transducer (DIAImpl lp, DIAImpl tg){
		this.learningPurpose = lp;
		this.teacher = new IATeacherImpl(tg);
		this.currentState = lp.getInitial().getIndex();
		this.iAp = lp.getInAPs();
		this.oAp = lp.getOutAPs();
	}
	
	public void reset(){
		this.currentState = this.learningPurpose.getInitial().getIndex();
		this.resetTeacher();
	}
	
	public int transducerStep(int letter){
		if(letter <this.learningPurpose.getInApSize()){
			if(this.learningPurpose.getState(this.currentState).isEnable(letter)){
				this.currentState = this.learningPurpose
										.getState(this.currentState)
										.getSuccessor(letter);
				this.teacher.step(letter);
				return this.oAp.size()+2;//TODO: return the character +
			} else {
				return this.oAp.size()+1;//TODO: return the character -
			}
		} else {
			int outLetter = this.getOutputLetterFromTeacher();
			this.currentState = this.learningPurpose
									.getState(this.currentState)
									.getSuccessor(outLetter);
			return outLetter;
		}
	}
	
	public int getOutputLetterFromTeacher(){
		return this.teacher.getOutput() - this.iAp.size();
	}
	
	public void resetTeacher(){
		this.teacher.reset();
	}
	
	public int getOutputLetterFromWord(Word word){
		for(int letterNr = 0; letterNr < word.length() - 1; letterNr++){
			this.transducerStep(word.getLetter(letterNr));
		}
		int returnLetter = this.transducerStep(word.getLetter(word.length()-1));
		this.reset();
		return returnLetter;
	}
	
	public String transducerEquiQuery(MMForIA hypothesis){
		//TODO: equivalence
		String counterExample = null;
		
		return counterExample;
		
	}
	
}
