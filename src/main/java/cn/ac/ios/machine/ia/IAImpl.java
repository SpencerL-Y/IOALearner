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
		this.states = new ArrayList<>();
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
}