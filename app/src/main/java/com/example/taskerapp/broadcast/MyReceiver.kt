package com.example.taskerapp.broadcast

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.example.taskerapp.MainActivity
import com.example.taskerapp.R
import com.example.taskerapp.SecondActivity
import com.example.taskerapp.db.AppDatabase

class MyReceiver : BroadcastReceiver() {
    private lateinit var appDatabase: AppDatabase
    override fun onReceive(context: Context, intent: Intent) {
        var id = intent!!.getIntExtra("id", 1)
        val intent = Intent(context, SecondActivity::class.java)
        intent.putExtra("id", id)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        appDatabase = AppDatabase.getInstance(context)
//appDatabase.serviceDao().getAllListName()
//        val taskByID = appDatabase.taskDao().getTaskByID(id)
//        val boxByID = appDatabase.boxDao().getBoxByID(taskByID.task_box_id)
//        val title = boxByID.box_name
//        val body = taskByID.task_name

        val pendingIntent =
            PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        val CHANNEL_ID = "com.example.todoapp"
        val CHANNEL_NAME = "todoapp"

        val notificationManager =
            context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setContentIntent(pendingIntent)
            .setSmallIcon(R.drawable.ic_alarm)
            .setContentTitle("title")
            .setContentText("body")
            .setAutoCancel(true)

        val notification = builder.build()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, importance)
            channel.description = "dec"
            notificationManager.createNotificationChannel(channel)
        }
        notificationManager.notify(1, notification)
    }
}