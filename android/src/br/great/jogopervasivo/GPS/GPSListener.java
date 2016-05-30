package br.great.jogopervasivo.GPS;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;

import br.great.jogopervasivo.actvititesDoJogo.Mapa;
import br.great.jogopervasivo.util.Armazenamento;
import br.ufc.great.arviewer.ARViewer;
import br.ufc.great.arviewer.android.R;

/**
 * Created by great on 25/05/16.
 *
 * @author Messias Lima
 */
public class GPSListener implements LocationListener {

    private Context context;
    private LocationManager locationManager;
    private ARViewer visualizadorDeRa;
    private ProgressDialog progressDialog;
    private Mapa mapa;
    private boolean enableNetWorkProvider;

    public void setMapa(Mapa mapa) {
        this.mapa = mapa;
    }

    public GPSListener(Context context) {
        this.context = context;
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 1, this);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, this);
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage(context.getString(R.string.ativando_gps));
        progressDialog.setCancelable(false);
        if (Armazenamento.resgatarUltimaLocalizacao(context) == null) {
            progressDialog.show();
        }
    }

    @Override
    public void onLocationChanged(Location location) {

        Armazenamento.salvarLocalizacao(location, context);
        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }

        if (mapa!=null){
            mapa.novaLocalizacao(location);
        }


    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {
        if (provider.equals(LocationManager.GPS_PROVIDER)) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle(R.string.gps_desativado).setMessage(context.getString(R.string.app_name) + " " + context.getString(R.string.nao_funciona_sem_gps));
            builder.setNegativeButton(R.string.sair_do_jogo
                    , new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ((Activity)context).finish();
                        }
                    });
            builder.setPositiveButton("Ativar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    context.startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                }
            });
            builder.setCancelable(false).create().show();
        }
    }

    public void pararListener() {
        locationManager.removeUpdates(this);
    }


}
