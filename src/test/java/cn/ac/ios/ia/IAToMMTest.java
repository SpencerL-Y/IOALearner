package cn.ac.ios.ia;

import cn.ac.ios.machine.MachineExporterDOT;
import cn.ac.ios.machine.ia.DIAImpl;
import cn.ac.ios.machine.ia.InterfaceAutomaton;
import cn.ac.ios.machine.ia.util.UtilIA;
import cn.ac.ios.machine.mealy.MealyMachine;
import cn.ac.ios.machine.mealy4ia.MMExporterForIA;
import cn.ac.ios.words.Alphabet;

public class IAToMMTest {

	public static void main(String[] args) {
		Alphabet input = new Alphabet(Integer.class);
		Alphabet output = new Alphabet(Integer.class);
		input.addLetter(0);
		input.addLetter(1);
		
		output.addLetter(0);
		output.addLetter(1);
		output.addLetter(2);
		InterfaceAutomaton IA = new DIAImpl(input.getAPs(), output.getAPs());
		for(int i = 0; i < 4; i++){
			IA.createState();
		}
		IA.setInitial(0);
		IA.getInitial().addTransition(0, 1);
		
		
		MealyMachine M = UtilIA.IAToMM(IA);
		M.getState(M.getInitialState()).getOutput(0);
		MMExporterForIA.exporter(M);
	}
}