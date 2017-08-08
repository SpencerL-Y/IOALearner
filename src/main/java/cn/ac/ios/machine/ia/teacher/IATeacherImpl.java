package cn.ac.ios.machine.ia.teacher;

import cn.ac.ios.machine.ia.DIAImpl;
import cn.ac.ios.machine.ia.transducer.TransducerRunner;
import cn.ac.ios.machine.mealy.MealyMachine;
import cn.ac.ios.query.Query;
import cn.ac.ios.query.QuerySimple;
import cn.ac.ios.table.HashableValue;
import cn.ac.ios.table.HashableValueBoolean;
import cn.ac.ios.table.HashableValueInt;
import cn.ac.ios.words.APList;
import cn.ac.ios.words.Alphabet;
import cn.ac.ios.words.Word;

public class IATeacherImpl implements IATeacher {
	
	private TransducerRunner runner;
	public Alphabet mealyInAlpha;
	public Alphabet mealyOutAlpha;
	public IATeacherImpl(DIAImpl learningPurpose, DIAImpl learningTarget, Alphabet mmInput, Alphabet mmOutput){
		runner = new TransducerRunner(learningPurpose, learningTarget);
		mealyInAlpha = mmInput;
		mealyOutAlpha = mmOutput;
	}
	
	public Word parseString(String counterexample){
		String[] wordStr = counterexample.split("");
		int[] wordArr = new int[wordStr.length];
		
		for(int letterNr = 0; letterNr < wordStr.length; letterNr++){
			wordArr[letterNr] = Integer.parseInt(wordStr[letterNr]);
		}
		
		
		return this.mealyInAlpha.getArrayWord(wordArr);
		
	}
	
	@Override
	public Query<HashableValue> answerEquivalenceQuery(MealyMachine hypothesis) {
		String counterexample = this.runner.transducerEquiQuery(hypothesis);
		boolean isEq = false;
		Word wordCE = mealyInAlpha.getEmptyWord();
		if(counterexample == null){
			
			isEq = true;
		} else {
			System.out.println("counterexample: " + counterexample);
			wordCE = this.parseString(counterexample);
		}
		Query<HashableValue> ceQuery = new QuerySimple<>(wordCE);
		ceQuery.answerQuery(new HashableValueBoolean(isEq));
		return ceQuery;
	}

	
	
	
	@Override
	public HashableValue answerMembershipQuery(Query<HashableValue> query) {
		Word word = query.getQueriedWord();
		System.out.println("membership word: "+ word.toString());
		int outputLetter = this.runner.getOutputLetterFromWord(word);
		return new HashableValueInt(outputLetter);
	}


}
