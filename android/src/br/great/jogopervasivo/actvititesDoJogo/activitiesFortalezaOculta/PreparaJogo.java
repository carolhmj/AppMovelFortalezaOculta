package br.great.jogopervasivo.actvititesDoJogo.activitiesFortalezaOculta;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.telephony.TelephonyManager;

import br.great.jogopervasivo.beans.Jogo;
import br.great.jogopervasivo.util.Constantes;
import br.great.jogopervasivo.webServices.CriarNovaInstanciaDeJogo;
import br.ufc.great.arviewer.android.R;


/**
 * Created by great on 18/05/16.
 *
 * @author Messias Lima
 */
public class PreparaJogo extends AsyncTask<Void, Void, Void> {

    private Context context;
    private ProgressDialog progressDialog;
    private int tipoJogo;

    public PreparaJogo(Context context, int tipoJogo) {
        this.context = context;
        this.tipoJogo = tipoJogo;
    }

    @Override
    protected void onPreExecute() {
        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle(R.string.app_name);
        progressDialog.setMessage(context.getString(R.string.obtendo_informacoes));
        progressDialog.show();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        switch (tipoJogo) {
            case Jogo.CAMINHADA_BODE:
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
                CriarNovaInstanciaDeJogo criarNovaInstanciaDeJogo = new CriarNovaInstanciaDeJogo();
                criarNovaInstanciaDeJogo.criar("548", telephonyManager.getDeviceId());

                break;
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        progressDialog.dismiss();
    }
}
