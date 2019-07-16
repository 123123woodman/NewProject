#include <jni.h>

JNIEXPORT jstring JNICALL Java_wangjie_com_newproject_application_NativeMethod_fuck
  (JNIEnv *env, jobject obj){
     return (*env)->NewStringUTF(env,"fuck");
  }