/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class com_libsoc_API */

#ifndef _Included_com_libsoc_API
#define _Included_com_libsoc_API
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     com_libsoc_API
 * Method:    defineConstants
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_com_libsoc_API_defineConstants
  (JNIEnv *, jclass);

/*
 * Class:     com_libsoc_API
 * Method:    libsoc_gpio_request
 * Signature: (II)J
 */
JNIEXPORT jlong JNICALL Java_com_libsoc_API_libsoc_1gpio_1request
  (JNIEnv *, jobject, jint, jint);

/*
 * Class:     com_libsoc_API
 * Method:    libsoc_gpio_free
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_com_libsoc_API_libsoc_1gpio_1free
  (JNIEnv *, jobject, jlong);

/*
 * Class:     com_libsoc_API
 * Method:    libsoc_gpio_set_direction
 * Signature: (JI)I
 */
JNIEXPORT jint JNICALL Java_com_libsoc_API_libsoc_1gpio_1set_1direction
  (JNIEnv *, jobject, jlong, jint);

/*
 * Class:     com_libsoc_API
 * Method:    libsoc_gpio_get_direction
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_com_libsoc_API_libsoc_1gpio_1get_1direction
  (JNIEnv *, jobject, jlong);

/*
 * Class:     com_libsoc_API
 * Method:    libsoc_gpio_set_level
 * Signature: (JI)I
 */
JNIEXPORT jint JNICALL Java_com_libsoc_API_libsoc_1gpio_1set_1level
  (JNIEnv *, jobject, jlong, jint);

/*
 * Class:     com_libsoc_API
 * Method:    libsoc_gpio_get_level
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_com_libsoc_API_libsoc_1gpio_1get_1level
  (JNIEnv *, jobject, jlong);

/*
 * Class:     com_libsoc_API
 * Method:    libsoc_gpio_get_edge
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_com_libsoc_API_libsoc_1gpio_1get_1edge
  (JNIEnv *, jobject, jlong);

/*
 * Class:     com_libsoc_API
 * Method:    libsoc_gpio_set_edge
 * Signature: (JI)I
 */
JNIEXPORT jint JNICALL Java_com_libsoc_API_libsoc_1gpio_1set_1edge
  (JNIEnv *, jobject, jlong, jint);

/*
 * Class:     com_libsoc_API
 * Method:    libsoc_set_debug
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_com_libsoc_API_libsoc_1set_1debug
  (JNIEnv *, jobject, jint);

/*
 * Class:     com_libsoc_API
 * Method:    libsoc_gpio_poll
 * Signature: (JI)I
 */
JNIEXPORT jint JNICALL Java_com_libsoc_API_libsoc_1gpio_1poll
  (JNIEnv *, jobject, jlong, jint);

/*
 * Class:     com_libsoc_API
 * Method:    libsoc_board_init
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_com_libsoc_API_libsoc_1board_1init
  (JNIEnv *, jobject);

/*
 * Class:     com_libsoc_API
 * Method:    libsoc_board_gpio_id
 * Signature: (JLjava/lang/String;)I
 */
JNIEXPORT jint JNICALL Java_com_libsoc_API_libsoc_1board_1gpio_1id
  (JNIEnv *, jobject, jlong, jstring);

/*
 * Class:     com_libsoc_API
 * Method:    libsoc_board_free
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_com_libsoc_API_libsoc_1board_1free
  (JNIEnv *, jobject, jlong);

#ifdef __cplusplus
}
#endif
#endif
