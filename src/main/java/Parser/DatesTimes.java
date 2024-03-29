/**
 * getDate obtains the date input by user in a predefined format YYYY-MM-DD for eg 2019-12-01 and returns a format "MMM dd yyyy"
 * getDateTime returns the time in HH mm format
 * getInitialReminder updates the reminder list for the first time when a deadline task is first created
 * getRegularReminder recurring show the reminder list while user is still interacting with the chatbot
 */



package Parser;

import Exceptions.EuanExceptions;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class DatesTimes {

    public static ArrayList<String> monthList;
    public static void createMonth()
    {
        monthList = new ArrayList<>();
        monthList.add("jan");
        monthList.add("feb");
        monthList.add("mar");
        monthList.add("apr");
        monthList.add("may");
        monthList.add("jun");
        monthList.add("jul");
        monthList.add("aug");
        monthList.add("sep");
        monthList.add("oct");
        monthList.add("nov");
        monthList.add("dec");
    }

        static EuanExceptions myEuanExceptions = new EuanExceptions();
        public static String getDate(String byDescription) {
//            System.out.println(myDate.getDayOfWeek());
//            System.out.println(myDate.getDayOfMonth());
//            System.out.println(myDate.getMonth());
//            System.out.println(myDate.getYear());
//            System.out.println( myDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
//            try {
//                if (!byDescription.matches("\\d{4}-\\d{2}-\\d{2}")) {
//                    throw new EuanExceptions();
//                }
//            } catch (EuanExceptions e)
//            {
//                myEuanExceptions.dateFormat();
//            }
            LocalDate myDate = LocalDate.parse(byDescription);
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

        public static String getInitialReminder(String byDescription){

            LocalDate myDate = LocalDate.parse(byDescription);
            myDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
            Period dateRange = Period.between(LocalDate.now(),myDate);

            if(dateRange.getDays() < 3 && (dateRange.getMonths() == 0) && (dateRange.getYears() == 0) ){
                return "Caution ⚠: This task is due in less than 3 days";
            }
            else {
                return "Initial Reminder: You have "
                        + dateRange.getDays() + " day(s), "
                        + dateRange.getMonths() + " month(s), "
                        + dateRange.getYears() + " year(s), "
                        + "to complete this task";
            }


        }


        public static String getRegularReminder(String checkDate) {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
            LocalDate TargetDate = LocalDate.parse(checkDate,dateTimeFormatter);

            Period dateRange = Period.between(LocalDate.now(), TargetDate);
            if(dateRange.getDays() < 3 && (dateRange.getMonths() == 0) && (dateRange.getYears() == 0) ){
                return "Caution ⚠: This task is due in less than 3 days";
            }
            else {
                return "Reminder: You have "
                        + dateRange.getDays() + " day(s), "
                        + dateRange.getMonths() + " month(s), "
                        + dateRange.getYears() + " year(s), "
                        + "to complete this task";
            }
        }



}
