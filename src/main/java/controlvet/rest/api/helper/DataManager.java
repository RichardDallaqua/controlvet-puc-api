package controlvet.rest.api.helper;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Calendar;
import java.util.Date;

public class DataManager {
    private static DataManager instance;
    public DataManager() {
    }

    public static DataManager getInstance() {
        if (instance == null) {
            instance = new DataManager();
        }
        return instance;
    }

    public String formatDate(Date data){
        return new SimpleDateFormat("dd/MM/yyyy").format(data);
    }

    public String formatDate(Date data, String pattern){
        return new SimpleDateFormat(pattern).format(data);
    }

    public String formatDate(Time time){
        return new SimpleDateFormat("HH:mm").format(time);
    }

    public Boolean validarData(String data){
        String dateFormat = "dd/MM/uuuu";
        if (StringManager.getInstance().isNullOrEmpty(data)) {
            return false;
        }
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter
                .ofPattern(dateFormat)
                .withResolverStyle(ResolverStyle.STRICT);
        try {
            LocalDate date = LocalDate.parse(data, dateTimeFormatter);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean validarHora(String hora){
        if (StringManager.getInstance().isNullOrEmpty(hora)) {
            return false;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        sdf.setLenient(false);
        try{
            sdf.parse(hora);
        }catch(ParseException e){
            return false;
        }
        return true;
    }

    public String formatDateTime(Date data){ return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(data); }

    public Date stringToDate(String data)throws Exception {
        if (StringManager.getInstance().isNullOrEmpty(data)) {
           return null;
        }
        return new SimpleDateFormat("dd/MM/yyyy").parse(data);

    }

    public Date addTimeinDate(Date data, Time time) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(data);
        cal.add(Calendar.HOUR_OF_DAY, time.getHours());
        cal.add(Calendar.MINUTE, time.getMinutes());
        return  cal.getTime();
    }

    public Time getTimeofDate(Date data) {
        String hora = String.format ("%02d", data.getHours());
        String minutos = String.format ("%02d", data.getMinutes());
        return java.sql.Time.valueOf(hora + ":" + minutos +":00");
    }

    public Time stringToTime(String time){
        return java.sql.Time.valueOf(time + ":00");
    }
}
