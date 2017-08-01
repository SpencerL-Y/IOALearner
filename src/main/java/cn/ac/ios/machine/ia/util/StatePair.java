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
	
	private List<StatePair> oStepPair(int letter, Boolean grid[][]){
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
						   this.IState.getSuccessors(i).get(k)){
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
		}
		return tempList;
	}
	
	private List<StatePair> iStepPair(int letter, Boolean grid[][]){
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
						   this.IState.getSuccessors(i).get(k)){
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



	public Boolean aAIStep(int letter){
		for(int i = 0; i < this.OState.getTotalApSize()+1; i++){
			if(this.OState.getSuccessors(i).size() != 0){
				if(this.IState.getSuccessors(i).size() == 0){
					return false;
				}
			}
		}
		return true;
	}
	
	private Boolean iAIStep(int letter){
		for(int i = 0; i < this.IState.getInApSize(); i++){
			if(this.IState.getSuccessors(i).size() != 0){
				if(this.OState.getSuccessors(i).size() == 0){
					return false;
				}
			}
		}
		return true;
	}
	
	private List<StatePair> aAIStepPair(int letter, Boolean grid[][]){
		assert aAIStep(letter);
		List<StatePair> tempList = new ArrayList<StatePair>();
		for(int i = 0; 
				i < this.OState.getTotalApSize()+1; 
				i++){
			if(this.OState.getSuccessors(i).size() != 0 && 
			   this.IState.getSuccessors(i).size() != 0){
				for(int j = 0; j < this.OState.getSuccessors(i).size(); j++){
					for(int k = 0; k < this.IState.getSuccessors(i).size();k++){
						if(this.OState.getSuccessors(i).get(j) && 
						   this.IState.getSuccessors(i).get(k)){
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
		}
		return tempList;
	}
	
	private List<StatePair> iAIStepPair(int letter, Boolean grid[][]){
		assert iAIStep(letter);
		List<StatePair> tempList = new ArrayList<StatePair>();
		for(int i = 0; 
				i < this.IState.getInApSize(); 
				i++){
			if(this.OState.getSuccessors(i).size() != 0 && 
			   this.IState.getSuccessors(i).size() != 0){
				for(int j = 0; j < this.OState.getSuccessors(i).size(); j++){
					for(int k = 0; k < this.IState.getSuccessors(i).size();k++){
						if(this.OState.getSuccessors(i).get(j) && 
						   this.IState.getSuccessors(i).get(k)){
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
		}
		return tempList;
	}
	
	private Boolean iAISimCheck(Boolean grid[][]){
		for(int i = 0; 
				i < this.IState.getInApSize(); 
				i++){
			Boolean result = this.iAIStep(i);
			if(result){
				for(Iterator<StatePair> itr = this.iAIStepPair(i,grid).iterator(); itr.hasNext();){
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
				for(Iterator<StatePair> itr = this.aAIStepPair(i, grid).iterator(); itr.hasNext();){
					StatePair p = itr.next();
					result = result && p.aAISimCheck(grid) && p.iAltSimCheck(grid);
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
		return this.aAISimCheck(grid) && this.iAISimCheck(grid);
	}



}
