package cn.ac.ios.machine.ia;

import java.util.ArrayList;
import java.util.List;

import cn.ac.ios.words.APList;

public class IAImpl implements InterfaceAutomaton {
	protected State initialState;
	protected final APList iApList;
	protected final APList oApList;
	protected final List<State> states;
	
	
	public IAImpl(APList iAps, APList oAps){
		this.iApList = iAps;
		this.oApList = oAps;
		this.states = new ArrayList<State>();
	}
	
	@Override
	public State getInitial() {
		return this.initialState;
	}

	@Override
	public State getState(int index) {
		assert index < states.size();
		return states.get(index);
	}

	@Override
	public State createState() {
		State state = makeState();
		states.add(state);
		return state;
	}

	@Override
	public int getStateSize() {
		return states.size();
	}

	@Override
	public void setInitial(int state) {
		this.initialState = this.states.get(state);
	}
	
	private State makeState(){
		State newState = new StateImpl(this, this.states.size());
		return newState;
	}

	@Override
	public APList getInAPs() {
		return this.iApList;
	}

	@Override
	public APList getOutAPs() {
		return this.oApList;
	}

	@Override
	public int getInApSize() {
		return this.getInAPs().size();
	}

	@Override
	public int getTotalApSize() {
		return this.getInApSize() + this.getOutAPs().size();
	}

	@Override
	public void addDelta() {
		for(int i = 0; i < this.getStateSize(); i++){
			if(this.getState(i).isQuiescent()){
				this.getState(i).addTransition(this.getTotalApSize(), i);
			}
		}
	}

	@Override
	public void removeDelta() {
		for(int i = 0; i < this.getStateSize(); i++){
			if(this.getState(i).isEnable(getTotalApSize())){
				this.getState(i).rmTransition(this.getTotalApSize());
			}
		}
	}

	@Override
	public Boolean isDeterministic() {
		return false;
	}
}
