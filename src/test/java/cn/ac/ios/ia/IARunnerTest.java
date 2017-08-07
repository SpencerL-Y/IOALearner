package cn.ac.ios.ia;

import cn.ac.ios.machine.ia.DIAImpl;
import cn.ac.ios.machine.ia.IAExporterDOT;
import cn.ac.ios.machine.ia.InterfaceAutomaton;
import cn.ac.ios.machine.ia.runner.IARunner;
import cn.ac.ios.machine.ia.runner.IARunnerImpl;
import cn.ac.ios.machine.ia.util.AutoIAGenerator;
import cn.ac.ios.words.Alphabet;

public class IARunnerTest {

	public static void main(String[] args) {
		
		Alphabet input = new Alphabet(Integer.class);
		input.addLetter(0);
		input.addLetter(1);
		
		Alphabet output = new Alphabet(Integer.class);
        output.addLetter(0);
        output.addLetter(1);
        output.addLetter(2);
		
		
		for(int i = 0; i < 10; i++){
			DIAImpl machine = (DIAImpl) AutoIAGenerator.generate(input.getAPs(), output.getAPs(), 5);
			IARunner teacher = new IARunnerImpl(machine);
			teacher.step(0);
			teacher.step(1);
			System.out.println(teacher.getOutput());
			teacher.step(0);
			System.out.println(teacher.getOutput());
			System.out.println(teacher.getCurrentState());
			machine.addDelta();
			IAExporterDOT.export(machine);
			
			InterfaceAutomaton result = AutoIAGenerator.generate(input.getAPs(), output.getAPs(), 5);
			String CE = teacher.equivalenceQuery(result);
			result.addDelta();
			IAExporterDOT.export(result);
			System.out.println(CE);
		}
	}

}
