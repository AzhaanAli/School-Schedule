package com.blucyn.schoolschedulehelper;

import java.util.ArrayList;
import java.util.Calendar;

public class Schedule {
    // ------------------------------------------------------------------------------------------------------------ //
    // Instance variables.

    // Represents the name of your period __ class.
    String[] pNames;
    ArrayList<Event> events;

    // ------------------------------------------------------------------------------------------------------------ //
    // Constructor.

    // One param.
    // Takes in a String array with [7] elements.
    public Schedule(String[] periods){
        // Assign values to instance variables.
        this.pNames = periods;
        this.events = this.getSchedule();
    }

    // ------------------------------------------------------------------------------------------------------------ //
    // Initialization methods.

    //Looks for the current day and responds accordingly using the methods below.
    public ArrayList<Event> getSchedule(int offset){
        // Initialize ArrayList to return later.
        ArrayList<Event> events = new ArrayList<>();

        // Find out what the day of the week is to assign the schedule accordingly.
        int dayOfWeek = Calendar.getInstance().get(Calendar.DAY_OF_WEEK) + offset;

        //Check if its a weekend.
        if(dayOfWeek == 7 || dayOfWeek == 1){
            // Since it is, add weekend event and then return.
            events.add(new Event("Weekend", "15:15", "7:10"));

            // If its Saturday then offset the start and end accordingly.
            if(dayOfWeek == 7){
                events.get(0).modifyStartTime(-1);
                events.get(0).modifyEndTime(2);
            }else{
                // Since its Sunday, offset for that instead.
                events.get(0).modifyStartTime(-2);
                events.get(0).modifyEndTime(1);
            }

            return events;
        }

        // Since its not a weekend, just assign whichever schedule it is.
        if(dayOfWeek == 2 || dayOfWeek == 5){
            events = this.getMonThurs();
        }else if(dayOfWeek == 3){
            events = this.getTues();
        }else if(dayOfWeek == 6){
            events = this.getFri();
        }else{
            events = this.getWed();
        }
        return events;
    }
    public ArrayList<Event> getSchedule(){
        return this.getSchedule(0);

    }


