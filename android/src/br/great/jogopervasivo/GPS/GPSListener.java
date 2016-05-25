package br.great.jogopervasivo.GPS;

import android.app.ProgressDialog;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

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
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    public void pararListener(){
        locationManager.removeUpdates(this);
    }
}
