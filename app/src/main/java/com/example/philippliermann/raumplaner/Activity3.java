package com.example.philippliermann.raumplaner;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Activity3 extends AppCompatActivity {


        private TextView gruen;
        private TextView orange;
        private TextView rot;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_3);
            gruen = (TextView)findViewById(R.id.gruen);
            orange = (TextView)findViewById(R.id.orange);
            rot = (TextView)findViewById(R.id.rot);
            getWebsite();
        }

    private void getWebsite() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final StringBuilder gruenStrBuilder = new StringBuilder();
                final StringBuilder orangeStrBuilder = new StringBuilder();
                final StringBuilder rotStrBuilder = new StringBuilder();



                try {
                    Document doc = Jsoup.connect("https://cis.technikum-wien.at/cis/infoterminal/index.php?raumtyp_kurzbz=EDV&standort_id=").get();
                    Elements gruen = doc.getElementsByClass("gruen_mitteText");
                    appendText(gruenStrBuilder, gruen);
                    Elements orange = doc.getElementsByClass("orange_mitteText");
                    appendText(orangeStrBuilder, orange);
                    Elements rot = doc.getElementsByClass("rot_mitteText");
                    appendText(rotStrBuilder, rot);

                } catch (IOException e){
                    gruenStrBuilder.append("error : ").append(e.getMessage()).append("\n");
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        gruen.setText(gruenStrBuilder.toString());
                        gruen.setTextColor(Color.GREEN);
                        orange.setText(orangeStrBuilder.toString());
                        orange.setTextColor(Color.BLUE);
                        rot.setText(rotStrBuilder.toString());
                        rot.setTextColor(Color.RED);

                    }
            });
            }
            private void appendText(StringBuilder builder, Elements elements)
            {
                for (Element element : elements) {
                    builder.append(element.text()).append("\n");
                }
            }
        }).start();
    }
}
