package com.example.computersolutions;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class Util {
    private Context context;
    public Util(Context context){
        this.context = context;
    }
    public AlertDialog createLoadingAlert(){
        return  new AlertDialog.Builder(context)
                .setMessage(R.string.dialogBoxWait)
                .setCancelable(false)
                .create();
    }

    public AlertDialog createDescriptionAlert(String description){
        return  new AlertDialog.Builder(context)
                .setTitle(R.string.app_name)
                .setMessage(description)
                .setPositiveButton("Ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        })
                .create();
    }
}
