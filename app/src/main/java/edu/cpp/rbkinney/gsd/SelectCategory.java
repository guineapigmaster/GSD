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


public class SelectCategory extends ActionBarActivity {
    private static final boolean DEBUG = false;
    private static final String TAG = "SelectCategory";
    private static Toast toast;

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
                goBackToTime();
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

    public void goBackToTime() {
        Intent intent = new Intent(this, SelectTime.class);
        if (DEBUG) {
            Log.i(TAG, "backToTime() called " + this.toString());
            toast = Toast.makeText(getApplicationContext(), "backToTime() called!", Toast.LENGTH_SHORT);
            toast.show();
        }
        startActivity(intent);
    }
}
