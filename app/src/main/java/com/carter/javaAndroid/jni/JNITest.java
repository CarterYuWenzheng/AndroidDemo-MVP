package com.carter.javaAndroid.jni;

public class JNITest {

    public static native String getJNIName();

    static {
        System.loadLibrary("JNITest");
    }
}
