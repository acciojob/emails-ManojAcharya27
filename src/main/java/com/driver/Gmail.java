package com.driver;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.*;

public class Gmail extends Email {

    int inboxCapacity; //maximum number of mails inbox can store
    //Inbox: Stores mails. Each mail has date (Date), sender (String), message (String). It is guaranteed that message is distinct for all mails.
    //Trash: Stores mails. Each mail has date (Date), sender (String), message (String)
    ArrayList<Truple> indbox=new ArrayList<>();
    ArrayList<Truple> trash=new ArrayList<>();
    public  Gmail(String emailId){
        super(emailId);
    }

    public Gmail(String emailId, int inboxCapacity) {
       super(emailId);
       this.inboxCapacity=inboxCapacity;
    }

    public void receiveMail(Date date, String sender, String message){
        // If the inbox is full, move the oldest mail in the inbox to trash and add the new mail to inbox.
        // It is guaranteed that:
        // 1. Each mail in the inbox is distinct.
        // 2. The mails are received in non-decreasing order. This means that the date of a new mail is greater than equal to the dates of mails received already.
       if(indbox.size()>=inboxCapacity){
           Truple it =indbox.remove(0);
           indbox.add(new Truple(date,sender,message));
           trash.add(new Truple(it.date, it.sender, it.message));
       }else {
           indbox.add(new Truple(date, sender, message));
       }
    }

    public void deleteMail(String message){
        // Each message is distinct
        // If the given message is found in any mail in the inbox, move the mail to trash, else do nothin
        if(indbox.size()==0) return;
        int a=0;
        boolean flag=false;
        for(int i=0;i<indbox.size();i++){
            if(indbox.get(i).message.equals(message)){
                a=i;
                flag=true;
            }
        }
        Truple it;
        if(flag){
            it=indbox.get(a);
            indbox.remove(a);
            trash.add(new Truple(it.date, it.sender,it.message));
        }

    }

    public String findLatestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the latest mail present in the inbox

        if(indbox.size()!=0) return indbox.get(indbox.size()-1).message;
        return  null;

    }

    public String findOldestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the oldest mail present in the inbox
       if(indbox.size()!=0) return indbox.get(0).message;
       return  null;

        }


    public int findMailsBetweenDates(Date start, Date end){
        //find number of mails in the inbox which are received between given dates
        //It is guaranteed that start date <= end date
       /* Format formatter = new SimpleDateFormat("dd/MM/yyyy");
        String s = formatter.format(start);
        //System.out.println(s);
        String  s2=formatter.format(end);
        int a=0;
        for(int i=0;i<2;i++){
            char c=s.charAt(i);
            int n=Character.getNumericValue(c);
            a=a*10+n;
        }
        int b=0;
        for(int i=0;i<2;i++){
            char c=s2.charAt(i);
            int n=Character.getNumericValue(c);
            b=b*10+n;
        }
        int ans=0;
            for(int i=0;i<indbox.size();i++){
                Truple it=indbox.get(i);
                String date=formatter.format(it.date);
                int val=0;
                for(int j=0;j<2;j++){
                    char c=s2.charAt(j);
                    int n=Character.getNumericValue(c);
                    val=val*10+n;
                }
                if(val>=a&&val<=b) {
                    ans++;
                }
            }
            return ans;

*/
        int ans=0;
        for(int i=0;i<indbox.size();i++){
            if(indbox.get(i).date.compareTo(start)>=0&&indbox.get(i).date.compareTo(end)<=0){
                ans++;
            }
        }
        return ans+1;
    }

    public int getInboxSize(){
        // Return number of mails in inbox
        return indbox.size();

    }

    public int getTrashSize(){
        // Return number of mails in Trash
        return trash.size();

    }

    public void emptyTrash(){
        // clear all mails in the trash
      trash=new ArrayList<>();

    }

    public int getInboxCapacity() {
        // Return the maximum number of mails that can be stored in the inbox
        return this.inboxCapacity;
    }
}
class Truple{
    Date date;
    String sender;
    String message;

    public Truple(Date date, String sender, String message) {
        this.date = date;
        this.sender = sender;
        this.message = message;
    }
}