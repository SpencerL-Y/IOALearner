package cn.ac.ios.machine.ia.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import cn.ac.ios.machine.ia.State;

public class StatePair {
	
	public final State OState;
	public final State IState;
	
	public StatePair(State o, State i){
		this.OState = o;
		this.IState = i;
	}
	
	private Boolean outputStep(int letter){
		if(this.OState.getSuccessors(letter).length() != 0){
			if(this.IState.getSuccessors(letter).length() == 0){
				return false;
			}
		}
		return true;
	}
	
	private Boolean inputStep(int letter){
		if(this.IState.getSuccessors(letter).length() != 0){
			if(this.OState.getSuccessors(letter).length() == 0){
				return false;
			}
		}
		return true;
	}
	
	private List<StatePair> oStepPair(int letter, Boolean grid[][]){
		assert outputStep(letter);
		List<StatePair> tempList = new ArrayList<StatePair>();
			if(this.OState.getSuccessors(letter).length() != 0 && 
			   this.IState.getSuccessors(letter).length() != 0){
				for(int j = 0; j < this.OState.getSuccessors(letter).length(); j++){
					for(int k = 0; k < this.IState.getSuccessors(letter).length();k++){
						if(this.OState.getSuccessors(letter).get(j) && 
						   this.IState.getSuccessors(letter).get(k)){
							StatePair p = new StatePair(this.OState.getIA().getState(j),
													    this.IState.getIA().getState(k));
							if(!grid[p.OState.getIndex()][p.IState.getIndex()]){
								grid[p.OState.getIndex()][p.IState.getIndex()] = true;
								tempList.add(p);
							}
						}
					}
				}
			}
		return tempList;
	}
	
	private List<StatePair> iStepPair(int letter, Boolean grid[][]){
		assert inputStep(letter);
		List<StatePair> tempList = new ArrayList<StatePair>();
			if(this.OState.getSuccessors(letter).length() != 0 && 
			   this.IState.getSuccessors(letter).length() != 0){
				for(int j = 0; j < this.OState.getSuccessors(letter).length(); j++){
					for(int k = 0; k < this.IState.getSuccessors(letter).length();k++){
						if(this.OState.getSuccessors(letter).get(j) && 
						this.IState.getSuccessors(letter).get(k)){
						StatePair p = new StatePair(this.OState.getIA().getState(j),
													this.IState.getIA().getState(k));
						if(!grid[p.OState.getIndex()][p.IState.getIndex()]){
							grid[p.OState.getIndex()][p.IState.getIndex()] = true;
							tempList.add(p);
						}
					}
				}
			}
		}
		return tempList;
	}
	
	private Boolean iAltSimCheck(Boolean grid[][]){
		for(int i = 0; i < this.IState.getInApSize(); i++){
			Boolean result = this.inputStep(i);
			if(result){
				for(Iterator<StatePair> itr = this.iStepPair(i,grid).iterator(); itr.hasNext();){
					StatePair p = itr.next();
					result = result && p.oAltSimCheck(grid) && p.iAltSimCheck(grid);
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
	
	private Boolean oAltSimCheck(Boolean grid[][]){
		for(int i = this.OState.getInApSize(); i < this.OState.getTotalApSize()+1; i++){
			Boolean result = this.outputStep(i);
			if(result){
				for(Iterator<StatePair> itr = this.oStepPair(i, grid).iterator(); itr.hasNext();){
					StatePair p = itr.next();
					result = result && p.oAltSimCheck(grid) && p.iAltSimCheck(grid);
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
	
	public Boolean alterSimCheck(Boolean grid[][]){
		assert this.OState.isInitial(this.OState.getIndex());
		assert this.IState.isInitial(this.IState.getIndex());
		return this.iAltSimCheck(grid) && this.oAltSimCheck(grid);
	}
	
	
	
	
	
	
	
	private Boolean iAISimCheck(Boolean grid[][]){
		for(int i = 0; i < this.IState.getInApSize(); i++){
			Boolean result = this.inputStep(i);
			if(result){
				for(Iterator<StatePair> itr = this.iStepPair(i,grid).iterator(); itr.hasNext();){
					StatePair p = itr.next();
					result = result && p.aAISimCheck(grid) && p.iAISimCheck(grid);
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
	
	private Boolean aAISimCheck(Boolean grid[][]){
		for(int i = 0; i < this.OState.getTotalApSize()+1; i++){
			Boolean result = this.outputStep(i);
			if(result){
				for(Iterator<StatePair> itr = this.oStepPair(i, grid).iterator(); itr.hasNext();){
					StatePair p = itr.next();
					result = result && p.oAltSimCheck(grid) && p.iAltSimCheck(grid);
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
	
	public Boolean AISimCheck(Boolean grid[][]){
		assert this.OState.isInitial(this.OState.getIndex());
		assert this.IState.isInitial(this.IState.getIndex());
		return this.iAISimCheck(grid) && this.aAISimCheck(grid);
	}

}
