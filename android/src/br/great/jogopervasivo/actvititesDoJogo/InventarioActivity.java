package br.great.jogopervasivo.actvititesDoJogo;

import android.app.Activity;
import android.os.Bundle;

import br.ufc.great.arviewer.android.R;

public class InventarioActivity extends Activity {
    public static final String ITEM_ID = "item_id";
    public static final String ITEM_TIPO = "item_tipo";
    public static final String ITEM_ARQUIVO = "item_arquivo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventario);
    }
}
