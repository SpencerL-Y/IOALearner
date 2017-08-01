package cn.ac.ios.machine.ia;

import java.util.BitSet;

public class TransImpl implements Transition {
	
	protected  int letter = -1;
	protected  BitSet successors;
	
	public TransImpl(int lt, InterfaceAutomaton IA){
		this.letter = lt;
		int num = IA.getStateSize();
		this.successors = new BitSet(num);
		
		
	}
	
	@Override
	public BitSet getSuccessors() {
		return this.successors;
	}

	@Override
	public int getLetter() {
		return this.letter;
	}

	@Override
	public void setSuccessor(int succ) {
		this.successors.set(succ);
	}

}
