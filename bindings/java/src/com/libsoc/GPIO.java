package com.libsoc;

import com.sun.jna.Pointer;

public class GPIO {
	Pointer _gpio;
	int id;
	static Pointer _board_config;
	
	public GPIO(int gpio_id, int mode, int direction) {
		//validate mode, direction
		if(mode!= API.LS_SHARED && mode!= API.LS_GREEDY && mode!= API.LS_WEAK){
			throw new IllegalArgumentException("Invalid GPIO mode: " + mode);
		}
		if(direction!= API.DIRECTION_INPUT && direction!= API.DIRECTION_OUTPUT){
			throw new IllegalArgumentException("Invalid GPIO direction: " + direction);
		}
	
		//request a gpio & validate
		_gpio = API.INSTANCE.libsoc_gpio_request(gpio_id, mode);
		if(_gpio == null) {
			throw new IllegalArgumentException("Unable to request GPIO " + gpio_id);
		}
		
		//set direction& validate
		API.INSTANCE.libsoc_gpio_set_direction(_gpio, direction);
		if(direction != this.getDirection()){	
			throw new IllegalArgumentException("Unable to set GPIO " + gpio_id + " direction");
		}
		
		id = gpio_id;
	}
	
	public static int gpioID(String pinname){
		int gpio_id;
		if(_board_config == null){
			_board_config = API.INSTANCE.libsoc_board_init();
		}
		gpio_id = API.INSTANCE.libsoc_board_gpio_id(_board_config, pinname);
		if(gpio_id < 0){
			throw new IllegalArgumentException("Invalid GPIO pin name " + pinname);
		}
		return gpio_id;
	}
	
	public static void closeBoard(){
		API.INSTANCE.libsoc_board_free(_board_config);
	}
	
	private void setEdge(int edge) {
		if(this.getDirection() == API.DIRECTION_INPUT && 
				edge!= API.EDGE_BOTH && edge!= API.EDGE_FALLING && edge!= API.EDGE_NONE && edge!= API.EDGE_RISING){
			throw new IllegalArgumentException("Invalid GPIO edge: " + edge + " for GPIO" + id);
		}
		if (API.INSTANCE.libsoc_gpio_set_edge(_gpio, edge) != 0){
			throw new IllegalArgumentException("Unable to set GPIO " + id + " edge");
		}     
	}
	
	//Interrupt
	boolean poll(int timeout_ms){
		return API.INSTANCE.libsoc_gpio_poll(_gpio, timeout_ms) == 0;
	}
	
	boolean waitInterrupt(int timeout){//
		assert this._gpio != null:"Interrupt: Pointer should not be null";
		assert this.getDirection() == API.DIRECTION_INPUT:"Interrupt: Direction should be Input";
		assert this.getEdge() != API.EDGE_NONE && this.getEdge() > 0:"Interrupt: Edge should not be NONE or ERROR";
		
		return API.INSTANCE.libsoc_gpio_poll(_gpio, timeout) == 0;
	}
	
	Thread startInterruptHandler(CallBack c) throws InterruptedException{
        /*
         * Returns a thread that continuosly polls the GPIO. If an interrupt is
           encountered, the interrupt_callback function will be run. This
           thread can be stopped by calling interrupt_handler.stop()
        */
        Thread ih = new InterruptHandler(this, c);
        ih.start();
        Thread.sleep(10);
        return ih;
	}
	
	//Request GPIO & Close
	public static GPIO createOutput(int gpio_id) {
		return new GPIO(gpio_id, API.LS_SHARED, API.DIRECTION_OUTPUT);
	}
	
	public static GPIO createInput(int gpio_id) {
		return new GPIO(gpio_id, API.LS_SHARED, API.DIRECTION_INPUT);
	}
	
	void close() {
		if(_gpio != null){
			API.INSTANCE.libsoc_gpio_free(_gpio);
			_gpio = null;
		}
	}
	
	public static void setDebugOn(){
		API.INSTANCE.libsoc_set_debug(1);
	}  
	
	public static void setDebugOff(){
		API.INSTANCE.libsoc_set_debug(0);
	}  
	
	//Set & Get Direction
	void setInput(){
		API.INSTANCE.libsoc_gpio_set_direction(_gpio, API.DIRECTION_INPUT);
	}
	
	void setOutput(){
		API.INSTANCE.libsoc_gpio_set_direction(_gpio, API.DIRECTION_OUTPUT);
	}
	
	int getDirection(){
		int d;
		d = API.INSTANCE.libsoc_gpio_get_direction(_gpio);
		if (d == -1){
			throw new IllegalArgumentException("Error reading GPIO " + id + " direction!");
		}  
	    return d;
	}
	
	//Set & Get Level
	void setHigh() {
		assert this.getDirection() == API.DIRECTION_OUTPUT : "setHigh: Direction shoudld be OUTPUT!";
		API.INSTANCE.libsoc_gpio_set_level(_gpio, API.LEVEL_HIGH);
	}
	
	void setLow() {
		assert this.getDirection() == API.DIRECTION_OUTPUT : "setLow: Direction shoudld be OUTPUT!";
		API.INSTANCE.libsoc_gpio_set_level(_gpio, API.LEVEL_LOW);
	}
	
	boolean isHigh(){
		int l;
		l = API.INSTANCE.libsoc_gpio_get_level(_gpio);
		if (l == -1){
			throw new IllegalArgumentException("Error reading GPIO " + id + " level!");
		}  
	    return l == 1;
	}
	
	boolean isLow(){
		int l;
		l = API.INSTANCE.libsoc_gpio_get_level(_gpio);
		if (l == -1){
			throw new IllegalArgumentException("Error reading GPIO " + id + " level!");
		}  
	    return l == 0;
	}
	
	int getLevel(){
		int l;
		l = API.INSTANCE.libsoc_gpio_get_level(_gpio);
		if (l == -1){
			throw new IllegalArgumentException("Error reading GPIO " + id + " level!");
		}  
	    return l;
	}
	
	//Set & Get Edge
	void setEdgeNone(){
		this.setEdge(API.EDGE_NONE);
	}
	
	void setEdgeBoth(){
		this.setEdge(API.EDGE_BOTH);
	}
	
	void setEdgeRising(){
		this.setEdge(API.EDGE_RISING);
	}
	
	void setEdgeFalling(){
		this.setEdge(API.EDGE_FALLING);
	}
	
	int getEdge(){
		int e;
		e = API.INSTANCE.libsoc_gpio_get_edge(_gpio);
		if (e == -1){
			throw new IllegalArgumentException("Error reading GPIO " + id + " edge!");
		}  
	    return e;
	}
}

