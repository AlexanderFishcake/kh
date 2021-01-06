package com.thread.alphabet;

public class LowerAlphbetThread implements Runnable {

	@Override
	public void run() {
		for(int i=0;i<('z'-'a'+1);i++) {
			System.out.printf("%c",i+'a');
		}
		
	}

}
