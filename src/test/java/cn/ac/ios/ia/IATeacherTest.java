package cn.ac.ios.ia;

import cn.ac.ios.machine.ia.DIAImpl;
import cn.ac.ios.machine.ia.teacher.IATeacherImpl;
import cn.ac.ios.machine.ia.util.AutoIAGenerator;
import cn.ac.ios.words.Alphabet;

public class IATeacherTest {

	public static void main(String[] args) {
		Alphabet input = new Alphabet(Integer.class);
		input.addLetter(0);
		input.addLetter(1);
		
		Alphabet output = new Alphabet(Integer.class);
        output.addLetter(0);
        output.addLetter(1);
        output.addLetter(2);
        
        DIAImpl iIA = (DIAImpl) AutoIAGenerator.generate(input.getAPs(), output.getAPs(), 5);
        DIAImpl oIA = (DIAImpl) AutoIAGenerator.generate(input.getAPs(), output.getAPs(), 5);
        /*
        String CE = "010203";
        String[] wordStr = CE.split("");
        int[] wordArr = new int[wordStr.length];
        System.out.println(wordStr[3]);
        for(int letterNr = 0; letterNr < wordStr.length; letterNr++){
			wordArr[letterNr] = Integer.parseInt(wordStr[letterNr]);
			System.out.println(input.getAPs().indexOf(wordArr[letterNr]));
			
		}
        
        
        System.out.println(input.getAPs().indexOf(2));
        */
        
        Alphabet MMin = new Alphabet(Integer.class);
        Alphabet MMout = new Alphabet(Integer.class);
        MMin.addLetter(0);
		MMin.addLetter(1);
		MMin.addLetter(2);//add Big Delta
		
		MMout.addLetter(0);
        MMout.addLetter(1);
        MMout.addLetter(2);
        MMout.addLetter(3);//add small delta
        MMout.addLetter(4);//add -
        MMout.addLetter(5);//add +
		IATeacherImpl teacher = new IATeacherImpl(iIA, oIA, MMin, MMout);
		
		//System.out.println(iIA.getInAPs().size());
		//System.out.println(teacher.inAlpha.getAPs().size());
		
		
	}

}
