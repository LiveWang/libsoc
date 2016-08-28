#include <jni.h>
#include <stdio.h>
#include <stdlib.h>

#include "com_libsoc_API.h"

#include "libsoc_debug.h"
#include "libsoc_gpio.h"
#include "libsoc_board.h"
/*
 * Class:     com_libsoc_API
 * Method:    defineConstants
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_com_libsoc_API_defineConstants
  (JNIEnv *env, jclass cls)
  {
	jfieldID fid = (*env)->GetStaticFieldID(env, cls, "LS_SHARED", "I");
	(*env)->SetStaticIntField(env, cls, fid, LS_SHARED);
	fid = (*env)->GetStaticFieldID(env, cls, "LS_GREEDY", "I");
	(*env)->SetStaticIntField(env, cls, fid, LS_GREEDY);
	fid = (*env)->GetStaticFieldID(env, cls, "LS_WEAK", "I");
	(*env)->SetStaticIntField(env, cls, fid, LS_WEAK);

	fid = (*env)->GetStaticFieldID(env, cls, "DIRECTION_INPUT", "I");
	(*env)->SetStaticIntField(env, cls, fid, INPUT);
	fid = (*env)->GetStaticFieldID(env, cls, "DIRECTION_OUTPUT", "I");
	(*env)->SetStaticIntField(env, cls, fid, OUTPUT);
	
	fid = (*env)->GetStaticFieldID(env, cls, "LEVEL_LOW", "I");
	(*env)->SetStaticIntField(env, cls, fid, LOW);
	fid = (*env)->GetStaticFieldID(env, cls, "LEVEL_HIGH", "I");
	(*env)->SetStaticIntField(env, cls, fid, HIGH);
	
	fid = (*env)->GetStaticFieldID(env, cls, "EDGE_RISING", "I");
	(*env)->SetStaticIntField(env, cls, fid, RISING);
	fid = (*env)->GetStaticFieldID(env, cls, "EDGE_FALLING", "I");
	(*env)->SetStaticIntField(env, cls, fid, FALLING);
	fid = (*env)->GetStaticFieldID(env, cls, "EDGE_NONE", "I");
	(*env)->SetStaticIntField(env, cls, fid, NONE);
	fid = (*env)->GetStaticFieldID(env, cls, "EDGE_BOTH", "I");
	(*env)->SetStaticIntField(env, cls, fid, BOTH);
  }

/*
 * Class:     com_libsoc_API
 * Method:    libsoc_gpio_request
 * Signature: (II)J
 */
JNIEXPORT jlong JNICALL Java_com_libsoc_API_libsoc_1gpio_1request
   (JNIEnv *env, jobject obj, jint gpio_id, jint mode)
  {
	  return (jlong)libsoc_gpio_request(gpio_id, mode);
  }

/*
 * Class:     com_libsoc_API
 * Method:    libsoc_gpio_free
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_com_libsoc_API_libsoc_1gpio_1free
  (JNIEnv *env, jobject obj, jlong gpio_addr)
  {
	  return libsoc_gpio_free((gpio*)gpio_addr);
  }

/*
 * Class:     com_libsoc_API
 * Method:    libsoc_gpio_set_direction
 * Signature: (JI)I
 */
JNIEXPORT jint JNICALL Java_com_libsoc_API_libsoc_1gpio_1set_1direction
  (JNIEnv *env, jobject obj, jlong gpio_addr, jint direction)
  {
	  return libsoc_gpio_set_direction((gpio*)gpio_addr, direction);
  }

/*
 * Class:     com_libsoc_API
 * Method:    libsoc_gpio_get_direction
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_com_libsoc_API_libsoc_1gpio_1get_1direction
  (JNIEnv *env, jobject obj, jlong gpio_addr)
  {
	  return libsoc_gpio_get_direction((gpio*)gpio_addr);
  }

/*
 * Class:     com_libsoc_API
 * Method:    libsoc_gpio_set_level
 * Signature: (JI)I
 */
JNIEXPORT jint JNICALL Java_com_libsoc_API_libsoc_1gpio_1set_1level
  (JNIEnv *env, jobject obj, jlong gpio_addr, jint level)
  {
	  return libsoc_gpio_set_level((gpio*)gpio_addr, level);
  }

/*
 * Class:     com_libsoc_API
 * Method:    libsoc_gpio_get_level
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_com_libsoc_API_libsoc_1gpio_1get_1level
  (JNIEnv *env, jobject obj, jlong gpio_addr)
  {
	  return libsoc_gpio_get_level((gpio*)gpio_addr);
  }

/*
 * Class:     com_libsoc_API
 * Method:    libsoc_gpio_get_edge
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_com_libsoc_API_libsoc_1gpio_1get_1edge
  (JNIEnv *env, jobject obj, jlong gpio_addr)
  {
	  return libsoc_gpio_get_edge((gpio*)gpio_addr);
  }
/*
 * Class:     com_libsoc_API
 * Method:    libsoc_gpio_set_edge
 * Signature: (JI)I
 */
JNIEXPORT jint JNICALL Java_com_libsoc_API_libsoc_1gpio_1set_1edge
  (JNIEnv *env, jobject obj, jlong gpio_addr, jint edge)
  {
	  return libsoc_gpio_set_edge((gpio*)gpio_addr, edge);
  }

/*
 * Class:     com_libsoc_API
 * Method:    libsoc_gpio_poll
 * Signature: (JI)I
 */
JNIEXPORT jint JNICALL Java_com_libsoc_API_libsoc_1gpio_1poll
  (JNIEnv *env, jobject obj, jlong gpio_addr, jint timeout)
  {
	  return libsoc_gpio_poll((gpio*)gpio_addr, timeout);
  }

/*
 * Class:     com_libsoc_API
 * Method:    libsoc_set_debug
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_com_libsoc_API_libsoc_1set_1debug
  (JNIEnv *env, jobject obj, jint enable)
  {
	  libsoc_set_debug(enable);
  }

/*
 * Class:     com_libsoc_API
 * Method:    libsoc_board_init
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_com_libsoc_API_libsoc_1board_1init
  (JNIEnv *env, jobject obj)
  {
	  return (jlong)libsoc_board_init();
  }

/*
 * Class:     com_libsoc_API
 * Method:    libsoc_board_gpio_id
 * Signature: (JLjava/lang/String;)I
 */
JNIEXPORT jint JNICALL Java_com_libsoc_API_libsoc_1board_1gpio_1id
  (JNIEnv *env, jobject obj, jlong config_addr, jstring pin)
  {
	const char* cpin = (*env)->GetStringUTFChars(env, pin, JNI_FALSE);  
	return libsoc_board_gpio_id((board_config*)config_addr, cpin);
  }

/*
 * Class:     com_libsoc_API
 * Method:    libsoc_board_free
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_com_libsoc_API_libsoc_1board_1free
  (JNIEnv *env, jobject obj, jlong config_addr)
  {
	  libsoc_board_free((board_config*)config_addr);
  }
