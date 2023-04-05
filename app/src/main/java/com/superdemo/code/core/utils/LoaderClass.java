package com.superdemo.code.core.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.superdemo.code.R;


public class LoaderClass {

    AlertDialog alertDialog;
    Context context;

    public LoaderClass(Context context) {
        this.context = context;
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(false);
        View view = LayoutInflater.from(context).inflate(R.layout.app_loader_layout, null);
        builder.setView(view);

        alertDialog = builder.create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.setCancelable(false);
        if (alertDialog.getWindow() != null) {
            alertDialog.getWindow().getDecorView().setBackgroundResource(android.R.color.transparent);
        }
    }

    public void showLoader() {alertDialog.show();}

    public void hideLoader() {
        alertDialog.dismiss();
    }

}
