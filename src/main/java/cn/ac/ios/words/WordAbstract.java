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

abstract class WordAbstract implements Word {

	protected final Alphabet alphabet;
	
	public WordAbstract(Alphabet alphabet) {
		assert alphabet != null;
		this.alphabet = alphabet;
	}

	@Override
	public Alphabet getAlphabet() {
		return alphabet;
	}
	
	@Override
	public boolean isEmpty() {
		return false;
	}
	
	@Override
	public int getFirstLetter() {
		return -1;
	}

	@Override
	public int getLastLetter() {
		return -1;
	}

	@Override
	public Word getEmptyWord() {
		return alphabet.getEmptyWord();
	}

	@Override
	public Word getLetterWord(int letter) {
		return new WordLetter(alphabet, letter);
	}

	@Override
	public Word fromLetters(int... letters) {
		return new WordArray(alphabet, letters);
	}

	@Override
	public Word fromArray(int[] letters, int offset, int length) {
		return new WordArray(alphabet, letters, offset, length);
	}

	@Override
	public Word concat(Word word) {
		if(word.isEmpty()) return this;
		if(isEmpty()) return word;
		int length = this.length() + word.length();
		int[] data = new int[length];
		write(0, data, 0, this.length());
		word.write(0, data, this.length(), word.length());
		return new WordArray(alphabet, data);
	}

	@Override
	public Word concat(Word... words) {
				
		int length = this.length();
		for(Word word : words) {
			length += word.length();
		}
		int[] data = new int[length];
		int offsetArr = this.length();
		write(0, data, 0, this.length());
		for(Word word : words) {
			word.write(0, data, offsetArr, word.length());
			offsetArr += word.length();
		}
		if(length == 0) return getEmptyWord();
		if(length == 1) return getLetterWord(data[0]);
		return new WordArray(alphabet, data);
	}
	
	public boolean equals(Object o) {
		
		if(! (o  instanceof Word)) {
			return false;
		}
		Word word = (WordAbstract)o;
		
		if(length() != word.length()) return false;
		for(int letterNr = 0; letterNr < length(); letterNr ++) {
			if(getLetter(letterNr) != word.getLetter(letterNr)) 
				return false;
		}
		return true;
	}
	
	@Override
	public int hashCode() {
		int hashCode = 1;
		for(int letterNr = 0; letterNr < length(); letterNr ++) {
			hashCode = 31 * hashCode + getLetter(letterNr);
		}
		return hashCode;
	}

}
