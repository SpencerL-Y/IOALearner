package cn.ac.ios.ia;

import cn.ac.ios.learner.table.mealy.LearnerMealyTable;
import cn.ac.ios.machine.ia.DIAImpl;
import cn.ac.ios.machine.ia.oracle.IAEquivalenceOracleImpl;
import cn.ac.ios.machine.ia.oracle.IAMembershipOracleImpl;
import cn.ac.ios.machine.ia.teacher.IATeacher;
import cn.ac.ios.machine.ia.teacher.IATeacherImpl;
import cn.ac.ios.machine.ia.util.AutoIAGenerator;
import cn.ac.ios.machine.mealy.MealyMachine;
import cn.ac.ios.oracle.EquivalenceOracle;
import cn.ac.ios.oracle.MembershipOracle;
import cn.ac.ios.query.Query;
import cn.ac.ios.table.HashableValue;
import cn.ac.ios.words.Alphabet;

public class IALearningTest {
	public static void main(String[] args){
		//Alphabet initialization
		Alphabet input = new Alphabet(Integer.class);
		input.addLetter(0);
		input.addLetter(1);
		
		Alphabet output = new Alphabet(Integer.class);
        output.addLetter(0);
        output.addLetter(1);
        output.addLetter(2);
        
        Alphabet mealyIn = new Alphabet(Integer.class);
        mealyIn.addLetter(0);
		mealyIn.addLetter(1);
		mealyIn.addLetter(2);//Big Delta
		
		Alphabet mealyOut = new Alphabet(Integer.class);
		mealyOut.addLetter(0);
		mealyOut.addLetter(1);
		mealyOut.addLetter(2);
		mealyOut.addLetter(3);// delta
		mealyOut.addLetter(4);//-
		mealyOut.addLetter(5);//+
		
		//learning purpose definition
		DIAImpl lp = new DIAImpl(input.getAPs(), output.getAPs());
		lp.createState(); lp.setInitial(0);
		for(int letter = 0; letter < input.getAPSize() + output.getAPSize() + 1; letter++){
			lp.getState(0).addTransition(letter, 0);
		}
		
		//learning target generation
		DIAImpl tg = AutoIAGenerator.generate(input.getAPs(), output.getAPs(), 5);
		IATeacher teacher = new IATeacherImpl(lp, tg, mealyIn, mealyOut);
		
		MembershipOracle<HashableValue> membershipOracle = new IAMembershipOracleImpl(teacher);
		
		LearnerMealyTable learner = new LearnerMealyTable(mealyIn, mealyOut, membershipOracle);
		learner.startLearning();
		boolean result = false;
		while(true){
			MealyMachine resultMachine =  (MealyMachine) learner.getHypothesis();
			
			Query<HashableValue> ceQuery = learner.makeTableConsistent();
			if(ceQuery != null){
				learner.refineHypothesis(ceQuery);
				continue;
			}
			
			EquivalenceOracle<MealyMachine, Boolean> equivalenceOracle = new IAEquivalenceOracleImpl(teacher);
			result = equivalenceOracle.answerEquivalenceQuery(resultMachine);
			if(result) break;
			ceQuery = teacher.answerEquivalenceQuery(resultMachine);
			ceQuery.answerQuery(teacher.answerMembershipQuery(ceQuery));
			learner.refineHypothesis(ceQuery);
		}
		
		
		
		
	}
}
