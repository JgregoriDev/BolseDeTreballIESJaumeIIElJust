package com.lpc.bolsedetreballiesjaumeiieljust;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.util.ArrayMap;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.lpc.bolsedetreballiesjaumeiieljust.Entitat.OfertesTreball;

/**
 * Created by lpc on 2/05/17.
 */

public class MyAndroidFirebaseMsgService extends FirebaseMessagingService {
    ArrayMap<String, String> campsMissatge;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        campsMissatge = new ArrayMap<>();
        campsMissatge = (ArrayMap<String, String>) remoteMessage.getData();
        /*if(remoteMessage.getData() != null) {
            Log.d("hola", "DATOS RECIBIDOS");
            Log.d("hola", "Usuario: " + remoteMessage.getData().get("nom"));
            Log.d("hola", "Estado: " + remoteMessage.getData().get("cognom"));
        }*/
    }

    private void createNotification(String messageBody) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent resultIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
        Uri notificationSoundURI = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder mNotificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Android Tutorial Point FCM Tutorial")
                .setContentText(messageBody)
                .setAutoCancel(true)
                .setSound(notificationSoundURI)
                .setContentIntent(resultIntent);
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, mNotificationBuilder.build());


    }

}
