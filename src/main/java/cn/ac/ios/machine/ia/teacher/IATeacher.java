package cn.ac.ios.machine.ia.teacher;

public interface IATeacher {

	void reset();
	int getOutput(int letter);
	int step(int letter);
	
	
}
