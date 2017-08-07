package cn.ac.ios.ia;

import cn.ac.ios.machine.ia.DIAImpl;
import cn.ac.ios.machine.ia.IAExporterDOT;
import cn.ac.ios.machine.ia.InterfaceAutomaton;
import cn.ac.ios.machine.ia.teacher.IATeacher;
import cn.ac.ios.machine.ia.teacher.IATeacherImpl;
import cn.ac.ios.machine.ia.util.AutoIAGenerator;

public class IATeacherTest {

	public static void main(String[] args) {
		
		
		
		
		for(int i = 0; i < 10; i++){
			DIAImpl machine = (DIAImpl) AutoIAGenerator.generate(5);
			IATeacher teacher = new IATeacherImpl(machine);
			teacher.step(0);
			teacher.step(1);
			System.out.println(teacher.getOutput());
			teacher.step(0);
			System.out.println(teacher.getOutput());
			System.out.println(teacher.getCurrentState());
			machine.addDelta();
			IAExporterDOT.export(machine);
			
			InterfaceAutomaton result = AutoIAGenerator.generate(5);
			String CE = teacher.equivalenceQuery(result);
			result.addDelta();
			IAExporterDOT.export(result);
			System.out.println(CE);
		}
	}

}
