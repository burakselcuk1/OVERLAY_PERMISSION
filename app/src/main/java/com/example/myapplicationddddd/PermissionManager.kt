package com.example.myapplicationddddd

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.widget.Toast
import androidx.annotation.RequiresApi

class PermissionManager(private val activity: Activity) {

    @RequiresApi(Build.VERSION_CODES.M)
    fun checkDrawOverlayPermission() {
        if (!Settings.canDrawOverlays(activity)) {
            val intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:${activity.packageName}"))
            activity.startActivityForResult(intent, 1)
        }else{
            Toast.makeText(activity, "İzin zaten verilmiş", Toast.LENGTH_SHORT).show()
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun onPermissionResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 1 && Settings.canDrawOverlays(activity)) {
            // İzin verildiğinde yapılacaklar
            val intent = Intent(activity, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            activity.startActivity(intent)
        }else{
            Toast.makeText(activity, "Lütfen izin veriniz", Toast.LENGTH_SHORT).show()
        }
    }
}