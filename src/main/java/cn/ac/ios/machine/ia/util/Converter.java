package cn.ac.ios.machine.ia.util;

import cn.ac.ios.machine.ia.DIAImpl;
import cn.ac.ios.machine.ia.InterfaceAutomaton;
import cn.ac.ios.machine.mealy4ia.MMForIA;
import cn.ac.ios.words.Alphabet;

public class Converter {
	
	
	public static MMForIA IAToMM(InterfaceAutomaton IA){
		IA.addDelta();
		Alphabet inputAp = new Alphabet(Integer.class);
		Alphabet outputAp = new Alphabet(Integer.class);
		//add Big Delta
		for(int inA = 0; inA < IA.getInApSize()+1; inA++){
			inputAp.addLetter(inA);
		}
		//add delta, - and +
		for(int outA = 0; outA < IA.getOutAPs().size()+3; outA++){
			outputAp.addLetter(outA);
		}
		
		MMForIA resultMM = new MMForIA(inputAp.getAPs(), outputAp.getAPs());
		for(int state = 0; state < IA.getStateSize(); state++){
			resultMM.createState();
		}
		resultMM.setInitial(0);
		for(int state = 0; state < IA.getStateSize(); state++){
			cn.ac.ios.machine.ia.State tempState = IA.getState(state);
			for(int inA = 0; inA < IA.getInApSize(); inA++){
				if(tempState.getSuccessors(inA).cardinality() == 0){
					//outputAp.getAPSize()-2 is the character "-"
					resultMM.getState(state).addTransition(inA, state, outputAp.getAPSize()-2);
				} else {
					for(int succ = 0; succ < tempState.getSuccessors(inA).length(); succ++){
						if(tempState.getSuccessors(inA).get(succ)){
							//outputAp.getAPSize()-1 is the character "+"
							resultMM.getState(state).addTransition(inA, succ, outputAp.getAPSize()-1);
						}
					}
				}
			}
			
			for(int outA = IA.getInApSize(); outA < IA.getTotalApSize() + 1; outA++){
				if(tempState.getSuccessors(outA).cardinality() == 0){
					;
				} else {
					for(int succ = 0; succ < tempState.getSuccessors(outA).length(); succ++){
						if(tempState.getSuccessors(outA).get(succ)){
							//inputAp.getAPSize()-1 is character "BigDelta"
								resultMM.getState(state).addTransition(inputAp.getAPSize()-1, succ, outA - IA.getInApSize());	
						}
					}
				}
			}
		}
		return resultMM;
	}

	public static InterfaceAutomaton MMToIA(MMForIA MM){
		Alphabet inputAp = new Alphabet(Integer.class);
		Alphabet outputAp = new Alphabet(Integer.class);
		for(int i = 0; i < MM.getInAPs().size()-1; i++){
			inputAp.addLetter(i);
		}
		//add + and -
		for(int j = 0; j < MM.getOutAPs().size()-3; j++){
			outputAp.addLetter(j);
		}
		
		InterfaceAutomaton resultIOA = new DIAImpl(inputAp.getAPs(), outputAp.getAPs());
		for(int state = 0; state < MM.getStateSize(); state++){
			resultIOA.createState();
		}
		resultIOA.setInitial(MM.getInitialState());
		for(int state = 0; state < MM.getStateSize(); state++){
			for(int inA = 0; inA < MM.getInAPs().size()-1; inA++){
				if(MM.getState(state).getOutput(inA) == MM.getOutAPs().size()-1){
					resultIOA.getState(state)
							 .addTransition(inA, MM.getState(state)
									               .getSuccessor(inA));
				} else {
					;
				}
			}
			if(MM.getState(state).getOutput(MM.getInAPs().size()-1)+resultIOA.getInApSize() != MM.getOutAPs().size()-1){
				resultIOA.getState(state)
				     	.addTransition(MM.getState(state)
				    		          	 .getOutput(MM.getInAPs().size()-1)+resultIOA.getInApSize(), 
				    		           MM.getState(state)
				    		          	 .getSuccessor(MM.getInAPs().size()-1));
			}
		}
		return resultIOA;
	}
	
	
	
	
}
