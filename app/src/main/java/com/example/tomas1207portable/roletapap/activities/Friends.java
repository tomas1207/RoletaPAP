package com.example.tomas1207portable.roletapap.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.tomas1207portable.roletapap.R;

import java.util.ArrayList;

public class Friends extends AppCompatActivity {
private Button addfriends;
    private TextView ListofFriends;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);
        addfriends = (Button) findViewById(R.id.addfriends);
        ListofFriends = (TextView) findViewById(R.id.listfriends);
        addfriends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> Nome = new ArrayList<>(10);
                    Nome.add("Tomas");
                    Nome.add("David");
                for (int i = 0; i <= Nome.size();i++)
                    {
                        ListofFriends.setText(Nome.get(i) + " * ");
                    }

            }
        });
    }
}
