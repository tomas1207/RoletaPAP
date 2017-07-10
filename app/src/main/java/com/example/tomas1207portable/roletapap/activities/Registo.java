package com.example.tomas1207portable.roletapap.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tomas1207portable.roletapap.Main;
import com.example.tomas1207portable.roletapap.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class Registo extends AppCompatActivity {
   private Button Register;
    private EditText UserEdit;
    private EditText PsssEdit;
    private  FirebaseAuth FirebaseAuth;
private Button Face;
    SharedPreferences sharedPref ;
    SharedPreferences.Editor editor ;

    String Email ;
    String Pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_registo);



        sharedPref = getSharedPreferences("cenas",MODE_PRIVATE);


        UserEdit = (EditText) findViewById(R.id.TXTuserregisto);
        PsssEdit = (EditText) findViewById(R.id.TXTPassregisto);
        Register = (Button) findViewById(R.id.BNTRegister);
        //region Butao registo
        //Inicio do butao
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Email = UserEdit.getText().toString();
                Pass = PsssEdit.getText().toString();
                Log.w("Teste User", Email);
                Log.w("Teste Pass", Pass);
                if (TextUtils.isEmpty(Email) || TextUtils.isEmpty(Pass)) {
                    Toast.makeText(Registo.this, "O email ou a pass estao vazios", Toast.LENGTH_SHORT).show();
                    return;
                } else{
                    FirebaseAuth.createUserWithEmailAndPassword(Email, Pass)
                            .addOnCompleteListener(Registo.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    Toast.makeText(Registo.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                                    if (!task.isSuccessful()) {
                                        Toast.makeText(Registo.this, "Authentication failed." + task.getException(),
                                                Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(Registo.this, "O Registo foi completo com sucesso", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(Registo.this, Main.class));
                                        editor = sharedPref.edit()
                                        .putString("clear",Email)
                                        .putString("clearpass",Pass);
                                        editor.apply();

                                        finish();
                                    }
                                }
                            });
            }
            }
        });
        //Fim do Butão
        //endregion


    }

}
