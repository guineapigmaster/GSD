package edu.cpp.rbkinney.gsd;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.Toast;

import butterknife.InjectView;


public class SuggestNewLesson extends ActionBarActivity {
    private String activityCategory;
    @InjectView(R.id.categoryWebView)
    WebView categoryWebView;
    private static final boolean DEBUG = true;
    private static final java.lang.String TAG = "SuggestNewLesson";
    private static Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggest_new_lesson);
        activityCategory = SelectCategory.getActivityCategory();
        if (DEBUG) {
            Log.i(TAG, "activityCategory shown" + activityCategory);
            toast = Toast.makeText(getApplicationContext(), "activityCateogory shown: " + activityCategory, Toast.LENGTH_SHORT);
            toast.show();
        }
        categoryWebView.loadUrl("http://beta.html5test.com/");

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
