package cn.ac.ios.machine.ia.teacher;

import cn.ac.ios.machine.mealy.MealyMachine;
import cn.ac.ios.query.Query;
import cn.ac.ios.table.HashableValue;

public interface IATeacher {
	
	public Query<HashableValue> answerEquivalenceQuery(MealyMachine machine);
	public HashableValue answerMembershipQuery(Query<HashableValue> query);
	
}
