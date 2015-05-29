package edu.cpp.rbkinney.gsd;

import android.annotation.TargetApi;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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
    private static String creditLink;
    private static String imageFileName;
    private static int numOfSteps;
    private static int numOfMinutes;
    private static int counter = -1;

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
    @InjectView(R.id.imageId)
    ImageView imageHere;

    private String title;
    private String infoString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggest_new_lesson);
        ButterKnife.inject(this);
        counter = -1;

        activityCategoryObject = SelectCategory.getActivityCategoryObject();
        numOfMinutes = SelectTime.getCustomTimeMinutes();

        try {
            prevStepButton.setText("Previous Step");
//            saveStepButton.setText("Save Progress");
            nextStepButton.setText("Next Step");
            numOfSteps = Integer.parseInt(activityCategoryObject.getString("numberOfSteps"));
            instructionArray = new JSONArray(activityCategoryObject.getString("instructions"));
            displayIntro();

            if (DEBUG) {
                Log.i(TAG, infoString);
            }


//            Log.d("saveme", stepListObject.toString());
//            Iterator daIterator = stepListObject.keys();
//            stepListArray = new JSONArray();
//            while (daIterator.hasNext()) {
//                String text = (String) daIterator.next();
//                stepListArray.put(stepListObject.get(text));
//            }
//            for (int i = 0; i < stepListArray.length(); i++) {
//                Log.d("plzsaveme", stepListArray.getString(i));
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
                    Log.i(TAG, "before counter++ is: " + counter);

                    Log.i(TAG, "after counter++ is: " + counter);
                    Log.i(TAG, "numOfSteps = " + numOfSteps);
                    if (counter < numOfSteps) {
                        counter++;
                        Log.i(TAG, "ifstmt counter is: " + counter);
                        if (counter == 0) {
                            displayMaterials();
                        } else {

                            JSONObject stepObject = stepListObject.getJSONObject("step" + counter);
                            String stepNumberText = "Step " + counter + ": ";
                            String nextStepTitle = stepNumberText + stepObject.getString("title");
                            String nextStepInfo = stepObject.getString("text");
                            if (DEBUG) {
                                Log.i(TAG, nextStepTitle.toString());
                                Log.i(TAG, nextStepInfo);
                                Log.i(TAG, Integer.toString(counter));
                            }

                            if (counter == numOfSteps) {
                                creditLink = activityCategoryObject.getString("link");
                                nextStepInfo += "\n\n\nAll credits go to: " + creditLink;

                            }
                            titleText.setText(nextStepTitle);
                            infoText.setText(nextStepInfo);
                        }

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
                    Log.i(TAG, "counter is: " + counter);
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
                    if (counter == 0) {
                        displayMaterials();
                        counter--;
                    }
                    if (counter <= -1) {
                        displayIntro();
                        counter = -1;
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

    public void displayIntro() throws JSONException {
        title = activityCategoryObject.getString("title");
        titleText.setText(title);
        numOfMinutes = Integer.parseInt(activityCategoryObject.getString("time"));
        infoText.setText("\nEstimated time to complete: " + numOfMinutes + " minutes");
        Resources res = getResources();
        String mDrawableName = activityCategoryObject.getString("image");
        Log.i(TAG, mDrawableName);
        int resID = res.getIdentifier(mDrawableName, "raw", getPackageName());
        Drawable drawable = res.getDrawable(resID);
        imageHere.setImageDrawable(drawable);
//        imageHere.setImageResource(R.raw);
    }

    public void displayMaterials() throws JSONException {
//        title = activityCategoryObject.getString("title");

        infoString = "List of Materials Needed:\n";
        titleText.setText(infoString);
        infoArray = new JSONArray(activityCategoryObject.getString("materialsRequired"));
        imageHere.setImageDrawable(null);

        for (int i = 0; i < infoArray.length(); i++) {
            infoString += i + 1 + ". " + infoArray.getString(i) + "\n";
        }
//        infoString += "\nEstimated time to complete: " + numOfMinutes + " minutes";
        infoText.setText(infoString);
    }
}
