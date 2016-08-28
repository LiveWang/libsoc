package com.libsoc;

public class GPIOTest {
	public static Object lock = new Object();
	
	public static class CallbackTest implements CallBack{
		int count = 0;

		public void OnInterrupt(GPIO g) {
			count++;
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		// Create both gpio objects
		GPIO gpioIn = GPIO.createInput(GPIO.gpioID("GPIO-E"));
		GPIO gpioOut = GPIO.createOutput(GPIO.gpioID("GPIO-G"));
		
		try {
			// Enable debug output
			GPIO.setDebugOn();
			
			// Check the direction
			assert API.DIRECTION_INPUT == gpioIn.getDirection();
			assert API.DIRECTION_OUTPUT == gpioOut.getDirection();
			
			// Set level HIGH and check level in software and hardware
			gpioOut.setHigh();
			assert gpioOut.isHigh();
			assert gpioIn.isHigh();
			
			// Set level LOW and check level in software and hardware
			gpioOut.setLow();
			assert gpioOut.isLow();
			assert gpioIn.isLow();
			
			// Turn off debug printing for fast toggle
			GPIO.setDebugOff();
			
			// Toggle the GPIO 1000 times as fast as it can go
			for (int i=0; i<1000; i++)
			{
			    gpioOut.setHigh();
			    gpioOut.setLow();
			}
			
			// Turn debug back on
			GPIO.setDebugOn();
			  
			// Set edge to RISING
			gpioIn.setEdgeRising();
			  
			// Check Edge
			assert API.EDGE_RISING == gpioIn.getEdge();
			  
			// Set edge to FALLING
			gpioIn.setEdgeFalling();
			  
			// Check Edge
			assert API.EDGE_FALLING == gpioIn.getEdge();
			  
			// Set edge to BOTH
			gpioIn.setEdgeBoth();
						  
			// Check Edge
			assert API.EDGE_BOTH == gpioIn.getEdge();

			// Set edge to NONE
			gpioIn.setEdgeNone();
						  
			// Check Edge
			assert API.EDGE_NONE == gpioIn.getEdge();
			
			//test wait for interrupt
			Thread t = new Thread(new Runnable() {
				public void run() {
					try {
						synchronized(lock){
							lock.wait();
						}
						Thread.sleep(10);  // wait 1/100th of second just to be safe
					} catch (InterruptedException e) {
						System.out.println("ERROR: Unable to wait for test signal");
					}
					gpioOut.setHigh();
					gpioOut.setLow();
				}
			});
			t.start();
	
			gpioOut.setLow();
			gpioIn.setEdgeFalling();
			synchronized(lock){
				lock.notify();
			}
			gpioIn.waitInterrupt(1000);	
			
			CallbackTest cb = new CallbackTest();
			Thread h = gpioIn.startInterruptHandler(cb);
			
			GPIO.setDebugOff();
			for(int i = 0; i < 100; i++){
				Thread.sleep(10);
				gpioOut.setHigh();
				gpioOut.setLow();
			}
			Thread.sleep(100);
			GPIO.setDebugOn();
			
			h.interrupt();
			h.join();
		    System.out.println("Sent 100 interrupts, received: " + cb.count);
		} catch(IllegalArgumentException e){
			System.out.println("caught illegal exception...");
			//System.out.println(e.getMessage());
			//e.printStackTrace();
		}finally {
			gpioIn.close();
			gpioOut.close();
			GPIO.closeBoard();
			System.out.println("Finish!");
		}
	}

}



