package cn.ac.ios.machine.ia;

import cn.ac.ios.words.APList;
import cn.ac.ios.words.Word;

public interface InterfaceAutomaton {
	APList getInAPs();
	
	APList getOutAPs();
	
	State getInitial();
	State getState(int index);
	State createState();
	
	int getStateSize();
	int getInApSize();
	int getTotalApSize();

	int getSuccessor(int state, int letter);
	
	void addDelta();
	void removeDelta();
	void setInitial(int state);
	
	Boolean isDeterministic();
	Boolean isDeltaAdded();
}
