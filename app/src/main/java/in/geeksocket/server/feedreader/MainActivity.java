package in.geeksocket.server.feedreader;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public static String feedreader_url = "No url";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void moveToView(View view) {
        Intent intent = new Intent(this, ViewFeedActivity.class);
        EditText text = (EditText) findViewById(R.id.editText2);
        String url = text.getText().toString();
        intent.putExtra(feedreader_url, url);
        startActivity(intent);
    }
}
