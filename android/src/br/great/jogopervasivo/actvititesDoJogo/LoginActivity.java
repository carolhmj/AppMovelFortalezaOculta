package br.great.jogopervasivo.actvititesDoJogo;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;

import br.great.jogopervasivo.GPS.GPSListener;
import br.great.jogopervasivo.util.Armazenamento;
import br.great.jogopervasivo.util.Constantes;
import br.great.jogopervasivo.webServices.FazerLogin;
import br.ufc.great.arviewer.android.R;

public class LoginActivity extends Activity {

    private EditText loginEditText, senhaEditText;
    private TextView naoCadastradoTextView;
    private GPSListener gpsListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        iniciarComponentes();
        Armazenamento.salvar(Constantes.JOGO_EXECUTANDO, false, this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        verificarPlayServices();
        iniciarListenerDeGPS();
    }

    private void iniciarListenerDeGPS() {
        gpsListener = new GPSListener(this);
    }

    private void iniciarComponentes() {
        loginEditText = (EditText) findViewById(R.id.login_login_edit_text);
        senhaEditText = (EditText) findViewById(R.id.login_senha_edit_text);
        naoCadastradoTextView = (TextView) findViewById(R.id.aindaNaoCadastradoTextView);
        naoCadastradoTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, getString(R.string.em_construcao), Snackbar.LENGTH_LONG).show();
            }
        });
    }

    public void fazerLogin(View v) {
        new FazerLogin(this,v,loginEditText.getEditableText().toString(),senhaEditText.getEditableText().toString()).execute();
    }

    private boolean verificarPlayServices() {
        final int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                Dialog dialog = GooglePlayServicesUtil.getErrorDialog(resultCode, this, Constantes.PLAY_SERVICES_RESOLUTION_REQUEST);
                dialog.show();
            } else {
                Toast.makeText(getApplicationContext(), "Play services sem suporte", Toast.LENGTH_SHORT).show();
            }
            return false;
        }
        return true;
    }

    @Override
    protected void onDestroy() {
        gpsListener.pararListener();
        super.onDestroy();

    }
}
