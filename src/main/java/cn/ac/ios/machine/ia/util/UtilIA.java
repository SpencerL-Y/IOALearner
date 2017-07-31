package cn.ac.ios.machine.ia.util;

import cn.ac.ios.machine.ia.InterfaceAutomaton;

public class UtilIA {
	public static Boolean alternatingSimCheck(InterfaceAutomaton oIA, InterfaceAutomaton iIA){
		StatePair ip = new StatePair(oIA.getInitial(), iIA.getInitial());
		return ip.alterSimCheck();
	}
}
