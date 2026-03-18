import java.util.Objects;

public class Length {
    private final double value;
    private final LengthUnit unit;

    public Length(double value, LengthUnit unit) {
        this.value = value;
        this.unit = unit;
    }


    @Override
    public String toString() {
        if (value == (long) value) {
            return String.valueOf((long) value) + unit;
        }
        return value + "" + unit;
    }

    public double getValue() {
        return value;
    }

    public LengthUnit getUnit() {
        return unit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Length)) return false;
        Length other = (Length) o;
        long thisMm = Math.round(this.value * this.unit.getMmRatio());
        long otherMm = Math.round(other.value * other.unit.getMmRatio());
        return thisMm == otherMm;
    }

    @Override
    public int hashCode() {
        long mm = Math.round(value * unit.getMmRatio());
        return Long.hashCode(mm);
    }

    public Length plus(Length other) {
        Objects.requireNonNull(other);
        double otherConverted = LengthUnit.convert(other.value, other.unit, this.unit);
        return new Length(this.value + otherConverted, this.unit);
    }

    public Length minus(Length other) {
        Objects.requireNonNull(other);
        double otherConverted = LengthUnit.convert(other.value, other.unit, this.unit);
        return new Length(this.value - otherConverted, this.unit);
    }

    public Length convert(LengthUnit unit) {
        double convertedValue = LengthUnit.convert(this.value, this.unit, unit);
        return new Length(convertedValue, unit);
    }
}
