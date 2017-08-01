package cn.ac.ios.machine.ia.util;

import cn.ac.ios.machine.ia.InterfaceAutomaton;

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
}
