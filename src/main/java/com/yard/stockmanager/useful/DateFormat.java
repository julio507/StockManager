package com.yard.stockmanager.useful;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
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