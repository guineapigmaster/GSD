package edu.cpp.rbkinney.gsd;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class NewLesson extends ActionBarActivity {
    private static final boolean DEBUG = false;
    private static final String TAG = "SuggestNewLesson";
    private static JSONObject activityCategoryObject;
    private static JSONArray infoArray;
    private static JSONObject stepListObject;
    private static JSONArray instructionArray;
    private static int numOfSteps;
    private static int numOfMinutes;
    private static int counter = 0;

    @InjectView(R.id.titleTextId)
    TextView titleText;
    @InjectView(R.id.infoTextId)
    TextView infoText;
    @InjectView(R.id.nextStepButtonId)
    Button nextStepButton;
    //    @InjectView(R.id.saveStepButtonId)
//    Button saveStepButton;
    @InjectView(R.id.prevStepButtonId)
    Button prevStepButton;

    private String title;
    private String infoString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggest_new_lesson);
        ButterKnife.inject(this);

        activityCategoryObject = SelectCategory.getActivityCategoryObject();
        numOfMinutes = SelectTime.getCustomTimeMinutes();

        try {
            prevStepButton.setText("Previous Step");
//            saveStepButton.setText("Save Progress");
            nextStepButton.setText("Next Step");

            instructionArray = new JSONArray(activityCategoryObject.getString("instructions"));
            displayMaterials();
            if (DEBUG) {
                Log.i(TAG, infoString);
            }


//            Log.d("damn", stepListObject.toString());
//            Iterator daIterator = stepListObject.keys();
//            stepListArray = new JSONArray();
//            while (daIterator.hasNext()) {
//                String text = (String) daIterator.next();
//                stepListArray.put(stepListObject.get(text));
//            }
//            for (int i = 0; i < stepListArray.length(); i++) {
//                Log.d("fuck", stepListArray.getString(i));
//            }
            stepListObject = (JSONObject) instructionArray.get(0);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        nextStepButton.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                try {

                    if (counter < numOfSteps) {
                        counter++;

                        JSONObject stepObject = stepListObject.getJSONObject("step" + counter);
                        String stepNumberText = "Step " + counter + ": ";
                        String nextStepTitle = stepNumberText + stepObject.getString("title");
                        String nextStepInfo = stepObject.getString("text");
                        if (DEBUG) {
                            Log.i(TAG, nextStepTitle.toString());
                            Log.i(TAG, nextStepInfo);
                            Log.i(TAG, Integer.toString(counter));
                        }
                        titleText.setText(nextStepTitle);
                        infoText.setText(nextStepInfo);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        prevStepButton.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                try {
                    counter--;
                    if (counter > 0) {
                        JSONObject stepObject = stepListObject.getJSONObject("step" + counter);
                        String stepNumberText = "Step " + counter + ": ";
                        String prevStepTitle = stepNumberText + stepObject.getString("title");
                        String prevStepInfo = stepObject.getString("text");
                        if (DEBUG) {
                            Log.i(TAG, prevStepTitle.toString());
                            Log.i(TAG, prevStepInfo);
                            Log.i(TAG, Integer.toString(counter));
                        }
                        titleText.setText(prevStepTitle);
                        infoText.setText(prevStepInfo);
                    }
                    if (counter <= 0) {
                        displayMaterials();
                        counter = 0;
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

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

    public void displayMaterials() throws JSONException {
        title = activityCategoryObject.getString("title");
        numOfSteps = Integer.parseInt(activityCategoryObject.getString("numberOfSteps"));
        numOfMinutes = Integer.parseInt(activityCategoryObject.getString("time"));
        titleText.setText(title);
        infoArray = new JSONArray(activityCategoryObject.getString("materialsRequired"));

        infoString = "List of Materials Needed:\n";
        for (int i = 0; i < infoArray.length(); i++) {
            infoString += i + 1 + ". " + infoArray.getString(i) + "\n";
        }
        infoString += "\nEstimated time to complete: " + numOfMinutes + " minutes";
        infoText.setText(infoString);
    }
}
