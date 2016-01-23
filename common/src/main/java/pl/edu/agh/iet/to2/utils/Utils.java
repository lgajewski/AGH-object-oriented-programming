package pl.edu.agh.iet.to2.utils;


import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.time.*;
import java.util.Date;

public class Utils {

    public static LocalDate convertDateToLocalDate(Date date) {
        Instant instant = Instant.ofEpochMilli(date.getTime());
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();
    }

    public static Date convertLocalDateToDate(LocalDate date) {
        Instant instant = date.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
    }

    public static BigDecimal convertStringToBigDecimal(String decimal) throws ParseException {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator(',');
        symbols.setDecimalSeparator('.');
        String pattern = "#,##0.0#";
        DecimalFormat decimalFormat = new DecimalFormat(pattern, symbols);
        decimalFormat.setParseBigDecimal(true);

        return (BigDecimal) decimalFormat.parse(decimal);
    }

    public static int diffInMonths(Date startDate, Date endDate) {
        LocalDate start = convertDateToLocalDate(startDate);
        LocalDate end = convertDateToLocalDate(endDate);

        Period betweenDates = Period.between(start, end);

        return betweenDates.getMonths();
    }
}
