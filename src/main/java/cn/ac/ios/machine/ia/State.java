package cn.ac.ios.machine.ia;

import java.util.BitSet;

public interface State {
	InterfaceAutomaton getIA();
	Boolean isInitial(int state);
	
	int getIndex();
	BitSet getSuccessors(int letter);
	void addTransition(int letter, int state);
	
	Boolean isEnable(int letter);
	Boolean isInputEnable();
	
	
}
