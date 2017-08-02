package cn.ac.ios.machine.ia.util;

import cn.ac.ios.machine.ia.DIAImpl;
import cn.ac.ios.machine.ia.InterfaceAutomaton;
import cn.ac.ios.machine.mealy.MealyMachine;
import cn.ac.ios.words.Alphabet;

public class UtilIA {
	public static Boolean alternatingSimCheck(InterfaceAutomaton oIA, InterfaceAutomaton iIA){
		Boolean grid[][] = new Boolean[oIA.getStateSize()][iIA.getStateSize()];
		for(int i = 0; i < oIA.getStateSize(); i++){
			for(int j = 0; j < iIA.getStateSize(); j++){
				grid[i][j] = false;
			}
		}
		StatePair ip = new StatePair(oIA.getInitial(), iIA.getInitial());
		return ip.alterSimCheck(grid);
	}
	
	public static Boolean AISimCheck(InterfaceAutomaton aIA, InterfaceAutomaton iIA){
		Boolean grid[][] = new Boolean[aIA.getStateSize()][iIA.getStateSize()];
		for(int i = 0; i < aIA.getStateSize(); i++){
			for(int j = 0; j < iIA.getStateSize(); j++){
				grid[i][j] = false;
			}
		}
		StatePair ip = new StatePair(aIA.getInitial(), iIA.getInitial());
		return ip.AISimCheck(grid);
	}
	
	public static Boolean alternatingSimCheckWithDelta(InterfaceAutomaton oIA, InterfaceAutomaton iIA){
		Boolean grid[][] = new Boolean[oIA.getStateSize()][iIA.getStateSize()];
		for(int i = 0; i < oIA.getStateSize(); i++){
			for(int j = 0; j < iIA.getStateSize(); j++){
				grid[i][j] = false;
			}
		}
		if(!oIA.isDeltaAdded()){
			oIA.addDelta();
		}
		if(!iIA.isDeltaAdded()){
			iIA.addDelta();
		}
		StatePair ip = new StatePair(oIA.getInitial(), iIA.getInitial());
		Boolean result = ip.alterSimCheck(grid);
		oIA.removeDelta(); iIA.removeDelta();
		return result;
		
	}
	
	public static Boolean AISimCheckWithDelta(InterfaceAutomaton aIA, InterfaceAutomaton iIA){
		Boolean grid[][] = new Boolean[aIA.getStateSize()][iIA.getStateSize()];
		for(int i = 0; i < aIA.getStateSize(); i++){
			for(int j = 0; j < iIA.getStateSize(); j++){
				grid[i][j] = false;
			}
		}
		if(!aIA.isDeltaAdded()){
			aIA.addDelta();
		}
		if(!iIA.isDeltaAdded()){
			iIA.addDelta();
		}
		
		StatePair ip = new StatePair(aIA.getInitial(), iIA.getInitial());
		Boolean result = ip.AISimCheck(grid);
		aIA.removeDelta(); iIA.removeDelta();
		return result;
	}
	
	public static MealyMachine IAToMM(InterfaceAutomaton IA){
		IA.addDelta();
		Alphabet inputAp = new Alphabet(Integer.class);
		Alphabet outputAp = new Alphabet(Integer.class);
		//add Big Delta
		for(int inA = 0; inA < IA.getInApSize()+1; inA++){
			inputAp.addLetter(inA);
		}
		//add + and -
		for(int outA = 0; outA < IA.getOutAPs().size()+3; outA++){
			outputAp.addLetter(outA);
		}
		
		MealyMachine resultMM = new MealyMachine(inputAp.getAPs(), outputAp.getAPs());
		for(int state = 0; state < IA.getStateSize(); state++){
			resultMM.createState();
		}
		resultMM.setInitial(0);
		for(int state = 0; state < IA.getStateSize(); state++){
			cn.ac.ios.machine.ia.State tempState = IA.getState(state);
			for(int inA = 0; inA < IA.getInApSize(); inA++){
				if(tempState.getSuccessors(inA).cardinality() == 0){
					//-
					resultMM.getState(state).addTransition(inA, state, outputAp.getAPSize()-2);
				} else {
					for(int succ = 0; succ < tempState.getSuccessors(inA).length(); succ++){
						if(tempState.getSuccessors(inA).get(succ)){
							//+
							
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
								resultMM.getState(state).addTransition(inputAp.getAPSize()-1, succ, outA - IA.getInApSize());	
						}
					}
				}
			}
		}
		return resultMM;
	}

	public static InterfaceAutomaton MMToIA(MealyMachine MM){
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
			resultIOA.getState(state)
				     	.addTransition(MM.getState(state)
				    		          	 .getOutput(MM.getInAPs().size()-1)+resultIOA.getInApSize(), 
				    		           MM.getState(state)
				    		          	 .getSuccessor(MM.getInAPs().size()-1));
			
		}
		
		return resultIOA;
	}

}
