package cn.ac.ios.machine.ia.util;

import java.util.Random;

import cn.ac.ios.machine.ia.DIAImpl;
import cn.ac.ios.machine.ia.InterfaceAutomaton;
import cn.ac.ios.words.Alphabet;

public class AutoIAGenerator {
	public static InterfaceAutomaton generate(int stateSize){
		Alphabet iAp = new Alphabet(Integer.class);
		Alphabet oAp = new Alphabet(Integer.class);
		iAp.addLetter(0);
		iAp.addLetter(1);
		
		oAp.addLetter(0);
		oAp.addLetter(1);
		oAp.addLetter(2);
		
		InterfaceAutomaton result = new DIAImpl(iAp.getAPs(), oAp.getAPs());
		for(int state = 0; state < stateSize; state++){
			result.createState();
		}
		result.setInitial(0);
		
		for(int state = 0; state < stateSize; state++){
			for(int inA = 0; inA < iAp.getAPSize(); inA++){
				Random r = new Random();
				if(r.nextBoolean()){
					result.getState(state).addTransition(inA, r.nextInt(stateSize));
				}
			}
			for(int outA = 0; outA < oAp.getAPSize(); outA++){
				Random r = new Random();
				if(r.nextBoolean()){
					result.getState(state).addTransition(outA+iAp.getAPSize(), r.nextInt(stateSize));
				}
			}
		}
		return result;
	}
}
