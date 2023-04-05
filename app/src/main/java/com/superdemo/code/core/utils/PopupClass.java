package com.superdemo.code.core.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.superdemo.code.R;

public class PopupClass {
    boolean askingForUpdate, twoButtons;
    String yesButtonString, noButtonString, title, messageToShow;
    PopupClicks popupClicks;

    public PopupClass(boolean twoButtons, boolean askingForUpdate, String yesButtonString, String noButtonString, String title, String messageToShow, PopupClicks popupClicks) {
        this.askingForUpdate = askingForUpdate;
        this.twoButtons = twoButtons;
        this.yesButtonString = yesButtonString;
        this.noButtonString = noButtonString;
        this.title = title;
        this.messageToShow = messageToShow;
        this.popupClicks = popupClicks;
    }

    public PopupClass(boolean twoButtons, String yesButtonString, String noButtonString, String title, String messageToShow, PopupClicks popupClicks) {
        this.twoButtons = twoButtons;
        this.yesButtonString = yesButtonString;
        this.noButtonString = noButtonString;
        this.title = title;
        this.messageToShow = messageToShow;
        this.popupClicks = popupClicks;
        this.askingForUpdate = false;
    }

    public void showPopup(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(false);

        View view = LayoutInflater.from(context).inflate(R.layout.app_update_layout, null);
        builder.setView(view);

        TextView titleTV = view.findViewById(R.id.titleTV);
        TextView messageTV = view.findViewById(R.id.messageTV);
        TextView noThanksButtonTV = view.findViewById(R.id.noThanksButtonTV);
        TextView updateButtonTV = view.findViewById(R.id.updateButtonTV);

        if (askingForUpdate) {
            titleTV.setText(messageToShow);
            messageTV.setText(messageToShow);
            noThanksButtonTV.setText(noButtonString);
            noThanksButtonTV.setVisibility(View.VISIBLE);
            updateButtonTV.setText(yesButtonString);
        } else {
            titleTV.setText(title);
            messageTV.setText(messageToShow);
            noThanksButtonTV.setVisibility(View.GONE);
            updateButtonTV.setText(yesButtonString);
        }


        if (twoButtons) {
            titleTV.setText(title);
            messageTV.setText(messageToShow);
            noThanksButtonTV.setText(noButtonString);
            noThanksButtonTV.setVisibility(View.VISIBLE);
            updateButtonTV.setText(yesButtonString);
        } else {
            noThanksButtonTV.setVisibility(View.GONE);
        }

        final AlertDialog alertDialog = builder.create();

        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.setCancelable(false);
        if (!((Activity) context).isFinishing()) {
            alertDialog.show();
        }

        noThanksButtonTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                popupClicks.onClickNoButton();
            }
        });

        updateButtonTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                popupClicks.onClickYesButton();
            }
        });


        if (alertDialog.getWindow() != null) {
            alertDialog.getWindow().getDecorView().setBackgroundResource(android.R.color.transparent);
        }
    }

}
