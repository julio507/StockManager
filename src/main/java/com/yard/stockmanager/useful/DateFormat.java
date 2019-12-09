package com.yard.stockmanager.useful;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class DateFormat
{
    private static DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("dd");
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    /**
     * 
     * @param date
     * @return
     */
    public static String getDayString( LocalDate date )
    {
        return date.format(dayFormatter);
    }

    public static String getFormatedString( LocalDate date )
    {
        return date.format( formatter );
    }

    public static String getFormatedString( Date date )
    {
        return getFormatedString( toLocalDate( date ) );
    }

    public static String getFormatedTime( Date date )
    {
        Calendar c = Calendar.getInstance();
        c.setTime(date);

        int h = c.get( Calendar.HOUR_OF_DAY );
        int m = c.get( Calendar.MINUTE );

        return h + ":" + m;
    }

    public static LocalDate toLocalDate(Date dateToConvert) {
        return dateToConvert.toInstant()
          .atZone(ZoneId.systemDefault())
          .toLocalDate();
    }

    public static Date toDate( LocalDate date )
    {
        return Date.from( date.atStartOfDay( ZoneId.systemDefault() ).toInstant() );
    }
}