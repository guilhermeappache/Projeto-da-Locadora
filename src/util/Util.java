/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/*
   @author guilherme;
 */
public class Util {

    public static Date stringToDate(String data) {
        if (data == null) {
            return null;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date ret = null;
        try {
            ret = sdf.parse(data);
        } catch (ParseException e) {

        }
        return ret;
    }

    
    public static java.sql.Date dataSQL(java.util.Date dataNormal) {
      
        Calendar data = Calendar.getInstance();
        data.setTime(dataNormal);
        data.set(Calendar.HOUR_OF_DAY, 0);
        data.set(Calendar.MINUTE, 0);
        data.set(Calendar.SECOND, 0);
        data.set(Calendar.MILLISECOND, 0);

        java.sql.Date dataSQL = new java.sql.Date(data.getTimeInMillis());

        return dataSQL;
    }
    
    
}
