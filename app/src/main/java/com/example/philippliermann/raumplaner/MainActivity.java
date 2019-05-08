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

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button hörsaal = (Button) findViewById(R.id.hörsaal);
        Button edv = (Button) findViewById(R.id.edv);
        Button sem = (Button) findViewById(R.id.seminar);


        hörsaal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openactivity2();
            }
        });

        edv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openactivity3();
            }
        });

        sem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openactivity4();
            }
        });

    }

    public void openactivity2(){
        Intent intent = new Intent(this, Activity2.class);
        startActivity(intent);
    }

    public void openactivity3(){
        Intent intent = new Intent(this, Activity3.class);
        startActivity(intent);
    }

    public void openactivity4(){
        Intent intent = new Intent(this, Activity4.class);
        startActivity(intent);
    }

}
