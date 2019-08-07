#include <jni.h>
#include <com_carter_javaAndroid_jni_JNITest.h>
#include <string.h>
/* Header for class com_carter_javaAndroid_jni_JNITest */

#ifndef _Included_com_carter_javaAndroid_jni_JNITest
#define _Included_com_carter_javaAndroid_jni_JNITest

/*
 * Class:     com_carter_javaAndroid_jni_JNITest
 * Method:    getJNIName
 * Signature: ()Ljava/lang/String;
 */
extern "C" JNIEXPORT jstring JNICALL Java_com_carter_javaAndroid_jni_JNITest_getJNIName
  (JNIEnv *env, jclass jc){

    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
  }


#endif
