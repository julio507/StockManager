package com.yard.stockmanager.useful;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateFormat
{
    private static DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("dd");
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static String getDayString( LocalDate date )
    {
        return date.format(dayFormatter);
    }

    public static String getFormatedString( LocalDate date )
    {
        return date.format( formatter );
    }
}