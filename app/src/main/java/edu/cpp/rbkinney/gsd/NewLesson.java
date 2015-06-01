package edu.cpp.rbkinney.gsd;

import android.annotation.TargetApi;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
    private static int numOfSteps;
    private static int numOfMinutes;
    private static int counter = -1;

    @InjectView(R.id.titleTextId)
    TextView titleText;
    @InjectView(R.id.infoTextId)
    TextView infoText;
    @InjectView(R.id.nextStepButtonId)
    Button nextStepButton;
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
        infoText.setMovementMethod(new ScrollingMovementMethod());
        try {
            setTitle(activityCategoryObject.getString("title"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

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
                                nextStepInfo += "\n\nAll credits go to: " + creditLink;

                            }
                            titleText.setText(nextStepTitle);
                            infoText.setText(nextStepInfo);
                            if (SelectCategory.getActivityCategoryObject().getString("category").equals("electronics")) {
                                Log.i("YOO", "im here");
                                Resources res = getResources();
                                String mDrawableName = activityCategoryObject.getString("step1image");
                                Log.i(TAG, mDrawableName);
                                int resID = res.getIdentifier(mDrawableName, "raw", getPackageName());
                                Drawable drawable = res.getDrawable(resID);
                                imageHere.setImageDrawable(drawable);
                            } else
                                imageHere.setImageDrawable(null);
                            freeUpImageSpace();
                        }
                    }
                    Log.i(TAG, "nextStep counter is: " + counter);
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
                    if (counter > -1) {
                        counter--;
                        Log.i(TAG, ">= -1, " + counter);
                        if (counter == -1) {
                            Log.i(TAG, "== -1, " + counter);
                            displayIntro();
                            counter = -1;
                        }
                        if (counter == 0) {
                            Log.i(TAG, "== 0, " + counter);
                            displayMaterials();
                        } else {
                            Log.i(TAG, "> 0, " + counter);
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
                            imageHere.setImageDrawable(null);
                            freeUpImageSpace();
                        }
                    }
                    Log.i(TAG, "prevStep counter is: " + counter);
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
//        freeUpImageSpace();
    }

    public void freeUpImageSpace() {

        if (imageHere == null) {
            ((LinearLayout.LayoutParams) imageHere.getLayoutParams()).weight = 0.0f;
            imageHere.refreshDrawableState();
        }
    }
}
