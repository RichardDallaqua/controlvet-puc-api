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

    public String formatString(String value, String mask) {
        if (value == null || mask == null) {
            return null;
        }

        StringBuilder result = new StringBuilder();
        String byteMask;
        String byteValue;
        int pos = 0;

        for (int i = 0; i < mask.length(); i++) {
            byteMask = mask.substring(i, i + 1);
            byteValue = value.length() > pos ? value.substring(pos, pos + 1) : "";

            if (byteMask.contains("0") || byteMask.contains("#") || byteMask.contains("X") || byteMask.contains("!") || byteMask.contains("*")) {

                if (byteMask.contains("0") && byteValue.trim().length() == 0) {
                    byteValue = "0";
                } else if (byteMask.contains("#") && byteValue.trim().length() == 0) {
                    byteValue = "";
                } else if (byteMask.contains("!")) {
                    byteValue = byteValue.toUpperCase();
                } else if (byteMask.contains("*") && byteValue.trim().length() != 0) {
                    byteValue = "*";
                }

                if (byteValue.length() > 0) {
                    result.append(byteValue);
                }

                pos++;

            } else {

                result.append(byteMask);

                if (byteValue.equals(byteMask)) {
                    pos++;
                }
            }
        }

        return result.toString();
    }

}
