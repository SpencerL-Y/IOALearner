package cn.ac.ios.machine.ia.teacher;

import cn.ac.ios.machine.mealy4ia.MMForIA;
import cn.ac.ios.query.Query;
import cn.ac.ios.table.HashableValue;

public interface IATeacher {
	
	public Query<HashableValue> answerEquivalenceQuery(MMForIA machine);
	public HashableValue answerMembershipQuery(Query<HashableValue> query);
	
}
