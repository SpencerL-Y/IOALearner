package cn.ac.ios.machine.ia;

import java.util.BitSet;

public interface State {
	InterfaceAutomaton getIA();
	Boolean isInitial(int state);
	
	int getIndex();
	int getTotalApSize();
	int getInApSize();
	int getOutApSize();
	BitSet getSuccessors(int letter);
	int getSuccessor(int letter);
	void addTransition(int letter, int state);
	void rmTransition(int letter);
	
	Boolean isEnable(int letter);
	Boolean isInputEnable();
	Boolean isQuiescent();
	
	
}
