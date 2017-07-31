package cn.ac.ios.machine.ia;

public class IAExporterDOT {
	public static void export(InterfaceAutomaton IA){
		if(IA instanceof InterfaceAutomaton){
			System.out.println("//Interface Automaton ");
			System.out.println("digraph G {");
			System.out.println("node [shape = doublecircle]; " + IA.getInitial().getIndex() + ";");
			System.out.println("node [shape = circle];");
			for(int i = 0; i < IA.getStateSize(); i++){
				for(int k = 0; k < IA.getInAPs().size() + IA.getOutAPs().size(); k++){
					if(IA.getState(i).getSuccessors(k).size() == 0){
						continue;
					}
					for(int j = 0; j < IA.getState(i).getSuccessors(k).size(); j++){
						if(IA.getState(i).getSuccessors(k).get(j)){
							System.out.print(i + " -> " + j);
							if(k >= IA.getInAPs().size()){
								System.out.println(" [label = \"out:" + (k-IA.getInAPs().size()) + "\"];");
							} else {
								System.out.println(" [ label = \"in:" + k + "\"];");
							}
						}
					}
				}
			}
			System.out.println("}");
		}
		else{
			System.out.println("ERROR");
		}
	}
}
