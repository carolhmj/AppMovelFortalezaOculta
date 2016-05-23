package br.great.jogopervasivo.actvititesDoJogo;

import android.app.Activity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import br.ufc.great.arviewer.android.R;


public class Mapa extends Activity {

    private Drawer drawer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa_externo);

        iniciarComponentes();


        DrawerBuilder drawerBuilder = new DrawerBuilder().withActivity(this);
        drawerBuilder.withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
            @Override
            public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {

                return true;
            }
        });
        drawer = drawerBuilder.build();
    }

    private void iniciarComponentes() {

    }

    public void toggleDrawer(View view) {
        if (drawer.isDrawerOpen()) {
            drawer.closeDrawer();
        } else {
            drawer.openDrawer();
        }
    }
}
