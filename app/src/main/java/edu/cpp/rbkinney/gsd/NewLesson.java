package edu.cpp.rbkinney.gsd;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class NewLesson extends ActionBarActivity {
    private static final boolean DEBUG = true;
    private static final java.lang.String TAG = "SuggestNewLesson";
    private static Toast toast;
    private static JSONObject activityCategoryObject;

    @InjectView(R.id.titleTextId)
    TextView titleText;
    @InjectView(R.id.materialsTextId)
    TextView materialsText;
    private String title;
    private String materialString;
    private String jsonFileName;

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggest_new_lesson);
        ButterKnife.inject(this);
        activityCategoryObject = SelectCategory.getActivityCategoryObject();
        jsonFileName = SelectCategory.getJsonFileName();
        try {
            title = activityCategoryObject.getString("title");
            titleText.setText(title);
            JSONArray jsonArray = new JSONArray(activityCategoryObject.getString("materialsRequired"));
            materialString = "List of Materials Needed:\n";
            for (int i = 0; i < jsonArray.length(); i++) {
                materialString += i + 1 + ". " + jsonArray.getString(i) + "\n";
            }

            Log.i(TAG, materialString);
            materialsText.setText(materialString);
        } catch (JSONException e) {
            e.printStackTrace();
        }

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
