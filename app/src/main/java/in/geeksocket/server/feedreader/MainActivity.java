package in.geeksocket.server.feedreader;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Parameter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    public static String feedreader_url = "No url";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void moveToView(View view) {
        new GetData().execute();

        Intent intent = new Intent(this, ViewFeedActivity.class);
        EditText text = (EditText) findViewById(R.id.editText2);
        String url = text.getText().toString();
        intent.putExtra(feedreader_url, url);
        startActivity(intent);
    }
}

class GetData extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String... strings){
        HttpURLConnection urlConnection = null;
        String result = "";
        try{
            URL url = new URL("https://www.google.com");
            urlConnection = (HttpURLConnection) url.openConnection();

            int code = urlConnection.getResponseCode();

            if(code==200){
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                if (in != null) {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
                    String line = "";

                    while ((line = bufferedReader.readLine()) != null)
                        result += line;
                }
                in.close();
            }


        } catch (MalformedURLException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        finally {
            urlConnection.disconnect();
        }
        return result;
    }

    @Override
    protected void onPostExecute(String result) {
        System.out.println(result);
    }

}

