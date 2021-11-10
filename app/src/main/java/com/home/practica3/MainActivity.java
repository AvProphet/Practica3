package com.home.practica3;

import static android.media.MediaPlayer.*;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageButton leftButton;
    ImageButton rightButton;
    ImageButton play;
    ImageView musicImage;
    MediaPlayer mediaPlayer;

    String actual = "punkyfer";
    /*
        El evento OnPrepared se lanzaría una vez, cuando el mp se encuentra listo para
        reproducir audio.
        En nuestro caso, como se obtiene el audio por medios locales, no hace falta caputar este evento.
        Si tuvieramos que obtener las canciones de la SD o por Streaming, entonces si nos interesaría c
        capturar este evento.

    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        leftButton = findViewById(R.id.izq);
        rightButton = findViewById(R.id.dcha);
        play = findViewById(R.id.play);
        musicImage = findViewById(R.id.foto);
        musicImage.setScaleType(ImageView.ScaleType.CENTER_INSIDE);

        leftButton.setOnClickListener(leftEvent -> changeMusic());
        rightButton.setOnClickListener(rightEvent -> changeMusic());
        play.setOnClickListener(playEvent -> stopMusic());

        // Asignamos la canción a sonar
        mediaPlayer = create(this, R.raw.punkyfer);
        // Establecemos el volumen. Puede ser que por edefcto el volumen sea muy bajo
        mediaPlayer.setVolume(0.5f, 0.5f);
        // Empezamos la canción
        mediaPlayer.start();
        mediaPlayer.setOnPreparedListener(MediaPlayer::start);

        // mediaPlayer.setOnPreparedListener(funcionPrepared)

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) mediaPlayer.release();
    }

    public void changeMusic() {
        System.out.println(actual);
        if (actual.equalsIgnoreCase("punkyfer")) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = create(this, R.raw.no_somos_nada);
            mediaPlayer.start();
            musicImage.setImageResource(R.drawable.nsn);
            actual = "nsn";
        } else {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = create(this, R.raw.punkyfer);
            mediaPlayer.start();
            actual = "punkyfer";
            musicImage.setImageResource(R.drawable.elueldlp);
        }

    }

    public void stopMusic() {
        if (!mediaPlayer.isPlaying()) {
            mediaPlayer.start();
        } else {
            mediaPlayer.pause();
        }
    }
}