package com.example.myapplicationddddd

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.widget.Button
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private lateinit var permissionManager: PermissionManager

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


     /*   val recycler_view = findViewById<Button>(R.id.baba)

        //define textview

        recycler_view.setOnClickListener {
            if (!Settings.canDrawOverlays(this)) {
                val intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:$packageName"))
                startActivityForResult(intent, 1)
            } else {
                Toast.makeText(this, "İzin zaten verilmiş", Toast.LENGTH_SHORT).show()

            }
        }*/

        permissionManager = PermissionManager(this)

        val recycler_view = findViewById<Button>(R.id.baba)
        recycler_view.setOnClickListener {
            permissionManager.checkDrawOverlayPermission()
        }
    }



    @RequiresApi(Build.VERSION_CODES.M)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        permissionManager.onPermissionResult(requestCode, resultCode, data)
    }


}


