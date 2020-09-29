package com.blucyn.schoolschedulehelper;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class Event implements Serializable {
    // ------------------------------------------------------------------------------------------------------------ //
    // Instance variables.

    // Represents the name of your period __ class.
    String name;
    Date startTime;
    Date endTime;

    int totalMinutes;

    // ------------------------------------------------------------------------------------------------------------ //
    // Constructor.

    // Three param.
    public Event(String name, String startTime, String endTime){
        // Split inputs into easier workable data.
        /*
          - Hours is [0].
          - Minutes is [1].
         */
        String[] startTimes = startTime.split(":");
        String[] endTimes = endTime.split(":");

        // Organize data.
        int startHour = Integer.parseInt(startTimes[0]);
        int startMin = Integer.parseInt(startTimes[1]);
        int endHour = Integer.parseInt(endTimes[0]);
        int endMin = Integer.parseInt(endTimes[1]);

        // Start assigning values to the object attributes.
        this.name = name;

        this.startTime = this.getModToday(startHour, startMin);
        this.endTime = this.getModToday(endHour, endMin);

        this.updateTotalMinutes();
    }
    public Event(String name, String date, String startTime, String endTime){
        // Split inputs into easier workable data.
        /*
          - Hours is [0].
          - Minutes is [1].
         */
        String[] startTimes = startTime.split(":");
        String[] endTimes = endTime.split(":");

        String[] specDay = date.split("/");

        // Organize data.
        int startHour = Integer.parseInt(startTimes[0]);
        int startMin = Integer.parseInt(startTimes[1]);
        int endHour = Integer.parseInt(endTimes[0]);
        int endMin = Integer.parseInt(endTimes[1]);

        int month = Integer.parseInt(specDay[0]) - 1;
        int day = Integer.parseInt(specDay[1]);

        // Start assigning values to the object attributes.
        this.name = name;

        this.startTime = this.getModToday(month, day, startHour, startMin);
        this.endTime = this.getModToday(month, day, endHour, endMin);

        this.updateTotalMinutes();
    }
    public Event(String name, int year, String date, String startTime, String endTime){
        // Split inputs into easier workable data.
        /*
          - Hours is [0].
          - Minutes is [1].
         */
        String[] startTimes = startTime.split(":");
        String[] endTimes = endTime.split(":");

        String[] specDay = date.split("/");

        // Organize data.
        int startHour = Integer.parseInt(startTimes[0]);
        int startMin = Integer.parseInt(startTimes[1]);
        int endHour = Integer.parseInt(endTimes[0]);
        int endMin = Integer.parseInt(endTimes[1]);

        int month = Integer.parseInt(specDay[0]) - 1;
        int day = Integer.parseInt(specDay[1]);

        // Start assigning values to the object attributes.
        this.name = name;

        this.startTime = this.getModToday(year, month, day, startHour, startMin);
        this.endTime = this.getModToday(year, month, day, endHour, endMin);

        this.updateTotalMinutes();
    }

    // ------------------------------------------------------------------------------------------------------------ //
    // Methods.

    // Returns today, but with a different hour and minute.
    public Date getModToday(int hour, int min){
        // Get today's time and add the specified translation.
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, min);

        // Reset values we don't care about.
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        // Return end product.
        return cal.getTime();
    }
    public Date getModToday(int month, int day, int hour, int min){
        // Get today's time and add the specified translation.
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, min);

        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, day);

        // Reset values we don't care about.
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        // Return end product.
        return cal.getTime();
    }
    public Date getModToday(int year, int month, int day, int hour, int min){
        // Get today's time and add the specified translation.
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, min);

        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, day);

        cal.set(Calendar.YEAR, year);

        // Reset values we don't care about.
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        // Return end product.
        return cal.getTime();
    }

    // Returns current day.
    public Date getToday(){
        // Get today's time.
        Calendar cal = Calendar.getInstance();

        // Return time.
        return cal.getTime();
    }

    // Shifts start / end times by factors of a day.
    public void modifyStartTime(int change){
        // Convert date to a Calendar object for a little
        Calendar c = Calendar.getInstance();
        c.setTime(this.startTime);
        c.add(Calendar.DAY_OF_YEAR, change);

        // Update startTime.
        this.startTime = c.getTime();

        // Update totalMinutes.
        this.totalMinutes = (int)this.getTimeDiff(this.startTime, this.endTime);
    }
    public void modifyEndTime(int change){
        // Convert date to a Calendar object for a little
        Calendar c = Calendar.getInstance();
        c.setTime(this.endTime);
        c.add(Calendar.DAY_OF_YEAR, change);

        // Update endTime.
        this.endTime = c.getTime();

        // Update totalMinutes.
        this.totalMinutes = (int)this.getTimeDiff(this.startTime, this.endTime);
    }

    // Updates total minutes.
    public void updateTotalMinutes(){
        this.totalMinutes = (int)this.getTimeDiff(this.startTime, this.endTime);

    }

    // Returns the time difference in minutes.
    public double getTimeDiff(Date start, Date end){
        long timeDiff = end.getTime() - start.getTime();
        return timeDiff / 1000.0 / 60.0;
    }
    // Returns true if the event is already done.
    public boolean isDone(){
        return this.getToday().getTime() > this.endTime.getTime();

    }
    // Returns true if the event hasn't started yet.
    public boolean notStarted(){
        return this.getToday().getTime() < this.startTime.getTime();

    }

    // Returns (in percent) how far the course is to being done.
    /*
        RETURNS -1 IF:
          - Event has not started.
          - Event has already finished.
     */
    public double getPercentDone(){
        // Check if event hasn't started yet.
        if(this.getToday().getTime() < this.startTime.getTime()){
            return -1;

        }
        // Check if event has already ended.
        if(this.getToday().getTime() > this.endTime.getTime()){
            return -1;

        }

        // Now that we know the event is currently happening, figure out how much has elapsed.
        double timeLeft = this.getTimeLeft();

        // Divide it by the total minutes of the event to get the percent and return end product.
        return 1 - (timeLeft / this.totalMinutes);
    }
    public double getTimeLeft(){
        return this.getTimeDiff(getToday(), this.endTime);
    }

    // Returns a string of the day of the week.
    public String getDayOfWeek(){
        // Create day names.
        String[] dayNames = {
                // Index translation.
                "Saturday", // 0
                "Sunday",   // 1
                "Monday",   // 2
                "Tuesday",  // 3
                "Wednesday",// 4
                "Thursday", // 5
                "Friday"};  // 6

        // Transform date object into calender object.
        Calendar cal = Calendar. getInstance();
        cal.setTime(this.startTime);

        // Return the index of the date.
        return dayNames[cal.get(Calendar.DAY_OF_WEEK)];
    }

    // Returns start/end time in String form.
    private String formatTime(String time){
        // Boolean to keep track for AM/ PM.
        boolean am = true;

        // Get time into Hour:Min format.
        time = time.substring(11, 16);

        // Split into two separate parts.
        String[] times = time.split(":");
        int hour = Integer.parseInt(times[0]);

        // Turn off AM if time is greater than or equal to 12.
        if(hour >= 12){
            am = false;
        }

        // Process hour in case its over 12.
        if(hour > 12){
            hour -= 12;
        }

        // Special case for 12 AM.
        if(hour == 0){
            hour = 12;
        }

        // Add AM/ PM.
        time = hour + ":" + times[1];
        if(am){
            time += " AM";
        }else{
            time += " PM";
        }

        // Return end product.
        return time;
    }
    public String getStartString(){
        return this.formatTime(this.startTime.toString());

    }
    public String getEndString(){
        return this.formatTime(this.endTime.toString());

    }
}
