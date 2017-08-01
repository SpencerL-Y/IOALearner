package cn.ac.ios.machine.ia;

public class DTransImpl extends TransImpl{

	public DTransImpl(int lt, InterfaceAutomaton IA) {
		super(lt, IA);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void setSuccessor(int succ) {
		if(this.successors.cardinality() == 0){
			this.successors.set(succ);
		} else {
			System.out.print("error adding successors: determinism violation");
		}
	}
}
