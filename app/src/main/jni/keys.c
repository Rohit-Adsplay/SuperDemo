#include <jni.h>
#include <stdbool.h>

char *liveBaseUrl = "liveurl";
char *testBaseUrl = "testurl";

JNIEXPORT jstring JNICALL
Java_com_quadrish_flexline_core_networkutils_HiltModules_GetToken(JNIEnv *env, jobject thiz) {
    return (*env)->NewStringUTF(env, "d411104a29d4bb908aa7dcc7f2599ba0");
}

JNIEXPORT jstring JNICALL
Java_com_quadrish_flexline_core_networkutils_HiltModules_GetLiveBaseUrl(JNIEnv *env, jobject thiz) {
    return (*env)->NewStringUTF(env, liveBaseUrl);
}

JNIEXPORT jstring JNICALL
Java_com_quadrish_flexline_core_networkutils_HiltModules_GetTestBaseUrl(JNIEnv *env, jobject thiz) {
    return (*env)->NewStringUTF(env, testBaseUrl);
}