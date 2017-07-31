package cn.ac.ios.machine.ia.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import cn.ac.ios.machine.ia.State;

public class StatePair {
	
	State OState;
	State IState;
	
	public StatePair(State o, State i){
		this.OState = o;
		this.IState = i;
	}
	
	private Boolean outputStep(int letter){
		for(int i = this.OState.getInApSize(); i < this.OState.getTotalApSize()+1; i++){
			if(this.OState.getSuccessors(i).size() != 0){
				if(this.IState.getSuccessors(i).size() == 0){
					return false;
				}
			}
		}
		return true;
	}
	
	private Boolean inputStep(int letter){
		for(int i = 0; i < this.IState.getInApSize(); i++){
			if(this.IState.getSuccessors(i).size() != 0){
				if(this.OState.getSuccessors(i).size() == 0){
					return false;
				}
			}
		}
		return true;
	}
	
	private List<StatePair> oStepPair(int letter){
		assert outputStep(letter);
		List<StatePair> tempList = new ArrayList<StatePair>();
		for(int i = this.OState.getInApSize(); 
				i < this.OState.getTotalApSize()+1; 
				i++){
			if(this.OState.getSuccessors(i).size() != 0 && 
			   this.IState.getSuccessors(i).size() != 0){
				for(int j = 0; j < this.OState.getSuccessors(i).size(); j++){
					for(int k = 0; k < this.IState.getSuccessors(i).size();k++){
						if(this.OState.getSuccessors(i).get(j) && 
						   this.OState.getSuccessors(i).get(k)){
							StatePair p = new StatePair(this.OState.getIA().getState(j),
														this.IState.getIA().getState(k));
							if(!(p.OState.getIndex() == this.OState.getIndex() && 
								 p.IState.getIndex() == this.IState.getIndex())){
								tempList.add(p);
							}
						}
					}
				}
				
			}
		}
		return tempList;
	}
	
	private List<StatePair> iStepPair(int letter){
		assert inputStep(letter);
		List<StatePair> tempList = new ArrayList<StatePair>();
		for(int i = 0; 
				i < this.IState.getInApSize(); 
				i++){
			if(this.OState.getSuccessors(i).size() != 0 && 
			   this.IState.getSuccessors(i).size() != 0){
				for(int j = 0; j < this.OState.getSuccessors(i).size(); j++){
					for(int k = 0; k < this.IState.getSuccessors(i).size();k++){
						if(this.OState.getSuccessors(i).get(j) && 
						   this.OState.getSuccessors(i).get(k)){
							StatePair p = new StatePair(this.OState.getIA().getState(j),
														this.IState.getIA().getState(k));
							if(!(p.OState.getIndex() == this.OState.getIndex() && 
								 p.IState.getIndex() == this.IState.getIndex())){
								tempList.add(p);
							}
								
						}
					}
				}
				
			}
		}
		return tempList;
	}
	
	private Boolean iAltSimCheck(){
		for(int i = 0; i < this.IState.getInApSize(); i++){
			Boolean result = this.inputStep(i);
			if(result){
				for(Iterator<StatePair> itr = this.iStepPair(i).iterator(); itr.hasNext();){
					StatePair p = itr.next();
					result = result && p.iAltSimCheck() && p.oAltSimCheck();
					if(!result){
						return result;
					}
				}
			} else {
				return result;
			}
		}
		return true;
	}
	
	private Boolean oAltSimCheck(){
		for(int i = this.OState.getInApSize(); i < this.OState.getTotalApSize(); i++){
			Boolean result = this.outputStep(i);
			if(result){
				for(Iterator<StatePair> itr = this.oStepPair(i).iterator(); itr.hasNext();){
					StatePair p = itr.next();
					result = result && p.oAltSimCheck() && p.iAltSimCheck();
					if(!result){
						return result;
					}
				}
			} else {
				return result;
			}
		}
		return true;
	}
	
	public Boolean alterSimCheck(){
		assert this.OState.isInitial(this.OState.getIndex());
		assert this.IState.isInitial(this.IState.getIndex());
		
		return this.iAltSimCheck() && this.oAltSimCheck();
	}
}
