package br.great.jogopervasivo.actvititesDoJogo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import br.great.jogopervasivo.util.EfeitoClique;
import br.ufc.great.arviewer.android.R;


public class MenuJogos extends Activity {
    private LinearLayout botaoCaminhada;
    private LinearLayout botaoCooperativo;
    private LinearLayout outro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_jogos);
        iniciarComponentes();
    }

    private void iniciarComponentes() {
        //Primeiro Botão
        botaoCaminhada = (LinearLayout) findViewById(R.id.menu_jogos_botao_caminhada);
        botaoCaminhada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuJogos.this, MenuCaminhadas.class));
            }
        });
        botaoCaminhada.setOnTouchListener(new EfeitoClique(this));

        //Segundo botão
        botaoCooperativo = (LinearLayout) findViewById(R.id.menu_jogos_botao_cooperativo);
        botaoCooperativo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarAlerta();
            }
        });
        botaoCooperativo.setOnTouchListener(new EfeitoClique(this));

        //Terceiro Botão
        outro = (LinearLayout) findViewById(R.id.menu_jogos_botao_outro);
        outro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarAlerta();
            }
        });
        outro.setOnTouchListener(new EfeitoClique(this));
    }

    private void mostrarAlerta() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MenuJogos.this);
        builder.setTitle(R.string.app_name);
        builder.setMessage(R.string.em_construcao);
        builder.setNegativeButton(R.string.OK, null);
        builder.create().show();
    }

    public void abrirMapa(View view){
        startActivity(new Intent(this,Mapa.class));
    }
}
