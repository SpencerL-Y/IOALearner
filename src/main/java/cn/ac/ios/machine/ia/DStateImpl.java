package cn.ac.ios.machine.ia;

public class DStateImpl extends StateImpl{

	public DStateImpl(InterfaceAutomaton automaton, int index) {
		super(automaton, index);
	}
	
	@Override
	public void addTransition(int letter, int state) {
		if(this.trans[letter].getSuccessors().cardinality() == 0){
			this.trans[letter] = new DTransImpl(letter, IA);
			this.trans[letter].setSuccessor(state);
		} else {
			System.out.println("transition adding error: determinism violation");
		}
		

	}

}
