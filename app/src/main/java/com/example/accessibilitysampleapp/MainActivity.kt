package com.example.accessibilitysampleapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings.ACTION_ACCESSIBILITY_SETTINGS
import android.content.Intent
import android.content.DialogInterface
import android.accessibilityservice.AccessibilityServiceInfo
import android.content.Context
import androidx.core.view.accessibility.AccessibilityManagerCompat.getEnabledAccessibilityServiceList
import android.content.Context.ACCESSIBILITY_SERVICE
import android.provider.Settings
import androidx.core.content.ContextCompat.getSystemService
import android.view.accessibility.AccessibilityManager
import androidx.appcompat.app.AlertDialog


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 접근성 권한이 없으면 접근성 권한 설정하는 다이얼로그 띄워주는 부분
        if (!checkAccessibilityPermissions()) {
            setAccessibilityPermissions()
        }
    }

    // 접근성 권한이 있는지 없는지 확인하는 부분
    // 있으면 true, 없으면 false
    fun checkAccessibilityPermissions(): Boolean {
        val accessibilityManager = getSystemService(Context.ACCESSIBILITY_SERVICE) as AccessibilityManager

        // getEnabledAccessibilityServiceList는 현재 접근성 권한을 가진 리스트를 가져오게 된다
        val list = accessibilityManager.getEnabledAccessibilityServiceList(AccessibilityServiceInfo.DEFAULT)

        for (i in list.indices) {
            val info = list[i]

            // 접근성 권한을 가진 앱의 패키지 네임과 패키지 네임이 같으면 현재앱이 접근성 권한을 가지고 있다고 판단함
            if (info.resolveInfo.serviceInfo.packageName == application.packageName) {
                return true
            }
        }
        return false
    }

    // 접근성 설정화면으로 넘겨주는 부분
    fun setAccessibilityPermissions() {
        val gsDialog = AlertDialog.Builder(this)
        gsDialog.setTitle("접근성 권한 설정")
        gsDialog.setMessage("접근성 권한을 필요로 합니다")
        gsDialog.setPositiveButton("확인", DialogInterface.OnClickListener { dialog, which ->
            // 설정화면으로 보내는 부분
            startActivity(Intent(ACTION_ACCESSIBILITY_SETTINGS))
            return@OnClickListener
        }).create().show()
    }

}
