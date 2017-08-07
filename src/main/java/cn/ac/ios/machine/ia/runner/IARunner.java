package cn.ac.ios.machine.ia.teacher;

import cn.ac.ios.machine.ia.InterfaceAutomaton;

public interface IATeacher {

	void reset();
	int getOutput();
	int getCurrentState();
	void step(int letter);
	
	String equivalenceQuery(InterfaceAutomaton hypothesis);
}
