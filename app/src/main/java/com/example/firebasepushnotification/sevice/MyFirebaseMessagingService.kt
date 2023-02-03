package com.example.firebasepushnotification.sevice

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.firebasepushnotification.MainActivity
import com.example.firebasepushnotification.R

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

internal class MyFirebaseMessagingService : FirebaseMessagingService() {
    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        /* if (message.getData().size() > 0) {
            String title = message.getData().get("title");
            String body = message.getData().get("body");
            showNotification(getApplicationContext(), title, body);
        } else {*/
        Log.e(TAG, "onMessageReceived: title:==="+ message.notification!!.title )
        val title = message.notification!!.title
        val body = message.notification!!.body
        showNotification(applicationContext, title, body)
        /*  }*/
    }



    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.e("TAG", "New  Token: $token")
    }

    var NOTIFICATION_CHANNEL_ID = "MyNotificationId"
    var NOTIFICATION_ID = 100
    private val TAG = "FireBaseMessagingService"
    @SuppressLint("MissingPermission")
    private fun showNotification(applicationContext: Context, title: String?, body: String?) {
        val intent: Intent
        Log.e(TAG, "showNotification: ShowNotification" )
        Toast.makeText(applicationContext,"shoenotification",Toast.LENGTH_SHORT).show()
        intent = Intent(applicationContext, MainActivity::class.java)
        intent.data = Uri.parse("custom://" + System.currentTimeMillis())
        intent.action = "actionstring" + System.currentTimeMillis()
        intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP
        val pi = PendingIntent.getActivities(
            applicationContext,
            0,
            arrayOf(intent),
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        val notification: Notification
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Log.e(TAG, "showNotification: ShowNotification greter then  O" )
            Toast.makeText(applicationContext,"shoenotification - greter then  O",Toast.LENGTH_SHORT).show()

            notification = NotificationCompat.Builder(applicationContext, NOTIFICATION_CHANNEL_ID)
                .setOngoing(true)
                .setSmallIcon(notificationIcon)
                .setContentText(body)
                .setAutoCancel(false)
                .setContentIntent(pi)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(Notification.CATEGORY_SERVICE)
                .setWhen(System.currentTimeMillis())
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setContentTitle(title).build()
            val manager = NotificationManagerCompat.from(this)
            manager.notify(NOTIFICATION_ID, notification)

            /*NotificationManager notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, title, NotificationManager.IMPORTANCE_DEFAULT);

            notificationManager.createNotificationChannel(notificationChannel);
            notificationManager.notify(NOTIFICATION_ID, notification);*/
        } else {
            Log.e(TAG, "showNotification: ShowNotification less then  O" )
            Toast.makeText(applicationContext,"shoenotification- less then  O",Toast.LENGTH_SHORT).show()

            notification = NotificationCompat.Builder(getApplicationContext())
                .setSmallIcon(notificationIcon)
                .setAutoCancel(true)
                .setContentText(body)
                .setContentIntent(pi)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setContentTitle(title).build()
            val notificationManager = getApplicationContext().getSystemService(
                NOTIFICATION_SERVICE
            ) as NotificationManager
            notificationManager.notify(NOTIFICATION_ID, notification)
        }
    }

    @get:SuppressLint("UseCompatLoadingForDrawables")
    private val notificationIcon: Int
        private get() {
            val useWhiteIcon = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP
            val value: Int
            value = if (useWhiteIcon) R.mipmap.ic_launcher else R.mipmap.ic_launcher
            return value
        }
}