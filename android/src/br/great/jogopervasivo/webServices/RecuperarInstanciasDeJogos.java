package br.great.jogopervasivo.webServices;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.ufc.great.arviewer.android.R;
//import br.great.jogopervasivo.actvititesDoJogo.InstanciasExecutandoActivity;
import br.great.jogopervasivo.arrayAdapters.InstanciasExecutandoAdapter;
import br.great.jogopervasivo.beans.InstanciaDeJogo;
import br.great.jogopervasivo.util.Constantes;

/**
 * Created by messiaslima on 06/02/2015.
 *
 * @author messiaslima
 * @version 1.0
 * @since 1.0
 */
public class RecuperarInstanciasDeJogos {

    private Context context;

    public RecuperarInstanciasDeJogos(Context context) {
        this.context = context;
    }


    public List<InstanciaDeJogo> recuperar(Integer... params) {
        JSONArray jsonArrayReq = new JSONArray();
        JSONObject jsonObjectReq = new JSONObject();
        JSONObject jsonObject1Req = new JSONObject();

        try {
            jsonObjectReq.put("acao", 105);
            jsonObject1Req.put("jogo_id", params[0]);
            jsonObject1Req.put("jogador_id", params[1]);
            jsonArrayReq.put(0, jsonObjectReq);
            jsonArrayReq.put(1, jsonObject1Req);
        } catch (JSONException je) {
            je.printStackTrace();
        }
        String resposta = Servidor.fazerGet(jsonArrayReq.toString(),context);
        try {
            JSONArray jsonArray = new JSONArray(resposta);
            List<InstanciaDeJogo> instancias = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                InstanciaDeJogo instanciaDeJogo = new InstanciaDeJogo();
                instanciaDeJogo.setId(jsonObject.getInt("codigo"));
                JSONArray grupos = jsonObject.getJSONArray("grupos");
                if (grupos.length() == 0) {
                    instanciaDeJogo.setGrupoId(0);
                    instanciaDeJogo.setJogadorParticipando(false);
                } else {
                    instanciaDeJogo.setGrupoId(grupos.getJSONObject(0).optInt("id"));
                    instanciaDeJogo.setJogadorParticipando(true);
                }
                instanciaDeJogo.setIcone(jsonObject.optString("icone"));
                instanciaDeJogo.setNome(jsonObject.getString("nome"));
                instanciaDeJogo.setNomeFicticio(jsonObject.getString("nomeficticio"));
                instanciaDeJogo.setGrupoNome(jsonObject.optString("grupo_nome", " "));
                instancias.add(instanciaDeJogo);
            }
            return instancias;
        } catch (JSONException je) {
            Log.e(Constantes.TAG, "Erro no Json: \n" + je.getMessage());
            je.printStackTrace();
            return null;
        }

    }


}
