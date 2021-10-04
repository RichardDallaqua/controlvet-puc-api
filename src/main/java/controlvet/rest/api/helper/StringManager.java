package controlvet.rest.api.helper;

public class StringManager {

    private static StringManager instance;

    public StringManager() {
    }

    public static StringManager getInstance() {
        if (instance == null) {
            instance = new StringManager();
        }
        return instance;
    }

    public boolean isNullOrEmpty(final String... arrayStrings) {
        for (final String item : arrayStrings) {
            if (item == null || item.equals("")) {
                return true;
            }
        }
        return false;
    }

    public Boolean parseBoolean(String value){
        if (value.equals("1")) {
            return true;
        } else {
            return false;
        }
    }

}