    // Returns an events array as if it was Monday or Thursday.
    public ArrayList<Event> getMonThurs(){
        // Initialize ArrayList.
        ArrayList<Event> events = new ArrayList<>();

        // Add off school event for before school.
        events.add(new Event("Off School", "15:15", "7:10"));
        events.get(events.size() - 1).modifyStartTime(-1);


        // Add the school schedule.
        events.add(new Event("0th Period", "7:10", "8:00"));
        events.add(new Event("Break", "8:00", "8:45"));
        events.add(new Event(pNames[0], "8:45", "9:35"));
        events.add(new Event("Break", "9:35", "9:45"));
        events.add(new Event(pNames[1], "9:45", "10:35"));
        events.add(new Event("Break", "10:35", "10:45"));
        events.add(new Event(pNames[2], "10:45", "11:35"));
        events.add(new Event("Lunch", "11:35", "12:05"));
        events.add(new Event("Asynchronous Learning", "12:05", "12:35"));
        events.add(new Event(pNames[3], "12:35", "13:05"));
        events.add(new Event("Break", "13:05", "13:15"));
        events.add(new Event(pNames[4], "13:15", "13:45"));
        events.add(new Event("Break", "13:45", "13:55"));
        events.add(new Event(pNames[5], "13:55", "14:25"));
        events.add(new Event("Break", "14:25", "14:35"));
        events.add(new Event(pNames[6], "14:35", "15:05"));
        events.add(new Event("Asynchronous Learning", "15:05", "15:15"));


        // Add off school event for after school.
        events.add(new Event("Off School", "15:15", "7:30"));
        events.get(events.size() - 1).modifyEndTime(1);

        // Return the full schedule.
        return events;
    }
    // Returns an events array as if it was Tuesday.
    public ArrayList<Event> getTues(){
        // Initialize ArrayList.
        ArrayList<Event> events = new ArrayList<>();

        // Add off school event for before school.
        events.add(new Event("Off School", "15:15", "7:30"));
        events.get(events.size() - 1).modifyStartTime(-1);


        // Add the school schedule.
        events.add(new Event("0th Period", "7:30", "8:00"));
        events.add(new Event("Break", "8:00", "8:45"));
        events.add(new Event(pNames[3], "8:45", "9:35"));
        events.add(new Event("Break", "9:35", "9:45"));
        events.add(new Event(pNames[4], "9:45", "10:35"));
        events.add(new Event("Break", "10:35", "10:45"));
        events.add(new Event(pNames[5], "10:45", "11:35"));
        events.add(new Event("Lunch", "11:35", "12:05"));
        events.add(new Event(pNames[6], "12:05", "12:55"));
        events.add(new Event("Break", "12:55", "13:05"));
        events.add(new Event(pNames[0], "13:05", "13:35"));
        events.add(new Event("Break", "13:35", "13:45"));
        events.add(new Event(pNames[1], "13:45", "14:15"));
        events.add(new Event("Break", "14:15", "14:25"));
        events.add(new Event(pNames[2], "14:25", "15:05"));
        events.add(new Event("Asynchronous Learning", "15:05", "15:15"));


        // Add off school event for after school.
        events.add(new Event("Off School", "15:15", "8:45"));
        events.get(events.size() - 1).modifyEndTime(1);

        // Return the full schedule.
        return events;
    }
    // Returns an events array as if it was Wednesday.
    public ArrayList<Event> getWed(){
        // Initialize ArrayList.
        ArrayList<Event> events = new ArrayList<>();

        // Add off school event for before school.
        events.add(new Event("Off School", "15:15", "8:45"));
        events.get(events.size() - 1).modifyStartTime(-1);


        // Add the school schedule.
        events.add(new Event("Asynchronous Learning", "8:45", "10:20"));
        events.add(new Event("Break", "10:20", "10:30"));
        events.add(new Event("Asynchronous Learning", "10:30", "12:10"));
        events.add(new Event("Advisory", "12:10", "13:00"));
        events.add(new Event("Asynchronous Learning", "13:00", "13:10"));
        events.add(new Event("Asynchronous Learning (2)", "13:10", "13:40"));
        events.add(new Event("Asynchronous Learning (3)", "13:40", "13:50"));
        events.add(new Event("Asynchronous Learning (4)", "13:50", "14:20"));
        events.add(new Event("Asynchronous Learning (4)", "14:20", "15:15"));


        // Add off school event for after school.
        events.add(new Event("Off School", "15:15", "7:10"));
        events.get(events.size() - 1).modifyEndTime(1);

        // Return the full schedule.
        return events;
    }
    // Returns an events array as if it was Friday.
    public ArrayList<Event> getFri(){
        // Initialize ArrayList.
        ArrayList<Event> events = new ArrayList<>();

        // Add off school event for before school.
        events.add(new Event("Off School", "15:15", "7:30"));
        events.get(events.size() - 1).modifyStartTime(-1);


        // Add the school schedule.
        events.add(new Event("0th Period", "7:30", "8:00"));
        events.add(new Event("Break", "8:00", "8:45"));
        events.add(new Event(pNames[3], "8:45", "9:35"));
        events.add(new Event("Break", "9:35", "9:45"));
        events.add(new Event(pNames[4], "9:45", "10:35"));
        events.add(new Event("Break", "10:35", "10:45"));
        events.add(new Event(pNames[5], "10:45", "11:35"));
        events.add(new Event("Lunch", "11:35", "12:05"));
        events.add(new Event(pNames[6], "12:05", "12:55"));
        events.add(new Event("Break", "12:55", "13:05"));
        events.add(new Event(pNames[0], "13:05", "13:35"));
        events.add(new Event("Break", "13:35", "13:45"));
        events.add(new Event(pNames[1], "13:45", "14:15"));
        events.add(new Event("Break", "14:15", "14:25"));
        events.add(new Event(pNames[2], "14:25", "15:05"));
        events.add(new Event("Asynchronous Learning", "15:05", "15:15"));


        // Add off school event for after school.
        events.add(new Event("Weekend", "15:15", "8:45"));
        events.get(events.size() - 1).modifyEndTime(3);

        // Return the full schedule.
        return events;
    }

    // ------------------------------------------------------------------------------------------------------------ //
    // Methods.

    // Returns the current event.
    public Event getCurrentEvent(){
        return this.events.get(this.getIndOfCurrent());

    }

    // Returns the next event.
    public Event getNextEvent(){
        Event current = this.getCurrentEvent();

        try{
            // Initialize contender object.
            Event contender = this.events.get(this.getIndOfCurrent() + 1);

            // Search for a new one without the same name.
            int count = 1;
            while(current.name.equals(contender.name)){
                contender = this.events.get(this.getIndOfCurrent() + count);
                count++;
            }

            // Return the first future event of a different name.
            return contender;

        }catch (IndexOutOfBoundsException e){
            // Since you're on the last event of the day,
            // Get the first future event of a different name.
            ArrayList<Event> events = this.getSchedule(1);

            for(Event contender : events){
                if(!current.name.equals(contender.name)){
                    return contender;
                }
            }
        }

        return null;
    }

    // Returns the index of the current event.
    private int getIndOfCurrent(){
        // Get the index of the current event.
        for(int i = 0; i < this.events.size(); i++){
            if(this.events.get(i).getPercentDone() != -1){
                return i;
            }
        }

        // Aww looks like its not there.
        return -1;
    }

    // Returns true if today's a weekend.
    public boolean isWeekend(){
        int dayOfWeek = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);

        return dayOfWeek <= 1;
    }

}
