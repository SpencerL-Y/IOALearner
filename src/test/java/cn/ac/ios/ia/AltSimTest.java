package cn.ac.ios.ia;


import cn.ac.ios.machine.ia.IAExporterDOT;
import cn.ac.ios.machine.ia.IAImpl;
import cn.ac.ios.machine.ia.InterfaceAutomaton;
import cn.ac.ios.machine.ia.util.StatePairImpl;
import cn.ac.ios.machine.ia.util.SimulationChecker;
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
		IA2.getInitial().addTransition(1, 1);
		IA2.getState(0).addTransition(input.getAPSize(), 0);
		//IA2.getState(1).addTransition(input.getAPSize() + 1, 1);
		IA2.getState(1).addTransition(input.getAPSize() + 2, 1);
		IA2.getState(1).addTransition(input.getAPSize(), 0);
		
		IAExporterDOT.export(IA2);
		IA1.addDelta();
		IA2.addDelta();
		
		StatePairImpl test = new StatePairImpl(IA1.getInitial(),IA2.getInitial());
		String[] counterExample = {""};
		if(SimulationChecker.alternatingSimCheck(IA1, IA2, counterExample)){
			System.out.println("Everything is okay");
		} else {
			System.out.println(counterExample[0]);
		}
		
		
		
		if(SimulationChecker.AISimCheck(IA1, IA2, counterExample)){
			System.out.println("Something is not okay");
		}
		InterfaceAutomaton IA3 = new IAImpl(input.getAPs(), output.getAPs());
		for(int i = 0; i < 3; i++){
			IA3.createState();
		}
		IA3.setInitial(0);
		IA3.getState(0).addTransition(0, 1);
		IA3.getState(0).addTransition(1, 2);
		//IA3.getState(1).addTransition(IA3.getInApSize()+1, 0);
		IA3.getState(1).addTransition(IA3.getInApSize(), 2);
		IA3.getState(1).addTransition(0, 2);
		InterfaceAutomaton IA4 = new IAImpl(input.getAPs(), output.getAPs());
		for(int i = 0; i < 3; i++){
			IA4.createState();
		}
		IA4.setInitial(0);
		IA4.getInitial().addTransition(0, 1);
		IA4.getState(1).addTransition(IA4.getInApSize(), 2);
		String[] CE = {""};
		
		IAExporterDOT.export(IA3);
		IAExporterDOT.export(IA4);
		
		if(SimulationChecker.alternatingSimCheck(IA3, IA4, CE)){
			System.out.println("Everything is okay");
		} else {
			System.out.println(CE[0]);
		}
		
	}

}
