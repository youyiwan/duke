/**
 * getDate obtains the date input by user in a predefined format YYYY-MM-DD for eg 2019-12-01 and returns a format "MMM dd yyyy"
 * getDateTime returns the time in HH mm format
 */



package Parser;

import Exceptions.EuanExceptions;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class DatesTimes {

        static EuanExceptions myEuanExceptions = new EuanExceptions();
        public static String getDate(String byDescription){
            LocalDate myDate = LocalDate.parse(byDescription);

//            System.out.println(myDate.getDayOfWeek());
//            System.out.println(myDate.getDayOfMonth());
//            System.out.println(myDate.getMonth());
//            System.out.println(myDate.getYear());
//            System.out.println( myDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
            try {
                return myDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
            } catch (DateTimeParseException e){
                myEuanExceptions.dateFormat();
            }
            return myDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        }

        public static  String getDateTime (String to){
            LocalTime myTime = LocalTime.parse(to);
            try {
                return myTime.format(DateTimeFormatter.ofPattern("HH mm"));
            } catch (DateTimeParseException e){
                myEuanExceptions.timeFormat();
            }
            return myTime.format(DateTimeFormatter.ofPattern("HH mm"));
        }



}
