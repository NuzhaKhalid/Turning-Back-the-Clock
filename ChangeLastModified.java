package q3;
import java.util.*;
import java.text.*;
import java.io.File;
/**
 *
 * @author Nuzha
 */
public class Q3 {

    public static void setDate(String date, String time, String fileName) throws IllegalArgumentException
    {
        //extract day, month and year from date
        int day, month, year;
        try{
            day = Integer.parseInt(date.substring(date.indexOf('/') + 1, date.lastIndexOf('/')));
            month = Integer.parseInt(date.substring(0, date.indexOf('/')));
            year = Integer.parseInt(date.substring(date.lastIndexOf('/')+1));
        } catch(NumberFormatException e){
            throw new IllegalArgumentException("Unable to parse date!");
        }
        //extract sec, min and hour from time
        int sec, min, hr;
        try{
            sec = Integer.parseInt(time.substring(time.lastIndexOf(':') + 1));
            min = Integer.parseInt(time.substring(time.indexOf(':') + 1, time.lastIndexOf(':')));
            hr = Integer.parseInt(time.substring(0, time.indexOf(':')));
        } catch(NumberFormatException e){
            throw new IllegalArgumentException("Unable to parse time!");
        }
        
        File file = new File("File.txt");
        if (!file.isFile())
            throw new IllegalArgumentException("File does not exist: " + fileName);

        //create calender object to check if date and time values are valid
        Calendar cal = Calendar.getInstance();
        cal.setLenient(false);
        cal.set(year, --month, day, hr, min, sec);
        Date newLastModified;
        try {
            newLastModified = cal.getTime();
        }
        catch(IllegalArgumentException e){
            throw new IllegalArgumentException("Date/Time values are out of bounds");
        }
        if(file.isFile()){
            //declare a format
            java.text.SimpleDateFormat DateTimeformat = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            System.out.println("Changing Last Modified from " + DateTimeformat.format(file.lastModified()) + " to " + date + " " + time);
            //set new last modified
            file.setLastModified(newLastModified.getTime());
            System.out.println("Latest Last Modified: " + DateTimeformat.format(file.lastModified()));
        } 
    }

    public static void main(String[] args) {
        //return if arguments less than 3
        if(args.length < 3){
            System.out.println("Arguments less than 3! Please enter 3 arguments");
            return;
        }
        else if(args.length < 3)
            System.out.println("Arguments more than 3! The rest will be ignored");
        try { 
            setDate(args[0], args[1], args[2]);
        } catch (IllegalArgumentException e) {
            System.out.println("Exception thrown: " + e.getMessage());
        } 
    }
}
