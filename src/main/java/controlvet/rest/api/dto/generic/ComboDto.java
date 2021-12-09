package controlvet.rest.api.dto.generic;

public class ComboDto {
    private Object value;

    private String label;

    public ComboDto() {
    }

    public ComboDto(Object value, String label, String conditionalLabel) {
        super();
        this.value = value;
        this.label = (conditionalLabel == null || conditionalLabel.trim().isEmpty())
                ? String.valueOf(value) + "-" + label
                : conditionalLabel.trim() + "-" + label;
    }

    public ComboDto(Object value, String label) {
        super();
        this.value = value;
        this.label = label != null ? label.trim() : label;
    }

    public String getLabel() {
        return this.label;
    }

    public Object getValue() {
        return this.value;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String toString() {
        return "ComboDto [value=" + this.value + ", label=" + this.label + "]";
    }
}
