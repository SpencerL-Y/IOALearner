package cn.ac.ios.machine.ia;

import cn.ac.ios.words.APList;

public interface InterfaceAutomaton {
	APList getInAPs();
	
	APList getOutAPs();
	
	State getInitial();
	State getState(int index);
	State createState();
	
	int getStateSize();
	
	void setInitial(int state);
	
}
