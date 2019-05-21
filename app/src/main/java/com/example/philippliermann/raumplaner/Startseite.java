package com.example.philippliermann.raumplaner;

import android.app.Activity;
import android.content.Intent;
import android.provider.DocumentsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Startseite extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startseite2);
        Button weiter = (Button) findViewById(R.id.button);


        weiter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openmain();
            }
        });

    }

    private void openmain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
