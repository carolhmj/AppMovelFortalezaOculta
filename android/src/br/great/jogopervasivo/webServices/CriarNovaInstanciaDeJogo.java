package br.great.jogopervasivo.webServices;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import br.ufc.great.arviewer.android.R;
//import br.great.jogopervasivo.actvititesDoJogo.InstanciasExecutandoActivity;
import br.great.jogopervasivo.util.Constantes;
import br.great.jogopervasivo.util.InformacoesTemporarias;

/**
 * Created by messiaslima on 10/02/2015.
 * @author messiaslima
 * @since 1.0
 * @version 1.0
 */
public class CriarNovaInstanciaDeJogo  {
    private Context context;
    private ProgressDialog progressDialog;

    public CriarNovaInstanciaDeJogo(Context context) {
        this.context = context;
    }

    public boolean criar(String... params){
        JSONArray jsonArrayReq = new JSONArray();
        JSONObject jsonObjectReq = new JSONObject();
        JSONObject jsonObject1Req = new JSONObject();

        try {
            jsonObjectReq.put("acao", 100);
            jsonObject1Req.put("jogo_id", params[0]);
            jsonObject1Req.put("jogador_id",InformacoesTemporarias.idJogador);
            jsonObject1Req.put("nomeficticio",params[1].replace(" ", "%20"));
            jsonArrayReq.put(0, jsonObjectReq);
            jsonArrayReq.put(1, jsonObject1Req);
        } catch (JSONException je) {
            je.printStackTrace();
        }

        String resposta = Servidor.fazerGet(jsonArrayReq.toString(),context);

        try {
            JSONArray jsonArray = new JSONArray(resposta);
            return jsonArray.getJSONObject(0).getBoolean("result");
        } catch (JSONException je) {
            Log.e(Constantes.TAG, "Erro no Json \n" + je.getMessage());
            je.printStackTrace();
            return false;
        }
    }

}
