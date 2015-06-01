package edu.cpp.rbkinney.gsd;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class MainActivity extends Activity {
    private static final boolean DEBUG = false;
    private static final String TAG = "MainActivity";
    private static Toast toast;

    @InjectView(R.id.welcomeText)
    TextView welcomeText;
    @InjectView(R.id.startNewProjectButton)
    Button startNew;
//    @InjectView(R.id.resumeOldProjectButton)
//    Button resumeOld;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

//        Parse.enableLocalDatastore(this);
//        Parse.initialize(this, "ONNUGT6zp06IvEBiuQ3EkEl2AbFkWI9ljE2tpHe4", "Ex2K1plHhrcbmejPGoiQpgAEvgRghPNXjn5rDRCe");

        welcomeText.setText("welcome");
        startNew.setText("start");
//        resumeOld.setText("resume");

        startNew.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (DEBUG) {
                    Log.i(TAG, "startNew clicked");
                    toast = Toast.makeText(getApplicationContext(), "startNew clicked!", Toast.LENGTH_SHORT);
                    toast.show();
                }
                changeActivityTo(SelectTime.class);
            }
        });

//        resumeOld.setOnClickListener(new Button.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (DEBUG) {
//                    Log.i(TAG, "resumeOld clicked");
//                    toast = Toast.makeText(getApplicationContext(), "resumeOld clicked!", Toast.LENGTH_SHORT);
//                    toast.show();
//                }
//                changeActivityTo(ResumeActivityListFragment.class);
//            }
//        });
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

    public void changeActivityTo(Class toOpen) {
        Intent intent = new Intent(this, toOpen);
        if (DEBUG) {
            Log.i(TAG, "changeActivityTo() called " + this.toString());
            toast = Toast.makeText(getApplicationContext(), "changeActivity() called!", Toast.LENGTH_SHORT);
            toast.show();
        }
        startActivity(intent);
    }
}
