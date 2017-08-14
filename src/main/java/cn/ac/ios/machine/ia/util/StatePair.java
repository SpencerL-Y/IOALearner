package cn.ac.ios.machine.ia.util;



public interface StatePair {
	public Boolean alterSimCheck(Boolean grid[][], String[] CE);
	public Boolean bisimulationCheck(Boolean grid[][], String[] CE);
	public Boolean AISimCheck(Boolean grid[][], String[] CE);
}
