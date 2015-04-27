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

import butterknife.ButterKnife;
import butterknife.InjectView;


public class SelectTime extends ActionBarActivity {
    private static final boolean DEBUG = false;
    private static final java.lang.String TAG = "SelectTime";
    private static Toast toast;

    private static int customTimeMinutes;
    @InjectView(R.id.customTimeText)
    TextView customTimeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_new_activity_time);
        ButterKnife.inject(this);
        TextView startNewProjectText = (TextView) findViewById(R.id.startNewProjectText);
        Button topLeftButton = (Button) findViewById(R.id.topLeftButton);
        Button topRightButton = (Button) findViewById(R.id.topRightButton);
        Button bottomLeftButton = (Button) findViewById(R.id.bottomLeftButton);
        Button bottomRightButton = (Button) findViewById(R.id.bottomRightButton);

        Button goButton = (Button) findViewById(R.id.goButton);

        ImageButton backToMainButton = (ImageButton) findViewById(R.id.backToMainButton);

        startNewProjectText.setText("How much time do you want to spend?");
        topLeftButton.setText("5 minutes");
        topRightButton.setText("15 minutes");
        bottomLeftButton.setText("30 minutes");
        bottomRightButton.setText("60 minutes");
        goButton.setText("GO");

        topLeftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (DEBUG) {
                    Log.i(TAG, "topLeftButton clicked");
                    toast = Toast.makeText(getApplicationContext(), "topLeftButton clicked!", Toast.LENGTH_SHORT);
                    toast.show();
                }
                customTimeMinutes = 5;
                openCategory();
            }
        });

        topRightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (DEBUG) {
                    Log.i(TAG, "topRightButton clicked");
                    toast = Toast.makeText(getApplicationContext(), "topRightButton clicked!", Toast.LENGTH_SHORT);
                    toast.show();
                }
                customTimeMinutes = 15;
                openCategory();
            }
        });

        bottomLeftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (DEBUG) {
                    Log.i(TAG, "bottomLeftButton clicked");
                    toast = Toast.makeText(getApplicationContext(), "bottomLeftButton clicked!", Toast.LENGTH_SHORT);
                    toast.show();
                }
                customTimeMinutes = 30;
                openCategory();
            }
        });

        bottomRightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (DEBUG) {
                    Log.i(TAG, "bottomRightButton clicked");
                    toast = Toast.makeText(getApplicationContext(), "bottomRightButton clicked!", Toast.LENGTH_SHORT);
                    toast.show();
                }
                customTimeMinutes = 60;
                openCategory();
            }
        });
        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(customTimeText.getText().toString().equals(""))) {
                    if (DEBUG) {
                        Log.i(TAG, "customTimeText recognized, is: " + customTimeText.getText().toString());
                        toast = Toast.makeText(getApplicationContext(), customTimeText.getText().toString(), Toast.LENGTH_SHORT);
                        toast.show();
                        customTimeMinutes = Integer.parseInt(customTimeText.getText().toString());
                        openCategory();
                    }
                } else {
                    if (DEBUG) {
                        Log.i(TAG, "customTimeText is no more");
                        toast = Toast.makeText(getApplicationContext(), "no value of custom time", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }
            }
        });
        backToMainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (DEBUG) {
                    Log.i(TAG, "backToMainButton clicked");
                    toast = Toast.makeText(getApplicationContext(), "backToMainButton clicked!", Toast.LENGTH_SHORT);
                    toast.show();
                }
                goBackToMain();
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

    public void goBackToMain() {
        Intent intent = new Intent(this, MainActivity.class);
        if (DEBUG) {
            Log.i(TAG, "goBackToMain() called " + this.toString());
            toast = Toast.makeText(getApplicationContext(), "goBackToMain() called!", Toast.LENGTH_SHORT);
            toast.show();
        }
        startActivity(intent);
    }

    public void openCategory() {
        Intent intent = new Intent(this, SelectCategory.class);
        if (DEBUG) {
            Log.i(TAG, "openCategory() called " + this.toString());
            toast = Toast.makeText(getApplicationContext(), "openCategory() called!", Toast.LENGTH_SHORT);
            toast.show();
        }
        startActivity(intent);
    }

    public static int getCustomTimeMinutes() {
        return customTimeMinutes;
    }

}
