package controlvet.rest.api.enums;

public enum EspecieEnum {
    NENHUM(0, "Nenhum"), CANIDEO(1, "Canideo"), FELINO(2, "Felino");

    private final Integer value;
    private final String text;

    public static EspecieEnum valueOf(final int value) {
        for (final EspecieEnum type : values()) {
            if (type.value == value) {
                return type;
            }
        }
        return EspecieEnum.NENHUM;
    }


    EspecieEnum(final Integer value, final String text) {
        this.value = value;
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

    public Integer getValue() {
        return this.value;
    }

    public Integer value() {
        return this.value;
    }
}