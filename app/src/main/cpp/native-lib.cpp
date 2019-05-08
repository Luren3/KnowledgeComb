#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring JNICALL
Java_com_magicfrost_knowledgecomb_MainActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}
extern "C"
JNIEXPORT void JNICALL
Java_com_magicfrost_knowledgecomb_hotfix_DxManager_replace(JNIEnv *env, jobject instance, jobject wrongMethod,jobject method) {

    // TODO

}