package controlvet.rest.api.helper;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class NumberManager {
    private static NumberManager instance;
    public static final Locale LOCALE = new Locale("pt", "BR");

    public NumberManager() {
    }

    public static NumberManager getInstance() {
        if (instance == null) {
            instance = new NumberManager();
        }

        return instance;
    }

    public Boolean isNullOrZero(final BigDecimal... arrayBigDecimal) {
        for (final BigDecimal item : arrayBigDecimal) {
            if (item == null || item.compareTo(BigDecimal.ZERO) == 0) {
                return true;
            }
        }
        return false;
    }

    public Boolean isNullOrZero(final BigInteger... arrayBigInteger) {
        for (final BigInteger item : arrayBigInteger) {
            if (item == null || item.equals(0)) {
                return true;
            }
        }
        return false;
    }

    public Boolean isNullOrZero(final Double... arrayDouble) {
        for (final Double item : arrayDouble) {
            if (item == null || item.equals(0.)) {
                return true;
            }
        }
        return false;
    }

    public Boolean isNullOrZero(final Integer... arrayInteger) {
        for (final Integer item : arrayInteger) {
            if (item == null || item.equals(0)) {
                return true;
            }
        }
        return false;
    }
    public String formatCurrencyValue(Double value, String mask) {


        Double valor = value;

        if (valor == null) {
            valor = 0.0;
        }

        final DecimalFormat df = new DecimalFormat(mask);
        final DecimalFormatSymbols dfs = new DecimalFormatSymbols(LOCALE);
        dfs.setDecimalSeparator(',');
        df.setDecimalFormatSymbols(dfs);

        return df.format(valor);
    }

    public String formatWeightValue(Double value, String mask) {
        Double valor = value;

        if (valor == null) {
            valor = 0.0;
        }

        final DecimalFormat df = new DecimalFormat(mask);
        final DecimalFormatSymbols dfs = new DecimalFormatSymbols(LOCALE);
        dfs.setDecimalSeparator('.');
        df.setDecimalFormatSymbols(dfs);

        return df.format(valor);
    }

}
