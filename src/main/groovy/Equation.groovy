class Equation {

    String getValue() {
        return value
    }

    void setValue(String value) {
        this.value = value
    }

    Equation(String value) {
        this.value = value
    }
    String value;


    @Override
    public String toString() {
        return "Equation{" +
                "value='" + value + '\'' +
                '}';
    }
}
