package cn.ac.ios.ia;

import cn.ac.ios.machine.ia.IAExporterDOT;
import cn.ac.ios.machine.ia.IAImpl;
import cn.ac.ios.machine.ia.InterfaceAutomaton;
import cn.ac.ios.machine.ia.util.StatePair;
import cn.ac.ios.machine.ia.util.UtilIA;
import cn.ac.ios.words.Alphabet;

public class AltSimTest {

	public static void main(String[] args) {
		Alphabet input = new Alphabet(Integer.class);
		Alphabet output = new Alphabet(Integer.class);
		input.addLetter(0);
		input.addLetter(1);
		
		output.addLetter(0);
		output.addLetter(1);
		output.addLetter(2);
		InterfaceAutomaton IA1 = new IAImpl(input.getAPs(), output.getAPs());
		for(int i = 0; i < 2; i++){
			IA1.createState();
		}
		IA1.setInitial(0);
		IA1.getInitial().addTransition(0, 1);
		IA1.getState(0).addTransition(input.getAPSize(), 0);
		IA1.getState(0).addTransition(1, 1);
		IA1.getState(1).addTransition(input.getAPSize() + 1, 0);
		
		
		IAExporterDOT.export(IA1);
		
		InterfaceAutomaton IA2 = new IAImpl(input.getAPs(), output.getAPs());
		for(int i = 0; i < 2; i++){
			IA2.createState();
		}
		IA2.setInitial(0);
		IA2.getInitial().addTransition(0, 1);
		IA2.getState(0).addTransition(input.getAPSize(), 0);
		IA2.getState(1).addTransition(input.getAPSize() + 1, 1);
		IA2.getState(1).addTransition(input.getAPSize() + 2, 1);
		IA2.getState(1).addTransition(input.getAPSize(), 0);
		
		IAExporterDOT.export(IA2);
		IA1.addDelta();
		IA2.addDelta();
		
		if(UtilIA.alternatingSimCheck(IA1, IA2)){
			System.out.println("No problem");
		}
		
		if(UtilIA.AISimCheck(IA1, IA2)){
			System.out.println("No problem as you see");
		}
		
	}

}
