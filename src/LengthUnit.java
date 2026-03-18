public enum LengthUnit {
    M(1000), CM(10), MM(1);
    private final int mmRatio;

    public int getMmRatio() {
        return mmRatio;
    }

    LengthUnit(int valueInMm) {
        this.mmRatio = valueInMm;
    }

    public static double convert(double amount, LengthUnit from, LengthUnit to) {
        return amount * from.getMmRatio() / (double) to.getMmRatio();
    }

    public double between(Length l1, Length l2) {
        double l1mm = l1.getValue() * l1.getUnit().getMmRatio();
        double l2mm = l2.getValue() * l2.getUnit().getMmRatio();
        double intervalMM = l2mm - l1mm;
        return intervalMM / (double) this.getMmRatio();
    }
}
