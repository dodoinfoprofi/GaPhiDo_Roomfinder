package com.example.philippliermann.raumplaner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Activity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);
        readWebPage();
    }

    public void readWebPage()
    {
        try
        {
            Document doc = Jsoup.connect("https://cis.technikum-wien.at/cis/infoterminal/index.php").get();
            String title = doc.title();
            Element element = doc.body();
            element.getElementById("content");


            System.out.println("Title : " + title);
        }
        catch(Exception e)
        {

        }

    }
}
