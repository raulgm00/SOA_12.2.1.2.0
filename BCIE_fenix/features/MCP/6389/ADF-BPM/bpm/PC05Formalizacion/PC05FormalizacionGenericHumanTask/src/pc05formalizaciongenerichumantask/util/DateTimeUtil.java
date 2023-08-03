package pc05formalizaciongenerichumantask.util;

import java.sql.Timestamp;

import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;

import pc05formalizaciongenerichumantask.constants.FormalizacionConstants;

public class DateTimeUtil {
    public static Timestamp stringToTimestamp(String value) {
        SimpleDateFormat formatter = new SimpleDateFormat(FormalizacionConstants.FORMATO_CONVERSION_FECHA);
        Timestamp fechaFirmaConvertida = null;
        try {
            Date fecha = formatter.parse(value);
            fechaFirmaConvertida = new Timestamp(fecha.getTime());
        } catch (Exception e) {
            fechaFirmaConvertida = null;
        }
        
        return fechaFirmaConvertida;
    }
    
    public static Timestamp dateToTimestamp(Date value) {
        Timestamp fecha = null;
        Calendar cal = Calendar.getInstance();
        cal.setTime(value);
        cal.set(Calendar.MILLISECOND, 0);
        
        try {
            fecha = new java.sql.Timestamp(cal.getTimeInMillis());
        } catch(Exception e) {
            fecha = null;
        }
        
        return fecha;
    }
}
