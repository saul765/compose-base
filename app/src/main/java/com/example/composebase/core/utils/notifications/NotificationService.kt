package com.example.composebase.core.utils.notifications

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.ba.pokedex.utils.notifications.INotificationService
import com.example.composebase.R
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class NotificationService : INotificationService, KoinComponent {

    companion object {
        const val NOTIFICATION_ID = 666
        const val CHANNEL_ID = "pokemon_channel_id"
        const val CHANNEL_NAME = "Pokemon Channel"
        const val CHANNEL_DESCRIPTION = "Pokemon Channel Description"
    }

    val context by inject<Context>()

    init {
        createNotificationChannel()
    }

    @SuppressLint("MissingPermission")
    override fun showNotification(title: String, message: String) {

        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_pokeball_notification)
            .setContentTitle(title)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
        with(NotificationManagerCompat.from(context)) {
            notify(NOTIFICATION_ID, builder.build())
        }
    }

    private fun createNotificationChannel() {

        val channel = NotificationChannel(
            CHANNEL_ID,
            CHANNEL_NAME,
            NotificationManager.IMPORTANCE_HIGH
        ).apply {
            description = CHANNEL_DESCRIPTION
        }
        val notificationManager: NotificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}