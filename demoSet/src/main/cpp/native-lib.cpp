#include <jni.h>
#include <string>



extern "C"
JNIEXPORT jint JNICALL
Java_com_example_crxc_jnidemo_MainActivity_get(JNIEnv *env, jobject instance) {
    jclass clazz=env->GetObjectClass(instance);
//    jfieldID field=env->GetFieldID(clazz,"i","I");
    char cs[]="zqb";
    jstring jstring1=env->NewStringUTF(cs);
//    env->SetIntField(instance,field,123);
    jmethodID method=env->GetMethodID(clazz,"set","(Ljava/lang/String;)V");
    (env)->CallVoidMethod(instance,method,jstring1);
    return 1 ;
    // TODO
}



