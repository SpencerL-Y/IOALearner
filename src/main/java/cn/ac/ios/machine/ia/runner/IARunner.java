package cn.ac.ios.machine.ia.runner;

import cn.ac.ios.machine.ia.InterfaceAutomaton;

public interface IARunner {

	void reset();
	int getOutput();
	int getCurrentState();
	void step(int letter);
	
	String equivalenceQuery(InterfaceAutomaton hypothesis);
}
