package cn.ac.ios.machine.mealy4ia;

import cn.ac.ios.machine.mealy.MealyMachine;

public class MMExporterForIA {
	public static void exporter(MealyMachine MM){
		System.out.println("//Interface Automaton ");
		System.out.println("digraph G {");
		System.out.println("node [shape = doublecircle]; " + MM.getInitialState() + ";");
		System.out.println("node [shape = circle];");
		for(int i = 0; i < MM.getStateSize(); i++){
			for(int j = 0; j < MM.getInAPs().size(); j++){
				
				if(j != MM.getInAPs().size()-1){
					if(MM.getState(i).getOutput(j) < MM.getOutAPs().size()-2){
						System.out.println(i + " -> " + MM.getState(i).getSuccessor(j) 
							+" [ label = " + j +"|"+ MM.getState(i).getOutput(j) + " ] ");
					} else if(MM.getState(i).getOutput(j) == MM.getOutAPs().size()-2){
							System.out.println(i + " -> " + MM.getState(i).getSuccessor(j) 
									+" [ label = " + j +"|-" + " ] ");
					} else if(MM.getState(i).getOutput(j) == MM.getOutAPs().size()-1){
							System.out.println(i + " -> " + MM.getState(i).getSuccessor(j) 
									+" [ label = " + j +"|+" + " ] ");
					} else {
						assert false;
					}
				} else if(j == MM.getInAPs().size()-1){
					if(MM.getState(i).getOutput(j) == MM.getOutAPs().size()-3){
						System.out.println(i + " -> " + MM.getState(i).getSuccessor(j) 
								+" [ label =  D" +"|delta" + " ] ");
					} else {
						System.out.println(i + " -> " + MM.getState(i).getSuccessor(j) 
							+" [ label =  D" +"|"+ MM.getState(i).getOutput(j) + " ] ");
					}
				}
			}
		}
		System.out.println("}");
	}
}
