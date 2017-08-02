package cn.ac.ios.ia;

import cn.ac.ios.machine.ia.DIAImpl;
import cn.ac.ios.machine.ia.IAExporterDOT;
import cn.ac.ios.machine.ia.InterfaceAutomaton;
import cn.ac.ios.machine.ia.util.Converter;
import cn.ac.ios.machine.mealy4ia.MMExporterForIA;
import cn.ac.ios.machine.mealy4ia.MMForIA;
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
		IA.getState(1).addTransition(input.getAPSize(), 2);
		IA.getState(1).addTransition(0, 3);
		IA.getState(2).addTransition(1, 2);
		IA.getState(3).addTransition(0, 3);
		IA.getState(2).addTransition(input.getAPSize() + 1, 3);
		IAExporterDOT.export(IA);
		
		MMForIA M = Converter.IAToMM(IA);
		M.getState(M.getInitialState()).getOutput(0);
		MMExporterForIA.exporter(M);
		
		IAExporterDOT.export(Converter.MMToIA(M));
	}
}