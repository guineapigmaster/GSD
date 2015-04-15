package edu.cpp.rbkinney.gsd;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class SelectTime extends ActionBarActivity {
    private static final boolean DEBUG = true;
    private static final String TAG = "SelectTime";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_new_activity_time);

        TextView startNewProjectText = (TextView) findViewById(R.id.startNewProjectText);
        Button topLeftButton = (Button) findViewById(R.id.topLeftButton);
        Button topRightButton = (Button) findViewById(R.id.topRightButton);
        Button bottomLeftButton = (Button) findViewById(R.id.bottomLeftButton);
        Button bottomRightButton = (Button) findViewById(R.id.bottomRightButton);

        topLeftButton.setText("5 minutes");
        topRightButton.setText("15 minutes");
        bottomLeftButton.setText("30 minutes");
        bottomRightButton.setText("60 minutes");

        topLeftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(DEBUG)
                {

                }
            }
        });

        topRightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        bottomLeftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        bottomRightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
}
