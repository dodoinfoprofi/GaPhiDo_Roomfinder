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

    private Button getBtn;
    private TextView result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button hörsaal = (Button) findViewById(R.id.hörsaal);
        Button edv = (Button) findViewById(R.id.edv);
        Button sem = (Button) findViewById(R.id.seminar);
        getBtn = (Button) findViewById(R.id.getBtn);
        result = (TextView)findViewById(R.id.result);


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

        getBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getWebsie();
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

    private void getWebsie(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                final StringBuilder builder = new StringBuilder();

                try {
                    Document doc = Jsoup.connect("https://cis.technikum-wien.at/cis/infoterminal/index.php?raumtyp_kurzbz=EDV&ort_kurzbz=EDV_A2.06&standort_id=").get();

                    String title = doc.title();
                    //Elements links = doc.select("a[href]");
                    //builder.append(title).append("\n");

                    /*for (Element link : links) {
                        builder.append("\n").append("link : ").append(link.attr("href"))
                                .append("\n").append("Text : ").append(link.text());
                    }*/
                    Element content = doc.getElementById("content");
                    builder.append(content).append("\n");
                } catch (IOException e){
                    builder.append("error : ").append(e.getMessage()).append("\n");
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        result.setText(builder.toString());
                    }
                });
            }
        }).start();



    }
}
