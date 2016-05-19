package br.great.jogopervasivo.actvititesDoJogo.activitiesFortalezaOculta;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import br.great.jogopervasivo.beans.Jogo;
import br.ufc.great.arviewer.android.R;

public class MenuCaminhadas extends Activity {



    private LinearLayout botaoCaminhadaBode, botaoCaminhadaCalungueira, botaoCaminhadaGatoPingado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_caminhadas);
        iniciarComponentes();
    }

    private void iniciarComponentes() {
        //Inicializacao
        botaoCaminhadaBode = (LinearLayout) findViewById(R.id.menu_caminhadas_botao_bode);
        botaoCaminhadaCalungueira = (LinearLayout) findViewById(R.id.menu_caminhadas_botao_calungueira);
        botaoCaminhadaGatoPingado = (LinearLayout) findViewById(R.id.menu_caminhadas_botao_gato);

        //Ação
        botaoCaminhadaGatoPingado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarAlerta();
            }
        });
        botaoCaminhadaGatoPingado.setOnTouchListener(new EfeitoClique(this));

        botaoCaminhadaCalungueira.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarAlerta();
            }
        });
        botaoCaminhadaCalungueira.setOnTouchListener(new EfeitoClique(this));

        botaoCaminhadaBode.setOnTouchListener(new EfeitoClique(this));
        botaoCaminhadaBode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PreparaJogo preparaJogo =  new PreparaJogo(MenuCaminhadas.this, Jogo.CAMINHADA_BODE);
                preparaJogo.execute();
            }
        });

    }

    private void mostrarAlerta() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.app_name);
        builder.setMessage(R.string.em_construcao);
        builder.setNegativeButton(R.string.OK, null);
        builder.create().show();
    }
}
