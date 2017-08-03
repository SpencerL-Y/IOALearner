package cn.ac.ios.ia;

import cn.ac.ios.machine.ia.IAExporterDOT;
import cn.ac.ios.machine.ia.InterfaceAutomaton;
import cn.ac.ios.machine.ia.util.AutoIAGenerator;
import cn.ac.ios.machine.ia.util.SimulationChecker;

public class AutoIAGeneratorTest {
	public static void main(String[] args){
		//for(int i = 0; i < 10; i ++){
		//IAExporterDOT.export(AutoIAGenerator.generate(5));
		//}
		
		
		for(int i = 0; i < 100; i++){
			String CE = "";
			InterfaceAutomaton oIA = AutoIAGenerator.generate(3);
			InterfaceAutomaton iIA = AutoIAGenerator.generate(3);
			if(SimulationChecker.alternatingSimCheck(oIA,iIA, CE)){
				IAExporterDOT.export(oIA);
				IAExporterDOT.export(iIA);
				System.out.println("Everything is okay");
			}
		}
	}
	
	
	
}
