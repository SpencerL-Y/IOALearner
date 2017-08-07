package cn.ac.ios.ia;

import cn.ac.ios.machine.ia.IAExporterDOT;
import cn.ac.ios.machine.ia.IAImpl;
import cn.ac.ios.machine.ia.InterfaceAutomaton;
import cn.ac.ios.machine.ia.util.SimulationChecker;
import cn.ac.ios.words.Alphabet;

public class IAutilTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Alphabet input = new Alphabet(Integer.class);
		Alphabet output = new Alphabet(Integer.class);
		input.addLetter(0);
		input.addLetter(1);
		
		output.addLetter(0);
		output.addLetter(1);
		output.addLetter(2);
		InterfaceAutomaton IA = new IAImpl(input.getAPs(), output.getAPs());
		for(int i = 0; i < 4; i++){
			IA.createState();
		}
		IA.setInitial(0);
		IA.getInitial().addTransition(0, 1);
		IA.getState(1).addTransition(input.getAPSize(), 2);
		IA.getState(1).addTransition(0, 3);
		IA.getState(2).addTransition(1, 2);
		IA.getState(3).addTransition(0, 3);
		IA.getState(2).addTransition(input.getAPSize()+1, 3);
		IA.addDelta();
		
		IAExporterDOT.export(IA);
		
		if(IA.getState(3).isQuiescent()){
			System.out.println("Is quiescent.");
		}
		String[] counterExample = {""};
		if(SimulationChecker.alternatingSimCheck(IA, IA, counterExample)){
			System.out.println("No Problem");
		}
		
	}

}
