package com.blucyn.schoolschedulehelper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    // ------------------------------------------------------------------------------------------------------------ //
    // Setup.

    // Handler for looping.
    Handler handler = new Handler();
    Runnable refresh;

    // Reserved names for objects.
    public Schedule s;

    // Reserved names for screen elements.
    public TextView currentName;
    public TextView currentTime;

    public TextView progressMinutes;
    public ProgressBar progressBar;
    public TextView progressBarText;

    public TextView nextName;
    public TextView nextTime;

    // ------------------------------------------------------------------------------------------------------------ //
    // Main method.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Run backend and update frontend every ___ milliseconds.
        this.run(200);
    }

    // ============================================================================================================ //
    // Master methods.

    // Initialize and then loop.
    public void run(int milliseconds){
        // Initialize objects and screen elements.
        this.initialize();
        // Call update screen every ___ milliseconds.
        this.updateLoop(milliseconds);
    }

    // Calls other methods repeatedly.
    private void updateLoop(final int milliseconds){
        updateCards();
        refresh = new Runnable() {
            public void run() {
                // Update progress every time you loop.
                updateProgress();

                // Try to only update cards when its close to changing classes.
                if(!s.getCurrentEvent().name.equals(currentName.getText().toString())){
                    updateCards();
                }
                handler.postDelayed(refresh, milliseconds);
            }
        };
        handler.post(refresh);
    }

    // ============================================================================================================ //
    //Helper methods for initialization.

    // Does all initialization.
    private void initialize(){
        this.initializeElements();
        this.initializeSchedule();
    }

    // Creates the Schedule object "s".
    private void initializeSchedule(){
        // Array of classes.
        // Chloe Schedule.
//        String[] classes = {
//                "Algebra 3",
//                "Current Politics",
//                "Digital Media", "Music Collaboration",
//                "Spanish III", "LA 12",
//                "Criminal Justice"};

        // Azhaan Schedule.
        String[] classes = {
                "Physics",
                "Current Politics",
                "LA 12", "Pre-Calc",
                "Chemistry", "Ceramics",
                "AP Statistics"};

//        // General Schedule.
//        String[] classes = {
//                "Period 1",
//                "Period 2",
//                "Period 3", "Period 4",
//                "Period 5", "Period 6",
//                "Period 7"};

        // Assign classes to schedule.
        s = new Schedule(classes);
    }
    // Creates all screen elements.
    private void initializeElements(){
        // Match all elements to their respective components.
        currentName = (TextView)findViewById(R.id.currentName);
        currentTime = (TextView)findViewById(R.id.currentTime);

        progressMinutes = (TextView)findViewById(R.id.progressMinutes);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBarText = (TextView)findViewById(R.id.progressBarText);

        nextName = (TextView)findViewById(R.id.nextName);
        nextTime = (TextView)findViewById(R.id.nextTime);

        progressBar.setMax(1000);
    }

    // ------------------------------------------------------------------------------------------------------------ //
    //Helper methods for populating screen elements.

    // Updates both cards.
    private void updateCards(){
        this.updateCurrent();
        this.updateNext();
    }

    // Updates current card.
    private void updateCurrent(){
        // Create Event object.
        Event current = s.getCurrentEvent();

        // Fill current event name.
        currentName.setText(current.name);

        // Create time stamps to assign to current time.
        String time = current.getStartString() + " - " + current.getEndString();
        currentTime.setText(time);
    }
    // Updates next card.
    private void updateNext(){
        // Create Event object.
        Event next = s.getNextEvent();

        // Fill current event name.
        nextName.setText(next.name);

        // Create time stamps to assign to current time.
        String time = next.getStartString() + " - " + next.getEndString();
        nextTime.setText(time);
    }

    // ------------------------------------------------------------------------------------------------------------ //
    //Helper methods for populating progressbar related elements.

    // Updates all parts of progress.
    private void updateProgress(){
        // Create object for current event.
        Event current = s.getCurrentEvent();

        // Pass event and update all.
        this.updateProgressPos();
        this.updateProgressText(current);
        this.updateProgressPercent(current);
    }

    // Update the bars position.
    private void updateProgressPos(){
        int progress = (int)(s.getCurrentEvent().getPercentDone() * 1000);
        progressBar.setProgress(progress);
    }
    // Update the text directly above and below the bar.
    private void updateProgressText(Event current){
        // Assign value to minutes left.
        double diff = current.getTimeDiff(current.getToday(), current.endTime);
        String minutesText = formatTime(diff) + " Remaining";

        // Edit text values.
        progressMinutes.setText(minutesText);
    }
    // Updates the percent text.
    private void updateProgressPercent(Event current){
        // Assign values to percent.
        double percent = Math.round(current.getPercentDone() * 10000) / 100.0;
        String percentText = percent + "%";
        progressBarText.setText(percentText);
    }

    // Gets a string that represents time from a minute input.
    public String formatTime(double timeInMinutes){
        int minutes = (int)timeInMinutes;
        int seconds = (int)((timeInMinutes % 1) * 60) + 1;
        minutes = Math.round(minutes);


        String minutesText = "";

        // Format in case there's more than an hour left.
        if(minutes % 60 == 0){
            if(minutes == 0){
                minutesText = seconds + " Seconds";
            }else{
                minutesText = (minutes / 60) + " Hours";
            }
        }else if(minutes > 60){
            minutesText = (minutes / 60) + " Hours and " + (minutes % 60) + " Minutes";
        }else{
            // Format for if there's less than an hour left.
            minutesText = minutes + " Minutes and " + seconds + " Seconds";
        }

        return minutesText;
    }
}