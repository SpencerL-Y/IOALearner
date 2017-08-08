package cn.ac.ios.machine.ia.oracle;

import cn.ac.ios.machine.ia.teacher.IATeacher;
import cn.ac.ios.machine.mealy.MealyMachine;
import cn.ac.ios.oracle.EquivalenceOracle;

public class IAEquivalenceOracleImpl implements EquivalenceOracle<MealyMachine, Boolean> {

	private IATeacher teacher;
	
	public IAEquivalenceOracleImpl(IATeacher lehrer){
		this.teacher = lehrer;
	}
	@Override
	public Boolean answerEquivalenceQuery(MealyMachine automaton) {
		boolean isEq = this.teacher.answerEquivalenceQuery(automaton).getQueryAnswer().get();
		return isEq;
	}

}
