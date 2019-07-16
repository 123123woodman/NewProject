LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)

LOCAL_MODULE := NativeSo
LOCAL_LDFLAGS := -Wl,--build-id
LOCAL_SRC_FILES := E:\ijkplayer\NewProject\app\src\main\jni\nativemethod.c

LOCAL_C_INCLUDES += E:\ijkplayer\NewProject\app\src\main\jni
LOCAL_C_INCLUDES += E:\ijkplayer\NewProject\app\src\debug\jni

include $(BUILD_SHARED_LIBRARY)
