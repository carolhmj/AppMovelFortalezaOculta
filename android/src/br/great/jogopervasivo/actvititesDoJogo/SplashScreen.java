package br.great.jogopervasivo.actvititesDoJogo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;

import br.great.jogopervasivo.util.SobrescreverFonte;
import br.ufc.great.arviewer.android.R;

public class SplashScreen extends Activity {

    private ImageView animacaoImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        SobrescreverFonte.setDefaultFont(this, "SANS_SERIF", "fonts/GeosansLight.ttf");
        iniciarComponentes();
    }

    private void iniciarComponentes() {
        animacaoImageView = (ImageView) findViewById(R.id.animacao_image_view);
        animacaoImageView.setBackgroundResource(R.drawable.animacao_entrada);

        AnimationDrawable animationDrawable = (AnimationDrawable) animacaoImageView.getBackground();
        animationDrawable.start();


    }

    @Override
    protected void onResume() {
        super.onResume();

        /**
         * Isso só esta aqui pra animação aparecer por um tempo*/
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                startActivity(new Intent(SplashScreen.this, MenuJogos.class));
                finish();
            }
        }.execute();
    }
}
