package com.example.afinal

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.icu.util.Calendar
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.example.afinal.R
import java.util.*
//owo
class Notification: BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent){
        try{
            showNotification(context, "今天也跟著Kiraku一起前進吧！", "完成訊號與系統作業\n買打蛋器\n...and more")
        }catch(ex: Exception){
            Log.d("Receive Ex", "onReceive:${ex.printStackTrace()}")
        }
    }
}


private fun showNotification(context: Context, title: String, desc: String){
    val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    val channelId = "message_channel"
    val channelName = "message_name"

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val channel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT)
        manager.createNotificationChannel(channel)
    }

    val builder = NotificationCompat.Builder(context, channelId)
        .setContentTitle(title)
        .setContentText(desc)
        .setSmallIcon(R.mipmap.ic_launcher)
        .setLargeIcon(BitmapFactory.decodeResource(context.getResources(),
            R.mipmap.ic_launcher))//i need app icon


    manager.notify(1, builder.build())
}