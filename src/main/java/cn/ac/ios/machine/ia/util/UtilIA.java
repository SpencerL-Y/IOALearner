package cn.ac.ios.machine.ia.util;

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
		oIA.addDelta(); iIA.addDelta();
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
		aIA.addDelta(); iIA.addDelta();
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
		for(int i = 0; i < IA.getInApSize()+1; i++){
			inputAp.addLetter(i);
		}
		//add + and -
		for(int j = 0; j < IA.getOutAPs().size()+3; j++){
			outputAp.addLetter(j);
		}
		
		MealyMachine resultMM = new MealyMachine(inputAp.getAPs(), outputAp.getAPs());
		for(int i = 0; i < IA.getStateSize(); i++){
			resultMM.createState();
		}
		resultMM.setInitial(0);
		for(int i = 0; i < IA.getStateSize(); i++){
			cn.ac.ios.machine.ia.State tempState = IA.getState(i);
			for(int j = 0; j < IA.getInApSize(); j++){
				if(tempState.getSuccessors(j).cardinality() == 0){
					//-
					resultMM.getState(i).addTransition(j, i, outputAp.getAPSize()-2);
				} else {
					for(int k = 0; k < tempState.getSuccessors(j).length(); k++){
						if(tempState.getSuccessors(j).get(k)){
							//+
							
							resultMM.getState(i).addTransition(j, k, outputAp.getAPSize()-1);
						}
					}
				}
			}
			
			for(int j = IA.getInApSize(); j < IA.getTotalApSize() + 1; j++){
				if(tempState.getSuccessors(j).cardinality() == 0){
					;
				} else {
					for(int k = 0; k < tempState.getSuccessors(j).length(); k++){
						if(tempState.getSuccessors(j).get(k)){
								resultMM.getState(i).addTransition(inputAp.getAPSize()-1, k, j - IA.getInApSize());	
						}
					}
				}
			}
		}
		return resultMM;
	}
}
