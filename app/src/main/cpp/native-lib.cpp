#include <jni.h>
#include <string>

extern "C"
JNIEXPORT jstring

JNICALL
Java_elmeniawy_eslam_aesencryption_MainActivity_getSecretKey(
        JNIEnv *env,
        jobject /* this */) {
    std::string key = "This should be the key";
    return env->NewStringUTF(key.c_str());
}
