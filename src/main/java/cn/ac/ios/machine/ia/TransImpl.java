package cn.ac.ios.machine.ia;

import java.util.BitSet;

public class TransImpl implements Transition {
	
	protected final int letter;
	protected BitSet successors;
	
	public TransImpl(int lt){
		this.letter = lt;
		this.successors = new BitSet();
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
