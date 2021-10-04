package controlvet.rest.api.enums;

public enum SexoEnum {
    NENHUM(0, "Nenhum"), MACHO(1, "Macho"), FEMEA(2, "Fêmea");

    private final Integer value;
    private final String text;

    public static SexoEnum valueOf(final int value) {
        for (final SexoEnum type : values()) {
            if (type.value == value) {
                return type;
            }
        }
        return SexoEnum.NENHUM;
    }


    SexoEnum(final Integer value, final String text) {
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