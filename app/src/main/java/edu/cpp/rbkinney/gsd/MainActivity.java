package edu.cpp.rbkinney.gsd;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
    private static final boolean DEBUG = true;
    private static final String TAG = "MainActivity";

    private static Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView welcomeText = (TextView) findViewById(R.id.welcomeText);
        Button startNew = (Button) findViewById(R.id.startNewProjectButton);
        Button resumeOld = (Button) findViewById(R.id.resumeOldProjectButton);

        welcomeText.setText("welcome");
        startNew.setText("start");
        resumeOld.setText("resume");

        startNew.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (DEBUG) {
                    Log.i(TAG, "startNew clicked");
                    toast = Toast.makeText(getApplicationContext(), "startNew clicked!", Toast.LENGTH_SHORT);
                    toast.show();
                }
                changeActivity();
            }
        });

        resumeOld.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (DEBUG) {
                    Log.i(TAG, "resumeOld clicked");
                    toast = Toast.makeText(getApplicationContext(), "resumeOld clicked!", Toast.LENGTH_SHORT);
                    toast.show();
                }
                changeActivity();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_start_new_activity_time, menu);
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

    public void changeActivity()
    {
        Intent intent = new Intent(this, SelectTime.class);
        if (DEBUG) {
            Log.i(TAG, "changeActivity() called " + this.toString());
            toast = Toast.makeText(getApplicationContext(), "changeActivity() called!", Toast.LENGTH_SHORT);
            toast.show();
        }
        startActivity(intent);
    }
}
