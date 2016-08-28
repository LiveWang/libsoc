package com.libsoc;

interface CallBack {
    void OnInterrupt(GPIO g);
}

public class InterruptHandler extends Thread{
	GPIO gpio; 
	CallBack callback;
	
	public InterruptHandler(GPIO g, CallBack c){
		this.gpio = g;
		this.callback = c;
	}
	 
	public void run() { 
		while (!this.isInterrupted()) {
			if(gpio.poll(1000)){
				callback.OnInterrupt(gpio);
			}
		}
	} 

}
