package com.example.accessibilitysampleapp

import android.accessibilityservice.AccessibilityService
import android.view.accessibility.AccessibilityEvent
import android.accessibilityservice.AccessibilityServiceInfo
import android.util.Log

class SampleAccessibilityService : AccessibilityService() {

    private val TAG = "AccessibilityService"

    override fun onAccessibilityEvent(event: AccessibilityEvent) {

        Log.e(TAG, "Catch Event Package Name : " + event.packageName);
        Log.e(TAG, "Catch Event TEXT : " + event.text);
        Log.e(TAG, "Catch Event ContentDescription : " + event.contentDescription);
        Log.e(TAG, "Catch Event getSource : " + event.source);
        Log.e(TAG, "=========================================================================");
    }

    override fun onServiceConnected() {
        val info = AccessibilityServiceInfo()

        info.eventTypes = AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED
        info.feedbackType = AccessibilityServiceInfo.DEFAULT
        info.notificationTimeout = 100

        serviceInfo = info
    }

    override fun onInterrupt() {
        Log.e("TEST", "OnInterrupt");
    }

}
