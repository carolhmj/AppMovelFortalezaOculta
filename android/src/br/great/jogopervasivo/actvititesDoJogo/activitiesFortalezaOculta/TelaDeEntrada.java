package br.great.jogopervasivo.actvititesDoJogo.activitiesFortalezaOculta;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;

import br.great.jogopervasivo.util.SobrescreverFonte;
import br.ufc.great.arviewer.android.R;


public class TelaDeEntrada extends Activity {

    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_de_entrada);
        initComponents();
    }

    private void initComponents() {
        imageView = (ImageView) findViewById(R.id.animacao_imageView);
        imageView.setBackgroundResource(R.drawable.animacao_entrada);

        AnimationDrawable animationDrawable = (AnimationDrawable) imageView.getBackground();
        animationDrawable.start();

        SobrescreverFonte.setDefaultFont(this, "DEFAULT", "fonts/GeosansLight.ttf");
    }

    @Override
    protected void onResume() {
        super.onResume();
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                startActivity(new Intent(TelaDeEntrada.this,MenuJogos.class));
                finish();
            }
        }.execute();
    }


}
