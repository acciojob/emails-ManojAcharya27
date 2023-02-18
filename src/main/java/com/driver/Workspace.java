package com.driver;

import org.apache.commons.lang3.tuple.Pair;

import java.lang.reflect.AnnotatedArrayType;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;

public class Workspace extends Gmail{

    private ArrayList<Meeting> calendar; // Stores all the meetings
    String emailId;

    public Workspace(String emailId) {
        super(emailId);
        // The inboxCapacity is equal to the maximum value an integer can store.
      this.emailId=emailId;
      calendar=new ArrayList<>();

    }

    public void addMeeting(Meeting meeting){
        //add the meeting to calendar
       calendar.add(meeting);
    }

    public int findMaxMeetings(){
        // find the maximum number of meetings you can attend
        // 1. At a particular time, you can be present in at most one meeting
        // 2. If you want to attend a meeting, you must join it at its start time and leave at end time.
        // Example: If a meeting ends at 10:00 am, you cannot attend another meeting starting at 10:00 am

        String start="";
        String end="";
       // String val="";
        ArrayList<ArrayList<Double>> ans=new ArrayList<>();
        for(int i=0;i<calendar.size();i++) {
            ArrayList<Double> curr=new ArrayList<>();
            Meeting m = calendar.get(i);
             start = String.valueOf(m.getStartTime());
             end = String.valueOf(m.getEndTime());

             String val= String.valueOf(start.charAt(0));
               val+= String.valueOf(start.charAt(1));
            val+= String.valueOf('.');
            val+= String.valueOf(start.charAt(3));
            val+= String.valueOf(start.charAt(4));


            String val2=String.valueOf(end.charAt(0));
            val2+= String.valueOf(end.charAt(1));
            val2+= String.valueOf('.');
            val2+= String.valueOf(end.charAt(3));
            val2+= String.valueOf(end.charAt(4));
            double d=Double.parseDouble(val);
            double d2=Double.parseDouble(val2);
            curr.add(d);
            curr.add(d2);
            ans.add(new ArrayList<>(curr));

          }
       Collections.sort(ans,(a,b)->{
           for (int i=0;i<2;i++){
               return (int) (a.get(0)-b.get(0));
           }
            return (int) (a.get(0)-a.get(0));
        });
      Double prev=-1000.00;
        int res=0;
        for (int i=0;i<ans.size();i++){
            for(int j=0;j<ans.get(i).size();j++){
                if(ans.get(i).get(0)>prev){
                    res++;
                    prev=(ans.get(i).get(1));
                }
            }
        }

     return res;
    }
}

