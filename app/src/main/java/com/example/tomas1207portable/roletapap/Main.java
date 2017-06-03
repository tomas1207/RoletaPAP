package com.example.tomas1207portable.roletapap;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.tomas1207portable.roletapap.activities.RoletaActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.lang.reflect.Method;

public class Main extends AppCompatActivity {
    private FirebaseAuth auth;
    Button Registo;
    Button Login;
    EditText UserName;
    EditText PassWord;
    SharedPreferences sharedPreferences;
    String Passvar;
    boolean mobileDataEnabled = false; // Assume disabled
    NetworkInfo networkInfo;
    ConnectivityManager cm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = getSharedPreferences("cenas",MODE_PRIVATE);
        auth = FirebaseAuth.getInstance();
        cm  = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        UserName = (EditText) findViewById(R.id.Usertextlogin);
        PassWord = (EditText) findViewById(R.id.Passtextlogin);
        networkInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            String cl = sharedPreferences.getString("clear","b");
            String cl2 = sharedPreferences.getString("clearpass","0");
           sharedPreferences.getString("clear","0");
           sharedPreferences.getString("clearpass","0");
        if(cl.contentEquals("b") || cl2.contentEquals("0")){
        }else{
            UserName.setText(cl);
            PassWord.setText(cl2);
        }
        Log.w("Pass","Pass"+ Passvar);
        //region Register Button
        Registo = (Button) findViewById(R.id.RegistoBNT);

        Registo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main.this , com.example.tomas1207portable.roletapap.activities.Registo.class));
            }
        });
        //endregion1

        //region Login Button
        Login = (Button)findViewById(R.id.LoginBNT);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    Class cmClass = Class.forName(cm.getClass().getName());
                    Method method = cmClass.getDeclaredMethod("getMobileDataEnabled");
                    method.setAccessible(true); // Make the method callable
                    mobileDataEnabled = (Boolean)method.invoke(cm);

                } catch (Exception e) {

                }

                if (mobileDataEnabled == true || networkInfo.isConnected() == true) {

                    auth.signInWithEmailAndPassword(UserName.getText().toString(), PassWord.getText().toString())
                            .addOnCompleteListener(Main.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    Log.d("ola", "signInWithEmail:onComplete:" + task.isSuccessful());


                                    // If sign in fails, display a message to the user. If sign in succeeds
                                    // the auth state listener will be notified and logic to handle the
                                    // signed in user can be handled in the listener.
                                    if (!task.isSuccessful()) {
                                        Log.w("ola", "signInWithEmail", task.getException());
                                        Toast.makeText(Main.this, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();
                                    }else{

                                        SharedPreferences.Editor editor = sharedPreferences.edit();
                                        editor.putString("clear",UserName.getText().toString());
                                        editor.putString("clearpass",PassWord.getText().toString());
                                        editor.apply();
                                        startActivity(new Intent(Main.this, RoletaActivity.class));
                                    }

                                    // ...
                                }
                            });
                } else {
                    Toast.makeText(Main.this, "Liga a net", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //endregion
    }
}
