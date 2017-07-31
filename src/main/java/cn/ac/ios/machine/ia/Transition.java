package cn.ac.ios.machine.ia;

import java.util.BitSet;

public interface Transition {
	
	void setSuccessor(int succ);
	
	BitSet getSuccessors();
	int getLetter();
}
