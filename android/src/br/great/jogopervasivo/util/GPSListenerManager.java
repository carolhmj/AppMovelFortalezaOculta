package br.great.jogopervasivo.util;

import android.content.Context;

import br.great.jogopervasivo.GPS.GPSListener;

/**
 * Created by great on 25/05/16.
 *
 * @author Messias Lima
 */
public class GPSListenerManager {
    private static GPSListener gpsListener;

    public static GPSListener getGpsListener(Context context) {
        if (gpsListener == null) {
            gpsListener = new GPSListener(context);
        }
        return gpsListener;
    }
}
