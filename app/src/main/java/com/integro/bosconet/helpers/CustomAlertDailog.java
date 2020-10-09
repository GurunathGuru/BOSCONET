package com.integro.bosconet.helpers;

import android.content.Context;
import android.content.DialogInterface;

import androidx.appcompat.app.AlertDialog;

import com.integro.bosconet.MainActivity;

public class CustomAlertDailog {


    public CustomAlertDailog(Context context, String title, DialogInterface.OnClickListener positiveListener, DialogInterface.OnClickListener negativeListener, String posMessage, String negMessage) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setTitle("Exit");
        AlertDialog.Builder builder = alertDialogBuilder.setMessage(title).setCancelable(false)
                .setPositiveButton(posMessage, positiveListener).setNegativeButton(negMessage, negativeListener);
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
