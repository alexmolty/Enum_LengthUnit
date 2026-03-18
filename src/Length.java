import java.util.Objects;

public class Length {
    private double value;
    private LengthUnit unit;

    public Length(double value, LengthUnit unit) {
        this.value = value;
        this.unit = unit;
    }


    @Override
    public String toString() {
        String s = String.valueOf(value);
        if (s.endsWith(".0")) {
            return s.substring(0, s.length() - 2) + unit;
        } else {
            return s + unit;
        }
    }

    public double getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public LengthUnit getUnit() {
        return unit;
    }

    public void setUnit(LengthUnit unit) {
        this.unit = unit;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        double thisValueInMM = this.value * this.unit.mmRatio;
        double otherValueInMM = ((Length) o).value * ((Length)o).unit.mmRatio;
        return Math.abs(thisValueInMM - otherValueInMM) < 0.001;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, unit);
    }

    public Length plus(Length other) {
        double otherConverted = LengthUnit.convert(other.value, other.unit, this.unit);
        return new Length(this.value + otherConverted, this.unit);
    }

    public Length minus(Length other) {
        double otherConverted = LengthUnit.convert(other.value, other.unit, this.unit);
        return new Length(this.value - otherConverted, this.unit);
    }

    public Length convert(LengthUnit unit) {
        double convertedValue = LengthUnit.convert(this.value, this.unit, unit);
        return new Length(convertedValue, unit);
    }
}
