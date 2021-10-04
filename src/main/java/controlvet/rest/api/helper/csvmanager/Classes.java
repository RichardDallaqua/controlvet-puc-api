package controlvet.rest.api.helper.csvmanager;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

public enum Classes {
    STRING(String.class.getName()),
    DATE(Date.class.getName()),
    INTEGER(Integer.class.getName()),
    FLOAT(Float.class.getName()),
    DOUBLE(Double.class.getName()),
    LONG(Long.class.getName()),
    BIGDECIMAL(BigDecimal.class.getName()),
    BIGINTEGER(BigInteger.class.getName());

    private final String className;

    private Classes(String className) {
        this.className = className;
    }

    public String className() {
        return this.className;
    }
}