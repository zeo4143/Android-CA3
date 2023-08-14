package com.example.ca3

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RemoteViews
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import java.io.IOException

class Interact : AppCompatActivity() {

    lateinit var nm : EditText
    lateinit var course : EditText
    lateinit var section : EditText
    lateinit var reg : Button
    lateinit var notificationManager: NotificationManager
    lateinit var notificationChannel: NotificationChannel
    lateinit var builder: Notification.Builder
    private val channelId = "i.apps.notifications"
    private val description = "Test notification"
    lateinit var data1 : String
    lateinit var data2 : String
    lateinit var data3 : String
    var file = "registration.txt"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_interact)
        nm = findViewById(R.id.ed1)
        course = findViewById(R.id.ed2)
        section = findViewById(R.id.ed3)
        reg = findViewById(R.id.btn)
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        reg.setOnClickListener {
            data1 = nm.text.toString()
            data2 = course.text.toString()
            data3 = section.text.toString()

            if(data1.isEmpty() || data2.isEmpty() || data3.isEmpty()){
                Toast.makeText(this, "Fill all the details !!",Toast.LENGTH_LONG).show()
            }
            else {
                try {
                    val fout = openFileOutput(file, MODE_APPEND)
                    data1 += " ";
                    data2 += "\n";
                    data3 += "\n";
                    fout.write(data1.toByteArray())
                    fout.write(data2.toByteArray())
                    fout.write(data3.toByteArray())
                    fout.close()
                    var intent = Intent(this, afterNotification::class.java)
                    val pendingIntent = PendingIntent.getActivity(
                        this,
                        0,
                        intent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                    )

                    val contentView = RemoteViews(packageName, R.layout.activity_notification)

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        notificationChannel = NotificationChannel(
                            channelId,
                            description,
                            NotificationManager.IMPORTANCE_HIGH
                        )
                        notificationChannel.enableLights(true)
                        notificationChannel.lightColor = Color.GREEN
                        notificationChannel.enableVibration(false)
                        notificationManager.createNotificationChannel(notificationChannel)

                        builder = Notification.Builder(this, channelId)
                            .setContent(contentView)
                            .setSmallIcon(R.drawable.ic_launcher_background)
                            .setLargeIcon(
                                BitmapFactory.decodeResource(
                                    this.resources,
                                    R.drawable.ic_launcher_background
                                )
                            )
                            .setContentIntent(pendingIntent)
                    } else {

                        builder = Notification.Builder(this)
                            .setContent(contentView)
                            .setSmallIcon(R.drawable.ic_launcher_background)
                            .setLargeIcon(
                                BitmapFactory.decodeResource(
                                    this.resources,
                                    R.drawable.ic_launcher_background
                                )
                            )
                            .setContentIntent(pendingIntent)
                    }
                    notificationManager.notify(1234, builder.build())

                    intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }

    }

}