package com.libsoc;

public class API{
	public static int LS_SHARED;
	public static int LS_GREEDY;
	public static int LS_WEAK;
	
	public static int DIRECTION_INPUT;
	public static int DIRECTION_OUTPUT;
	
	public static int LEVEL_LOW;
	public static int LEVEL_HIGH;
	
	public static int EDGE_RISING;
	public static int EDGE_FALLING;
	public static int EDGE_NONE;
	public static int EDGE_BOTH;

	private static API _instance = null;
	
	private API() {
		System.loadLibrary("soc_jni");
		defineConstants();
		System.out.println(LS_SHARED);
	}
	
	private native static void defineConstants();
	
	public static API getInstance() {
		if(_instance == null) {
			_instance = new API();
		}
		return _instance;
	}
	
	native long libsoc_gpio_request(int gpio_id, int mode);
	native int libsoc_gpio_free(long gpio_addr);
	
	native int libsoc_gpio_set_direction(long gpio_addr, int direction);
	native int libsoc_gpio_get_direction(long gpio_addr);
	
	native int libsoc_gpio_set_level(long gpio_addr, int level);
	native int libsoc_gpio_get_level(long gpio_addr);
	
	native int libsoc_gpio_get_edge(long gpio_addr);
	native int libsoc_gpio_set_edge(long gpio_addr, int edge);
	
	native void libsoc_set_debug(int enable);
	native int libsoc_gpio_poll(long gpio_addr, int timeout_ms);
	
	native long libsoc_board_init();
	native int libsoc_board_gpio_id(long config_addr, String pin);
	native void libsoc_board_free(long config_addr);
}
	

