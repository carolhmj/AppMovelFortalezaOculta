package br.great.jogopervasivo.actvititesDoJogo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ImageView;

import br.great.jogopervasivo.util.SobrescreverFonte;
import br.ufc.great.arviewer.android.R;

public class SplashScreen extends Activity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SobrescreverFonte.setDefaultFont(this, "SANS_SERIF", "fonts/GeosansLight.ttf");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        iniciarComponentes();
    }

    private void iniciarComponentes() {
        webView = (WebView) findViewById(R.id.webViewAnimacao);
        webView.loadUrl("file:///android_asset/htmls/animacao.html");
        webView.setSaveEnabled(false);
        webView.setBackgroundColor(Color.TRANSPARENT);
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
                    Thread.sleep( 2 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                startActivity(new Intent(SplashScreen.this, LoginActivity.class));
                finish();
            }
        }.execute();
    }
}
