#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring JNICALL
Java_com_lovephotos_collageeditor_stpnfncmakads_1tom_activity_1tom_TommSplashActivity_getBaseURL(JNIEnv *env, jobject thiz) {
    return env->NewStringUTF("https://Test");
}
