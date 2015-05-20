package edu.cpp.rbkinney.gsd;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
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
import java.io.Writer;
import java.util.Random;


public class SelectCategory extends ActionBarActivity {
    private static final boolean DEBUG = false;
    private static final String TAG = "SelectCategory";
    private static Toast toast;

    private static JSONObject activityCategoryObject;
    private static String jsonFileName;
    private static int customTimeMinutes;

    public static JSONObject getActivityCategoryObject() {
        return activityCategoryObject;
    }

    public static String getJsonFileName() {
        return jsonFileName;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_new_activity_category);

        TextView selectCategoryText = (TextView) findViewById(R.id.selectCategoryText);
        Button cookingButton = (Button) findViewById(R.id.cookingButton);
        Button electronicsButton = (Button) findViewById(R.id.electronicsButton);
        Button diyButton = (Button) findViewById(R.id.diyButton);
        Button randomButton = (Button) findViewById(R.id.randomButton);
        ImageButton backToTimeButton = (ImageButton) findViewById(R.id.backToTimeButton);
        customTimeMinutes = SelectTime.getCustomTimeMinutes();
        if (DEBUG) {
            toast = Toast.makeText(getApplicationContext(), "custom time is: " + customTimeMinutes, Toast.LENGTH_SHORT);
            toast.show();
        }

        selectCategoryText.setText("What would you like to learn?");
        cookingButton.setText("Cooking");
        electronicsButton.setText("Electronics");
        diyButton.setText("DIY");
        randomButton.setText("Surprise Me");

        cookingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (DEBUG) {
                    Log.i(TAG, "cookingButton clicked");
                    toast = Toast.makeText(getApplicationContext(), "cookingButton clicked!", Toast.LENGTH_SHORT);
                    toast.show();
                }
                JSONprocesser("cooking1");
                changeActivityTo(NewLesson.class);
            }
        });

        electronicsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (DEBUG) {
                    Log.i(TAG, "electronicsButton clicked");
                    toast = Toast.makeText(getApplicationContext(), "electronicsButton clicked!", Toast.LENGTH_SHORT);
                    toast.show();
                }
                JSONprocesser("electronics1");
                changeActivityTo(NewLesson.class);
            }
        });

        diyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (DEBUG) {
                    Log.i(TAG, "diyButton clicked");
                    toast = Toast.makeText(getApplicationContext(), "diyButton clicked!", Toast.LENGTH_SHORT);
                    toast.show();
                }
                JSONprocesser("diy1");
                changeActivityTo(NewLesson.class);
            }
        });

        randomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (DEBUG) {
                    Log.i(TAG, "randomButton clicked");
                    toast = Toast.makeText(getApplicationContext(), "randomButton clicked!", Toast.LENGTH_SHORT);
                    toast.show();
                }
                String[] random = {"cooking1", "diy1", "electronics1"};
                JSONprocesser(random[new Random().nextInt(random.length)]);
                changeActivityTo(NewLesson.class);
            }
        });

        backToTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (DEBUG) {
                    Log.i(TAG, "backToTimeButton clicked");
                    toast = Toast.makeText(getApplicationContext(), "backToTimeButton clicked!", Toast.LENGTH_SHORT);
                    toast.show();
                }
                changeActivityTo(SelectTime.class);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_start_new_activity_category, menu);
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

    public void changeActivityTo(Class toOpen) {
        Intent intent = new Intent(this, toOpen);
        if (DEBUG) {
            Log.i(TAG, "changeActivityTo() called " + this.toString());
            toast = Toast.makeText(getApplicationContext(), "changeActivity() called!", Toast.LENGTH_SHORT);
            toast.show();
        }
        startActivity(intent);
    }

    public void JSONprocesser(String activityCategory) {
        InputStream is = this.getResources().openRawResource(getResources().getIdentifier(activityCategory, "raw", getPackageName()));
        //start JSON reader
//        InputStream is = getResources().openRawResource(lol.getIntExtra());
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try {
            Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            int n;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
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
        try {
            activityCategoryObject = new JSONObject(jsonString);
            String tester = activityCategoryObject.getString("category");
            Log.i(TAG, "tester is " + tester);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //end JSON reader
    }
}
