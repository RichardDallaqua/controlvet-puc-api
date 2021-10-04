package controlvet.rest.api.helper;

import java.text.SimpleDateFormat;
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

    public String formatDateTime(Date data){ return new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(data); }

    public Date stringToDate(String data)throws Exception {
        if (StringManager.getInstance().isNullOrEmpty(data)) {
           return null;
        }
        return new SimpleDateFormat("dd/MM/yyyy").parse(data);

    }
}
