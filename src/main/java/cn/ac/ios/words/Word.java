/* Copyright (c) 2016, 2017                                               */
/*       Institute of Software, Chinese Academy of Sciences               */
/* This file is part of ROLL, a Regular Omega Language Learning library.  */
/* ROLL is free software: you can redistribute it and/or modify           */
/* it under the terms of the GNU General Public License as published by   */
/* the Free Software Foundation, either version 3 of the License, or      */
/* (at your option) any later version.                                    */

/* This program is distributed in the hope that it will be useful,        */
/* but WITHOUT ANY WARRANTY; without even the implied warranty of         */
/* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the          */
/* GNU General Public License for more details.                           */

/* You should have received a copy of the GNU General Public License      */
/* along with this program.  If not, see <http://www.gnu.org/licenses/>.  */

package cn.ac.ios.words;

// we use integer to denote each letter
public interface Word extends Iterable<Integer> {
	
	Alphabet getAlphabet();
	
	default APList getAPs() {
		return getAlphabet().getAPs();
	}
	
	default Word getEmptyWord() {
		return getAlphabet().getEmptyWord();
	}
	
	void write(int offsetWord, int[] letters, int offsetArr, int length);
	
	Word getLetterWord(int letter);
	
	Word fromLetters(int ...letters);
	
	Word fromArray(int[] letters, int offset, int length);
		
	Word concat(Word word);
	
	Word concat( Word... words);
	
	int getLetter(int index);
	
	int length();
	
	// do contain the value at index toIdx
	Word getSubWord(int offset, int length);
	
	Word getPrefix(int prefixLength);
	
	Word getSuffix(int offset);
	
	Word append(int letter);
	
	Word preappend(int letter);
	
	int getFirstLetter();
	
	int getLastLetter();
	
	boolean isPrefixOf(Word word);
	
	boolean isSuffixOf(Word word);
	
	boolean isEmpty();
	
	String toString();
	
	default String toStringExact() {
		if(isEmpty()) return "";
		return toStringWithAlphabet();
	}
	
	default String toStringWithAlphabet() {
		StringBuilder builder = new StringBuilder();
		for(int letterNr = 0; letterNr < length(); letterNr ++) {
			int letter = getLetter(letterNr);
			builder.append(getAPs().get(letter).toString());
		}
		return builder.toString();
	}
	

	
	

}
