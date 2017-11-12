package ua.dnigma.parsesberbyjsoup;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public Elements content;
    public ArrayList<String> titleList = new ArrayList<String>();
    private ArrayAdapter<String> adapter = null;
    private ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView);
        new NewThread().execute();

        adapter = new ArrayAdapter<String>(this, R.layout.list_item, R.id.pro_item, titleList);
    }

    public class NewThread extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {

            Document document;
            try {
                document = Jsoup.connect("https://habrahabr.ru/").get();
                content = document.select("a.post__title_link");
                titleList.clear();
                for (Element contents : content){
                    titleList.add(contents.text());

                }
            } catch (IOException e) {
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            listView.setAdapter(adapter);

        }
    }


}
