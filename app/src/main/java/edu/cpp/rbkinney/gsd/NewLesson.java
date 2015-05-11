package edu.cpp.rbkinney.gsd;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

import butterknife.InjectView;


public class NewLesson extends ActionBarActivity {
    private static final boolean DEBUG = true;
    private static final java.lang.String TAG = "SuggestNewLesson";
    private static Toast toast;
    @InjectView(R.id.titleText)
    TextView titleText;
    private String activityCategory;
    private String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggest_new_lesson);

        InputStream is = getResources().openRawResource(R.raw.test);
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try {
            Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            int n;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String jsonString = writer.toString();
        Log.i(TAG, jsonString);
        JSONObject lol = null;
        try {
            lol = new JSONObject(jsonString);
            String tester = lol.getString("title");
            Log.i(TAG, "tester is " + tester);
            titleText.setText(tester);
        } catch (JSONException e) {
            e.printStackTrace();
        }


//        titleText.setText(title);
        //        WebView newLessonWebView = (WebView) findViewById(R.id.categoryWebView);
//        activityCategory = SelectCategory.getActivityCategory().toString();
//        if (DEBUG) {
//            Log.i(TAG, "activityCategory shown: " + activityCategory);
//            toast = Toast.makeText(getApplicationContext(), "activityCateogory shown: " + activityCategory, Toast.LENGTH_SHORT);
//            toast.show();
//        }
//        newLessonWebView.loadUrl(activityCategory);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_suggest_new_lesson, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
