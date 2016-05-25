package br.great.jogopervasivo.webServices;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.view.View;

import org.json.JSONObject;

import br.great.jogopervasivo.actvititesDoJogo.MenuJogos;
import br.ufc.great.arviewer.android.R;

/**
 * Created by great on 25/05/16.
 *
 * @author Messias Lima
 */
public class FazerLogin extends AsyncTask<Void, Void, JSONObject> {


    private ProgressDialog progressDialog;
    private Context context;
    private View view;
    private String login, senha;

    public FazerLogin(Context context, View v, String login, String senha) {
        progressDialog = new ProgressDialog(context);
        this.context = context;
        this.view = v;
        this.senha = senha;
        this.login = login;
    }

    @Override
    protected void onPreExecute() {
        progressDialog.setCancelable(false);
        progressDialog.setMessage(context.getString(R.string.autenticando));
        progressDialog.show();
    }

    @Override
    protected JSONObject doInBackground(Void... params) {
        JSONObject resposta = Servidor.fazerLogin(context, login, senha);
        return resposta;
    }

    @Override
    protected void onPostExecute(JSONObject jsonObject) {
        progressDialog.dismiss();
        if (jsonObject.optBoolean("resultado")) {
            Intent intent = new Intent(context, MenuJogos.class);
            context.startActivity(intent);
        } else {
            Snackbar.make(view, jsonObject.optString("mensagem"), Snackbar.LENGTH_LONG).show();
        }
    }
}