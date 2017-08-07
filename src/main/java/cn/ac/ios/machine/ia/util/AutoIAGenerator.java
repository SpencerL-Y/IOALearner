package cn.ac.ios.machine.ia.util;

import java.util.Random;

import cn.ac.ios.machine.ia.DIAImpl;
import cn.ac.ios.machine.ia.InterfaceAutomaton;
import cn.ac.ios.words.APList;
import cn.ac.ios.words.Alphabet;

public class AutoIAGenerator {
	public static InterfaceAutomaton generate(APList input, APList output,int stateSize){
		InterfaceAutomaton result = new DIAImpl(input, output);
		for(int state = 0; state < stateSize; state++){
			result.createState();
		}
		result.setInitial(0);
		
		for(int state = 0; state < stateSize; state++){
			for(int inA = 0; inA < input.size(); inA++){
				Random r = new Random();
				result.getState(state).addTransition(inA, r.nextInt(stateSize));
				
			}
			for(int outA = 0; outA < output.size(); outA++){
				Random r = new Random();
				if(r.nextBoolean()){
					result.getState(state).addTransition(outA+output.size(), r.nextInt(stateSize));
					break;
				}
			}
		}
		return result;
	}
}
