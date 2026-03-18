public enum LengthUnit {
    M(1000), CM(10), MM(1);
    int mmRatio;
    LengthUnit(int valueInMm) {
        this.mmRatio = valueInMm;
    }

    public static double convert(double amount, LengthUnit from, LengthUnit to) {
        return amount * from.mmRatio / (double) to.mmRatio;
    }
    public double between (Length l1, Length l2) {
        double l1mm = l1.convert(MM).getValue();
        double l2mm = l2.convert(MM).getValue();
        double intervalMM = l2mm - l1mm;
        return intervalMM / (double) this.mmRatio;
    }
}
