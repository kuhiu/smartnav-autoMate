//#include <string.h>
//#include <stdint.h>
//#include <jni.h>
//#include <android/log.h>
//#include <android/native_window.h>
//#include <android/native_window_jni.h>
//#include <gst/gst.h>
//#include <gst/video/video.h>
//#include <pthread.h>
//
//GST_DEBUG_CATEGORY_STATIC (debug_category);
//#define GST_CAT_DEFAULT debug_category
//
///*
// * These macros provide a way to store the native pointer to CustomData, which might be 32 or 64 bits, into
// * a jlong, which is always 64 bits, without warnings.
// */
//#if GLIB_SIZEOF_VOID_P == 8
//# define GET_CUSTOM_DATA(env, thiz, fieldID) (CustomData *)(*env)->GetLongField (env, thiz, fieldID)
//# define SET_CUSTOM_DATA(env, thiz, fieldID, data) (*env)->SetLongField (env, thiz, fieldID, (jlong)data)
//#else
//# define GET_CUSTOM_DATA(env, thiz, fieldID) (CustomData *)(jint)(*env)->GetLongField (env, thiz, fieldID)
//# define SET_CUSTOM_DATA(env, thiz, fieldID, data) (*env)->SetLongField (env, thiz, fieldID, (jlong)(jint)data)
//#endif
//
///* Structure to contain all our information, so we can pass it to callbacks */
//typedef struct _CustomData
//{
//    jobject app;                  /* Application instance, used to call its methods. A global reference is kept. */
//    GstElement *pipeline;         /* The running pipeline */
//    GMainContext *context;        /* GLib context used to run the main loop */
//    GMainLoop *main_loop;         /* GLib main loop */
//    gboolean initialized;         /* To avoid informing the UI multiple times about the initialization */
//    GstElement *video_sink;       /* The video sink element which receives XOverlay commands */
//    ANativeWindow *native_window; /* The Android native window where video will be rendered */
//} CustomData;
//
///* These global variables cache values which are not changing during execution */
//static pthread_t gst_app_thread;
//static pthread_key_t current_jni_env;
//static JavaVM *java_vm;
//static jfieldID custom_data_field_id;
//static jmethodID set_message_method_id;
//static jmethodID on_gstreamer_initialized_method_id;
//
//
//extern "C"
//JNIEXPORT void JNICALL Java_Fragments_Fragment2_nativeInit(JNIEnv *env, jobject thiz) {
//    // TODO: implement nativeInit()
//}
//extern "C"
//JNIEXPORT void JNICALL Java_Fragments_Fragment2_nativeFinalize(JNIEnv * env, jobject thiz) {
//    CustomData *data = GET_CUSTOM_DATA (env, thiz, custom_data_field_id);
//    if (!data)
//        return;
//    GST_DEBUG ("Releasing Native Window %p", data->native_window);
//
//    if (data->video_sink) {
//        gst_video_overlay_set_window_handle (GST_VIDEO_OVERLAY (data->video_sink),
//                                             (guintptr) NULL);
//        gst_element_set_state (data->pipeline, GST_STATE_READY);
//    }
//
//    ANativeWindow_release (data->native_window);
//    data->native_window = NULL;
//    data->initialized = FALSE;
//}
//
//extern "C"
//JNIEXPORT void JNICALL
//Java_Fragments_Fragment2_nativePlay(JNIEnv *env, jobject thiz) {
//    // TODO: implement nativePlay()
//}
//extern "C"
//JNIEXPORT void JNICALL
//Java_Fragments_Fragment2_nativePause(JNIEnv *env, jobject thiz) {
//    // TODO: implement nativePause()
//}
//extern "C"
//JNIEXPORT jboolean JNICALL
//Java_Fragments_Fragment2_nativeClassInit(JNIEnv *env, jclass clazz) {
//    // TODO: implement nativeClassInit()
//}
//extern "C"
//JNIEXPORT void JNICALL
//Java_Fragments_Fragment2_nativeSurfaceInit(JNIEnv *env, jobject thiz, jobject surface) {
//    // TODO: implement nativeSurfaceInit()
//}
//extern "C"
//JNIEXPORT void JNICALL
//Java_Fragments_Fragment2_nativeSurfaceFinalize(JNIEnv *env, jobject thiz) {
//    // TODO: implement nativeSurfaceFinalize()
//}
//
