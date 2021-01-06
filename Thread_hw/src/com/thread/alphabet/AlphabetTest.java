package com.thread.alphabet;

public class AlphabetTest {

	public static void main(String[] args) {
		Thread t1 = new Thread(new UpperAlphbetThread());
		Thread t2 = new Thread(new LowerAlphbetThread());
		
		t1.start();
		t2.start();
	}

}
