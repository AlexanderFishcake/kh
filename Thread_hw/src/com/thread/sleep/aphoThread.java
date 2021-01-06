package com.thread.sleep;

public class aphoThread implements Runnable {
	String[] str;
	
	

	public aphoThread(String[] str) {
		super();
		this.str = str;
	}



	@Override
	public void run() {
		int i=0;
		while(i<10) {
			
			int rnd = (int)(Math.random()*10);
			System.out.println(str[rnd]);
			
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			i++;
		}
	}

}
