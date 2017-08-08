package cn.ac.ios.ia;

import cn.ac.ios.machine.ia.IAExporterDOT;
import cn.ac.ios.machine.ia.InterfaceAutomaton;
import cn.ac.ios.machine.ia.util.AutoIAGenerator;
import cn.ac.ios.machine.ia.util.SimulationChecker;
import cn.ac.ios.words.Alphabet;

public class AutoIAGeneratorTest {
	public static void main(String[] args){
		//for(int i = 0; i < 10; i ++){
		//IAExporterDOT.export(AutoIAGenerator.generate(5));
		//}
		Alphabet input = new Alphabet(Integer.class);
		input.addLetter(0);
		input.addLetter(1);
		
		Alphabet output = new Alphabet(Integer.class);
        output.addLetter(0);
        output.addLetter(1);
        output.addLetter(2);
		
		for(int i = 0; i < 100; i++){
			String[] CE = {""};
			InterfaceAutomaton oIA = AutoIAGenerator.generate(input.getAPs(), output.getAPs(), 3);
			InterfaceAutomaton iIA = AutoIAGenerator.generate(input.getAPs(), output.getAPs(), 3);
			if(SimulationChecker.alternatingSimCheck(oIA,iIA, CE)){
				iIA.addDelta(); oIA.addDelta();
				IAExporterDOT.export(oIA);
				IAExporterDOT.export(iIA);
				System.out.println("Everything is okay");
			}
		}
	}
	
	
	
}
