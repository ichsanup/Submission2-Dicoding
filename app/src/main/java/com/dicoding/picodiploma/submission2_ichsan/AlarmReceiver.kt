package com.dicoding.picodiploma.submission2_ichsan

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.Toast
import androidx.core.app.NotificationCompat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class AlarmReceiver : BroadcastReceiver() {
    companion object{
        private const val CHANNEL_ID = "channel_id"
        private const val CHANNEL_NAME = "Alarm Reminder"
        private const val NOTIFICATION_ID = 1
        private const val TIME_FORMAT = "HH:mm"
        const val EXTRA_MESSAGE = "extra_message"
        const val EXTRA_TYPE = "extra_type"
        private const val REPEATING_ID = 101
    }

    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
       sendNotification(context)
    }

    private fun sendNotification(context: Context) {
        val intent = context?.packageManager.getLaunchIntentForPackage("com.dicoding.picodiploma.submission2_ichsan")
        val pendingIntent = PendingIntent.getActivity(context, 0, intent, 0)

        val notificationManager = context?.getSystemService(Context.NOTIFICATION_SERVICE)as NotificationManager
        val builder = NotificationCompat.Builder(context, CHANNEL_ID )
            .setContentIntent(pendingIntent)
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle(context.resources.getString((R.string.content_title)))
            .setContentText("Pengingat")
            .setAutoCancel(true)

        //untuk support os diatas android O
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT)

            builder.setChannelId(CHANNEL_ID)
            notificationManager.createNotificationChannel(channel)
        }
        //untuk diluar version
        val notification = builder.build()
        notificationManager.notify(NOTIFICATION_ID, notification)
    }
    //membuat function untuk repeat alarm
    fun setRepeatAlarm(context: Context, type: String, time:String, message:String){
        if (isDateInvalid(time, TIME_FORMAT))return
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, AlarmReceiver::class.java)
        intent.putExtra(EXTRA_MESSAGE, message)
        intent.putExtra(EXTRA_TYPE, type)
        val timeArray = time.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val calender = Calendar.getInstance()
        calender.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timeArray[0]))
        calender.set(Calendar.MINUTE, Integer.parseInt(timeArray[1]))
        calender.set(Calendar.SECOND, 0)
        val pendingIntent = PendingIntent.getBroadcast(context, REPEATING_ID, intent, 0)
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calender.timeInMillis, AlarmManager.INTERVAL_DAY, pendingIntent)
        Toast.makeText(context, "REPEATING ALARAM SET UP", Toast.LENGTH_SHORT).show()

    }

    private fun isDateInvalid(time: String, timeFormat: String): Boolean {

        return try {
            val df = SimpleDateFormat(timeFormat, Locale.getDefault())
            df.isLenient = false
            df.parse(time)
            false
        }catch (e: ParseException){
            true
        }
    }
    fun cancelAlarm(context: Context){
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, AlarmReceiver::class.java)
        val requestCode = REPEATING_ID
        val pendingIntent = PendingIntent.getBroadcast(context, requestCode, intent, 0 )
        pendingIntent.cancel()
        alarmManager.cancel(pendingIntent)
        Toast.makeText(context, "CANCEL ALARAM SET UP", Toast.LENGTH_SHORT).show()
    }
}