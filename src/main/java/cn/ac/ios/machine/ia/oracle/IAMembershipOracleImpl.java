package cn.ac.ios.machine.ia.oracle;

import cn.ac.ios.machine.ia.teacher.IATeacher;
import cn.ac.ios.oracle.MembershipOracle;
import cn.ac.ios.query.Query;
import cn.ac.ios.table.HashableValue;

public class IAMembershipOracleImpl implements MembershipOracle<HashableValue> {

	private IATeacher teacher;
	
	public IAMembershipOracleImpl(IATeacher lehrer){
		this.teacher = lehrer;
	}
	
	@Override
	public HashableValue answerMembershipQuery(Query<HashableValue> query) {
		
		return this.teacher.answerMembershipQuery(query);
	}

}
