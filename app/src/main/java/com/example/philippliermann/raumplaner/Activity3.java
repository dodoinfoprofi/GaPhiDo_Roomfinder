package com.example.philippliermann.raumplaner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Activity3 extends AppCompatActivity {

    private Button getBtn;
    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_4);
        getBtn = (Button) findViewById(R.id.getBtn);
        result = (TextView)findViewById(R.id.result);
        getWebsite();
        /*getBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getWebsite();
            }
        });*/
    }

    private void getWebsite(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                final StringBuilder builder = new StringBuilder();

                try {
                    Document doc = Jsoup.connect("https://cis.technikum-wien.at/cis/infoterminal/index.php?raumtyp_kurzbz=EDV&ort_kurzbz=EDV_A2.06&standort_id=").get();
                    Elements test = doc.getElementsByClass("gruen_mitteText");
                    builder.append(test).append("\n");


                    //Elements links = doc.select("a[href]");
                    //builder.append(title).append("\n");

                    /*for (Element link : links) {
                        builder.append("\n").append("link : ").append(link.attr("href"))
                                .append("\n").append("Text : ").append(link.text());
                    }*/


                    //Element content = doc.getElementById("content");
                    //builder.append(content).append("\n");


                    /*for (Element tes : test) {
                        builder.append("\n").append("Raum : ").append(test.attr("gruen_mitteText"));
                    }*/



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
