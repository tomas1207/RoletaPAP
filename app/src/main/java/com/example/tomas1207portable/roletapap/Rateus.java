package com.example.tomas1207portable.roletapap;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.provider.Telephony;
import android.widget.Button;

import java.security.PublicKey;

/**
 * Created by Tomas1207 on 30/11/2016.
 */

public class Rateus extends Dialog {
    Button Rateus;
    Button tolate;
    Button Remind;
    private static final String Control ="Control";
    private  static  final  String covertordeaparicoes = "Convertor";
    private  static  int Numero_de_aparicoes;
    public Rateus(Context context) {
        super(context);
    }
    public static void  Initializate(Context context){
        SharedPreferences sh = context.getSharedPreferences(Control,Context.MODE_PRIVATE);
        Numero_de_aparicoes = sh.getInt(covertordeaparicoes, 0);
        if (Numero_de_aparicoes == 2){
            com.example.tomas1207portable.roletapap.Rateus rr = new Rateus(context);
            rr.setContentView(R.layout.dialog_rateus);
            rr.show();
            Numero_de_aparicoes = 0;
            SharedPreferences.Editor sh_ed = sh.edit();
            sh_ed.putInt(covertordeaparicoes,Numero_de_aparicoes);
            sh_ed.apply();
        }else {Numero_de_aparicoes++;}

    }
}
