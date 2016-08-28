package com.libsoc;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;

public interface API extends Library{
	static int LS_SHARED = 0;
	static int LS_GREEDY = 1;
	static int LS_WEAK = 2;
	
	static int DIRECTION_INPUT = 0;
	static int DIRECTION_OUTPUT = 1;
	
	static int LEVEL_LOW = 0;
	static int LEVEL_HIGH = 1;
	
	static int EDGE_RISING = 0;
	static int EDGE_FALLING = 1;
	static int EDGE_NONE = 2;
	static int EDGE_BOTH = 3;

	
	API INSTANCE =(API)Native.loadLibrary("soc", API.class);
	
	Pointer libsoc_gpio_request(int gpio_id, int mode);
	int libsoc_gpio_free(Pointer gpio_struct);
	
	int libsoc_gpio_set_direction(Pointer gpio_struct, int direction);
	int libsoc_gpio_get_direction(Pointer gpio_struct);
	
	int libsoc_gpio_set_level(Pointer gpio_struct, int level);
	int libsoc_gpio_get_level(Pointer gpio_struct);
	
	int libsoc_gpio_get_edge(Pointer gpio_struct);
	int libsoc_gpio_set_edge(Pointer gpio_struct, int edge);
	
	//int libsoc_gpio_wait_interrupt(Pointer gpio_struct, int timeout);
	int libsoc_gpio_poll(Pointer gpio_struct, int timeout_ms);
	void libsoc_set_debug(int enable);
	
	Pointer libsoc_board_init();
	int libsoc_board_gpio_id(Pointer board_config, String pin);
	void libsoc_board_free(Pointer board_config);
}
