package cn.ac.ios.machine.ia.util;

import cn.ac.ios.machine.ia.InterfaceAutomaton;

public class SimulationChecker {
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
	
	public static Boolean bisimulationCheck(InterfaceAutomaton aIA, InterfaceAutomaton iIA){
		Boolean grid[][] = new Boolean[aIA.getStateSize()][iIA.getStateSize()];
		for(int i = 0; i < aIA.getStateSize(); i++){
			for(int j = 0; j < iIA.getStateSize(); j++){
				grid[i][j] = false;
			}
		}
		StatePair ip = new StatePair(aIA.getInitial(), iIA.getInitial());
		Boolean result = ip.bisimulationCheck(grid);
		return result;
	}
}
