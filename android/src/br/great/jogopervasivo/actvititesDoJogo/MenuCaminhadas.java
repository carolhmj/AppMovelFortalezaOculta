package br.great.jogopervasivo.actvititesDoJogo;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import java.util.List;

import br.great.jogopervasivo.arrayAdapters.ListarMecanicasAdapter;
import br.great.jogopervasivo.beans.Grupo;
import br.great.jogopervasivo.beans.InstanciaDeJogo;
import br.great.jogopervasivo.beans.Jogo;
import br.great.jogopervasivo.util.Armazenamento;
import br.great.jogopervasivo.util.Constantes;
import br.great.jogopervasivo.util.EfeitoClique;
import br.great.jogopervasivo.util.InformacoesTemporarias;
import br.great.jogopervasivo.webServices.CriarNovaInstanciaDeJogo;
import br.great.jogopervasivo.webServices.EscolherEquipe;
import br.great.jogopervasivo.webServices.InicializarJogo;
import br.great.jogopervasivo.webServices.RecuperarInstanciasDeJogos;
import br.great.jogopervasivo.webServices.RecuperarObjetosInventario;
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

                new AsyncTask<Void, Void, Boolean>() {

                    ProgressDialog progressDialog;


                    @Override
                    protected void onPreExecute() {
                        progressDialog = new ProgressDialog(MenuCaminhadas.this);
                        progressDialog.setMessage(getString(R.string.obtendo_informacoes));
                        progressDialog.show();
                        Log.e("MENU CAMINHADA ","Executou onPreExecute");
                    }

                    @Override
                    protected Boolean doInBackground(Void... params) {


                        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
                        String deviceId = telephonyManager.getDeviceId();
                        Log.i("Device ID", deviceId);

                        //primeiro Cria uma instancia de jogo novo
                        CriarNovaInstanciaDeJogo criarNovaInstanciaDeJogo = new CriarNovaInstanciaDeJogo(MenuCaminhadas.this);
                        criarNovaInstanciaDeJogo.criar(Integer.toString(Jogo.CAMINHADA_BODE), deviceId);

                        //Recupera as instancias ja criadas
                        RecuperarInstanciasDeJogos recuperarInstanciasDeJogos = new RecuperarInstanciasDeJogos(MenuCaminhadas.this);
                        List<InstanciaDeJogo> instanciaDeJogoList = recuperarInstanciasDeJogos.recuperar(Jogo.CAMINHADA_BODE, InformacoesTemporarias.idJogador);
                        for (InstanciaDeJogo i : instanciaDeJogoList) {

                            //Recupera instancia com o  mesmo Device ID
                            if (i.getNomeFicticio().equals(deviceId)) {
                                EscolherEquipe escolherEquipe = new EscolherEquipe(MenuCaminhadas.this);
                                List<Grupo> grupos = escolherEquipe.recuperarGrupos(i.getId());
                                InformacoesTemporarias.instanciaDeJogo = i;
                                InformacoesTemporarias.grupo = grupos.get(0);
                                Location localizacao = Armazenamento.resgatarUltimaLocalizacao(MenuCaminhadas.this);
                                InicializarJogo inicializarJogo = new InicializarJogo(MenuCaminhadas.this, InformacoesTemporarias.instanciaDeJogo, InformacoesTemporarias.grupo, localizacao.getLatitude(), localizacao.getLongitude());
                                inicializarJogo.inicializar();
                                Log.i(Constantes.TAG, "Inicializando o jogo");
                                InformacoesTemporarias.jogoAtual = i;
                                InformacoesTemporarias.grupoAtual = grupos.get(0);
                                Armazenamento.salvar(Constantes.JOGO_EXECUTANDO, true, MenuCaminhadas.this);//Diz que tem jogo executando;
                                RecuperarObjetosInventario.recuperar(MenuCaminhadas.this);
                                startActivity(new Intent(MenuCaminhadas.this, Mapa.class));
                                finish(); //Faz casting do Context para activity  e fecha a janela.
                                return true;
                            }
                        }

                        return false;
                    }

                    @Override
                    protected void onPostExecute(Boolean aBoolean) {
                        progressDialog.dismiss();
                        Log.e("MENU CAMINHADA ","Executou onPostExecute");
                    }
                }.execute();

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
