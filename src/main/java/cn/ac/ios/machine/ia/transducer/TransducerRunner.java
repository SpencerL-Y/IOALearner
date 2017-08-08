package cn.ac.ios.machine.ia.transducer;

import cn.ac.ios.machine.ia.DIAImpl;
import cn.ac.ios.machine.ia.InterfaceAutomaton;
import cn.ac.ios.machine.ia.runner.IARunner;
import cn.ac.ios.machine.ia.runner.IARunnerImpl;
import cn.ac.ios.machine.ia.util.Converter;
import cn.ac.ios.machine.ia.util.SimulationChecker;
import cn.ac.ios.machine.mealy.MealyMachine;
import cn.ac.ios.words.APList;
import cn.ac.ios.words.Alphabet;
import cn.ac.ios.words.Word;

public class TransducerRunner {
	public InterfaceAutomaton  learningPurpose;
	public int currentState; 
	public IARunner runner;
	public APList iAp;
	public APList oAp;
	public TransducerRunner (DIAImpl lp, DIAImpl tg){
		this.learningPurpose = lp;
		this.runner = new IARunnerImpl(tg);
		this.currentState = lp.getInitial().getIndex();
		this.iAp = lp.getInAPs();
		this.oAp = lp.getOutAPs();
	}
	
	public void reset(){
		this.currentState = this.learningPurpose.getInitial().getIndex();
		this.resetTeacher();
	}
	
	private int transducerStep(int letter){
		if(letter <this.learningPurpose.getInApSize()){
			if(this.learningPurpose.getState(this.currentState).isEnable(letter)){
				this.currentState = this.learningPurpose
										.getState(this.currentState)
										.getSuccessor(letter);
				this.runner.step(letter);
				return this.oAp.size()+2;//TODO: return the character +
			} else {
				return this.oAp.size()+1;//TODO: return the character -
			}
		} else {
			int outLetter = this.getOutputLetterFromTeacher();
			System.out.println(outLetter);
			this.currentState = this.learningPurpose
									.getState(this.currentState)
									.getSuccessor(outLetter);
			return outLetter;
		}
	}
	
	private int getOutputLetterFromTeacher(){
		
		return this.runner.getOutput() - this.iAp.size();
	}
	
	private void resetTeacher(){
		this.runner.reset();
	}
	
	public int getOutputLetterFromWord(Word word){
		for(int letterNr = 0; letterNr < word.length() - 1; letterNr++){
			this.transducerStep(word.getLetter(letterNr));
		}
		int returnLetter = this.transducerStep(word.getLetter(word.length()-1));
		System.out.println("return letter on "+ word.getLetter(word.length()-1) + " is: " + returnLetter);
		this.reset();
		return returnLetter;
	}
	
	public String transducerEquiQuery(MealyMachine hypothesis){
		//TODO: equivalence
		String[] CE = {""};
		Boolean lpSat = SimulationChecker.AISimCheck(Converter.MMToIA(hypothesis), 
													 this.learningPurpose, CE);
		if(lpSat){
			String ltgCE = this.runner.equivalenceQuery(Converter.MMToIA(hypothesis));
			return ltgCE;
		} else {
			return null;
		}
	}
	
	
	public Word getCeWordStr(Alphabet inputs, String counterexample) {
		Word word = null;
		do {
			String input = counterexample;
			String[] wordStr = input.split("");
			int[] wordArr = new int[wordStr.length];
			for(int index = 0; index < wordStr.length; index ++) {
				int letter = inputs.getAPs().indexOf(wordStr[index]);
				wordArr[index] = letter;
			}
			word = inputs.getArrayWord(wordArr);
			if(word == null)	System.out.println("Illegal input, try again!");
		}while(word == null);
		
		return word;
	}
}
