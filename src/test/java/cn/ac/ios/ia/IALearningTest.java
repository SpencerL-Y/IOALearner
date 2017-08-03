package cn.ac.ios.ia;

import cn.ac.ios.learner.table.mealy.LearnerMealyTable;
import cn.ac.ios.machine.Machine;
import cn.ac.ios.machine.ia.DIAImpl;
import cn.ac.ios.machine.ia.InterfaceAutomaton;
import cn.ac.ios.machine.ia.transducer.TransducerTeacher;
import cn.ac.ios.machine.mealy4ia.MMForIA;
import cn.ac.ios.mealy.EquivalenceOracleImpl;
import cn.ac.ios.mealy.InputHelper;
import cn.ac.ios.mealy.MembershipOracleImpl;
import cn.ac.ios.oracle.EquivalenceOracle;
import cn.ac.ios.oracle.MembershipOracle;
import cn.ac.ios.query.Query;
import cn.ac.ios.query.QuerySimple;
import cn.ac.ios.table.HashableValue;
import cn.ac.ios.table.HashableValueInt;
import cn.ac.ios.words.Alphabet;
import cn.ac.ios.words.Word;

public class IALearningTest {

	public static void main(String[] args) {
		Alphabet input = new Alphabet(Integer.class);
		input.addLetter(0);
		input.addLetter(1);
		
		Alphabet output = new Alphabet(Integer.class);
        output.addLetter(0);
        output.addLetter(1);
        output.addLetter(2);
        //create your learning purpose and learning target here
        DIAImpl learningPurpose = new DIAImpl(input.getAPs(), output.getAPs());
        DIAImpl learningTarget = new DIAImpl(input.getAPs(),output.getAPs());
        learningPurpose.addDelta();
        
        assert learningPurpose.isDeltaAdded();
        assert learningPurpose.isDeterministic();
        assert learningTarget.isDeterministic();
        
        TransducerTeacher teacher = new TransducerTeacher(learningPurpose, learningTarget);
        input.addLetter(2);//This is big Delta
        output.addLetter(3);//this is small delta
        output.addLetter(4);//this is character -
        output.addLetter(5);//this is character +
        
		MembershipOracle<HashableValue> membershipOracle = new MembershipOracleImpl(output);

		LearnerMealyTable learner = new LearnerMealyTable(input, output, membershipOracle);
		System.out.println("starting learning");
		learner.startLearning();
		boolean result = false;
		while(true) {
			System.out.println("Table is both closed and consistent\n" + learner.toString());
			
			MMForIA model = (MMForIA) learner.getHypothesis();
//			System.out.println("automaton\n" + model.toString());
			
			Query<HashableValue> ceQuery = learner.makeTableConsistent();
			
			if(ceQuery != null) {
				learner.refineHypothesis(ceQuery);
				continue;
			}
			
			
			String possibleCE = teacher.transducerEquiQuery(model); 
			if(possibleCE == null){
				result = true;
			} else {
				result = false;
			}
			if(result == true) break;
			Word runWord = teacher.getCeWordStr(input, possibleCE);
			ceQuery = new QuerySimple<HashableValue>(runWord);
			int outputLetter = teacher.getOutputLetterFromWord(runWord);
			
			ceQuery.answerQuery(new HashableValueInt(outputLetter));
			learner.refineHypothesis(ceQuery);
		}
		
	}

}
