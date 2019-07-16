#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring JNICALL
Java_wangjie_com_newproject_application_NativeMethod_fuck(
        JNIEnv* env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}
