package com.example.studente.appcucinaproject.Timer;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.AudioManager;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.studente.appcucinaproject.R;

public class TimerOverActivity extends AppCompatActivity {

    private Button stopButton;
    private Uri mySuond;
    private Intent myIntent, risposta;
    private PendingIntent pIntent;
    private Notification myNoti;
    private NotificationManager myNotificationManager;
    private AudioManager myAudioManager;
    private Ringtone myRingtone;
    private Uri myUriNotification;
    private int volume_level;

    private TextView nomeRicettaText;

    private String nomeRicettaFromIntent = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer_over);
        //ROTAZIONE SCHERMO BLOCCATA
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //ACCENDI SCHERMO QUANDO BLOCCATO
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON |
                    WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD |
                    WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED |
                    WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);

        stopButton = (Button) findViewById(R.id.buttonTermina);

        nomeRicettaText = (TextView) findViewById(R.id.textView5);

        myAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        volume_level = myAudioManager.getStreamVolume(AudioManager.STREAM_RING);
        //myAudioManager.setStreamVolume(AudioManager.STREAM_RING, myAudioManager.getStreamMaxVolume(AudioManager.STREAM_RING),0);
        myAudioManager.setStreamVolume(AudioManager.STREAM_RING,0,0);

        mySuond = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
        //myIntent = new Intent(this.getApplicationContext(),TimeOverActivity.class);
        myIntent = new Intent();
        pIntent = PendingIntent.getActivity(getApplicationContext(), 0, myIntent, 0);  //this = context

        myNoti = new Notification.Builder(this)
                    .setTicker("TIME OVER")
                    .setContentTitle("TIME OVER")
                    .setContentText("00:00:00")
                    .setSmallIcon(R.drawable.ic_timer)
                    .setContentIntent(pIntent).getNotification();

        myNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        myNotificationManager.notify(0, myNoti);

        //SETTO IL NOME DELLA RICETTA
        nomeRicettaFromIntent = getIntent().getStringExtra("name");
        nomeRicettaText.setText(nomeRicettaFromIntent);

        try {
            myUriNotification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
            myRingtone = RingtoneManager.getRingtone(getApplicationContext(), myUriNotification);
            myRingtone.play();
        } catch (Exception e) {
            e.printStackTrace();
        }

         stopButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {/*
                risposta.putExtra(getPackageName(), "fatto");
                //Restituisco il codice identificativo dell'activity corrente e la risposta
                setResult(123, risposta);
                finish();*/

                 onBackPressed();
             }
         });
    }

    @Override
    public void onBackPressed() {
        risposta = new Intent();
        if(myNoti != null) {
            myNotificationManager.cancelAll();
            myRingtone.stop();
            myAudioManager.setStreamVolume(
                    AudioManager.STREAM_RING,
                    volume_level,
                    0);
        }

        risposta.putExtra(getPackageName(), "fatto");
        setResult(123, risposta);

        finish();
    }
}
