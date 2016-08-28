package com.libsoc;

public class Demo {

	public static void main(String[] args) throws InterruptedException {
		GPIO touch = GPIO.createInput(GPIO.gpioID("GPIO-E"));
		GPIO led = GPIO.createOutput(GPIO.gpioID("GPIO-G"));
		int highs = 0, lows=0;
		
		try {
			touch.setEdgeBoth();
			while(true){
				if(touch.poll(100)){
					System.out.println("caught interrupt");
					if(led.isHigh()){
						led.setLow();
						highs++;
					}
					else{
						led.setHigh();
						lows++;
					}
					System.out.println("highs(" + highs + ") lows(" + lows + ")");
				}
			}
		}catch(IllegalArgumentException e){
			System.out.println("caught illegal exception...");
		}finally {
			touch.close();
			led.close();
			GPIO.closeBoard();
		}
	}
}
