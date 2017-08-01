package cn.ac.ios.ia;

import java.util.ArrayList;
import java.util.Iterator;

public class Test {

	public static void main(String[] args) {
		ArrayList myList = new ArrayList();// TODO Auto-generated method stub
		myList.add(1);myList.add(2);myList.add(3);myList.add(4);
		for(Iterator itr = myList.iterator(); itr.hasNext();){
			System.out.println(itr.next());
		}
	}

}
 