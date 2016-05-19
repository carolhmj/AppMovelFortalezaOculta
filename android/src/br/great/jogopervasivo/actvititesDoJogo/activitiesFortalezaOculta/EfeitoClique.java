package br.great.jogopervasivo.actvititesDoJogo.activitiesFortalezaOculta;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;

import br.ufc.great.arviewer.android.R;

public class EfeitoClique implements View.OnTouchListener {
    Context context;

    public EfeitoClique(Context context) {
        this.context=context;
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            view.setBackgroundColor(context.getResources().getColor(R.color.realce_botao));
        }
        if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
            view.setBackgroundColor(context.getResources().getColor(android.R.color.transparent));
        }
        return false;
    }
}