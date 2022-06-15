package com.uzb.permissionsmycontact

import android.Manifest
import android.annotation.SuppressLint
import android.app.PendingIntent
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.telephony.SmsManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.uzb.permissionsmycontact.databinding.ActivityUserBinding


class UserActivity : AppCompatActivity() {
    lateinit var binding: ActivityUserBinding
    val requestCode = 1001

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
    }

    @SuppressLint("UnspecifiedImmutableFlag")
    private fun initViews() {
        val name = intent.getStringExtra("name")
        val mobilNumber = intent.getStringExtra("number")

        binding.apply {
            tvUserName.text = name
            tvUserNumber.text = mobilNumber

            btnSmsSend.setOnClickListener {
                if (ContextCompat.checkSelfPermission(this@UserActivity, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED ){
                    val smsText = etSmsText.text

                    val intent = Intent(applicationContext, MainActivity::class.java)
                    val pi = PendingIntent.getActivity(applicationContext, 0, intent, 0)

                    val sms: SmsManager = SmsManager.getDefault()
                    sms.sendTextMessage(mobilNumber, null, smsText.toString(), pi, null)
                }else{
                    requestSContactPermissiopn()
                }
            }
        }
    }

    private fun requestSContactPermissiopn() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.SEND_SMS)){
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.SEND_SMS), requestCode)
        }else{
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.SEND_SMS), requestCode)
        }
    }
}