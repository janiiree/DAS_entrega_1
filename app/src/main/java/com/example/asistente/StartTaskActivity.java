package com.example.asistente;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.DialogFragment;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import java.util.Random;

public class StartTaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_task);
    }

    public void onClickFinish(View view) {
        //Intent i = new Intent(this, MainActivity.class)
        notificador();
        finish();
    }

    public void onClickScreen(View view) {
        DialogFragment touchScreenDialog = new TouchScreenDialog();
        touchScreenDialog.show(getSupportFragmentManager(), "etiqueta");
    }

    private void notificador() {
        //Método creado para crear notificaciones de rendición
        NotificationManager elManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder elBuilder = new NotificationCompat.Builder(this, "IdCanal");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel elCanal = new NotificationChannel("IdCanal", "NombreCanal",
                    NotificationManager.IMPORTANCE_DEFAULT);
            elManager.createNotificationChannel(elCanal);
        }
        //Creamos un intent para cerrar la actividad en la que estamos y esto le llegara al receiver
        Intent i = new Intent("android.intent.CLOSE_ACTIVITY");
        PendingIntent intentEnNot = PendingIntent.getBroadcast(this, 0, i, 0);
        elBuilder.setSmallIcon(android.R.drawable.star_big_on)
                .setContentIntent(intentEnNot)
                .setContentTitle(getResources().getString(R.string.app_name))
                .setContentText(getResources().getString(R.string.finish_congrats))
                .setVibrate(new long[]{0, 1000, 500, 1000})
                .setAutoCancel(true);
        elManager.notify(1, elBuilder.build());
    }
}