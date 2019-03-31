package com.example.philippliermann.raumplaner;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.apache.commons.codec.binary.Base64;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import java.net.URL;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class Activity2 extends AppCompatActivity {

    private static final String URL = "https://cis.technikum-wien.at/cis/infoterminal/index.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);

        TextView textView3 = (TextView) findViewById(R.id.textView3);
        readWebPage(textView3);
    }

    public void readWebPage(TextView textView)
    {
        textView.setText("Tests");
        RetrieveWebPage page = new RetrieveWebPage();
        page.execute(URL);


        //Timer um 5 sec zu warten
        long startTime = System.currentTimeMillis();
        while(true)
        {
            if(System.currentTimeMillis() - startTime > 5000)
            {
                break;
            }
        }

        if(page.doc != null)
        {
            textView.setText(page.doc.title());

        }
        else
        {
            textView.setText(page.exception.toString());
        }
    }

    private class RetrieveWebPage extends AsyncTask<String, Void, Document> {

        private Exception exception;

        private Document doc;

        @Override
        protected Document doInBackground(String... strings) {
            try {
                String login = "st17b058" + ":" + "Srw6DfC3!";          // Authorization HTTP Header Field
                String base64login = new String(Base64.encodeBase64(login.getBytes())); // Kodierung
                // return Jsoup.connect(strings[0]).header("Authorization", "Basic c3QxN2IwNTg6U3J3NkRmQzM=").get();
                return Jsoup.connect(strings[0]).get();
            } catch (Exception e) {
                this.exception = e;
                return null;
            }
        }

        @Override
        protected void onPostExecute(Document doc)
        {
            this.doc = doc;
        }
    }
}
