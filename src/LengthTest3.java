import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LengthTest3 {
    Length l1 = new Length(10, LengthUnit.M);      // 10000 mm
    Length l2 = new Length(1000, LengthUnit.CM);   // 10000 mm
    Length l3 = new Length(20, LengthUnit.M);      // 20000 mm

    @Test
    void testToString() {
        assertEquals("10M", l1.toString());
        assertEquals("1000CM", l2.toString());
    }

//    @Test
//    void testGetters() {
//        assertEquals(10, l1.getCount());
//        assertEquals(LengthUnit.M, l1.getUnit());
//
//        assertEquals(10000, l1.getCount(LengthUnit.MM));
//        assertEquals(1000, l1.getCount(LengthUnit.CM));
//        assertEquals(10, l1.getCount(LengthUnit.M));
//    }

    @Test
    void testPlus() {
        Length res = new Length(20, LengthUnit.M);
        assertEquals(res, l1.plus(l2));
    }

    @Test
    void testMinus() {
        Length res = new Length(0, LengthUnit.M);
        assertEquals(res, l1.minus(l2));

        res = new Length(10, LengthUnit.M);
        assertEquals(res, l3.minus(l1));
    }

    @Test
    void testConvert() {
        assertEquals(new Length(1000, LengthUnit.CM), l1.convert(LengthUnit.CM));
        assertEquals(new Length(10, LengthUnit.M), l2.convert(LengthUnit.M));
        assertEquals(new Length(10000, LengthUnit.MM), l1.convert(LengthUnit.MM));
    }

    @Test
    void testBetween() {
        assertEquals(-10000, LengthUnit.MM.between(l3, l2));
        assertEquals(10, LengthUnit.M.between(l2, l3));
        assertEquals(-1000, LengthUnit.CM.between(l3, l2));
    }

    @Test
    void testEquals() {
        assertEquals(l1, l2);
        assertEquals(new Length(0, LengthUnit.MM), l2.minus(l1).convert(LengthUnit.MM));
    }

    @Test
    void testHashCode() {
        Length a = new Length(10, LengthUnit.M);
        Length b = new Length(1000, LengthUnit.CM);

        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode());
    }

    @Test
    void testConvertToSameUnit() {
        Length res = l1.convert(LengthUnit.M);
        assertEquals(l1, res);
    }

    @Test
    void testPlusWithDifferentUnits() {
        Length mm = new Length(500, LengthUnit.MM);
        Length m = new Length(1, LengthUnit.M);

        Length expected = new Length(1500, LengthUnit.MM);
        assertEquals(expected, mm.plus(m));
    }

    @Test
    void testMinusWithDifferentUnits() {
        Length cm = new Length(150, LengthUnit.CM);
        Length m = new Length(1, LengthUnit.M);

        Length expected = new Length(50, LengthUnit.CM);
        assertEquals(expected, cm.minus(m));
    }

    @Test
    void testBetweenSameValues() {
        assertEquals(0, LengthUnit.M.between(l1, l2));
    }

//    @Test
//    void testConstructorNegativeCount() {
//        assertThrows(IllegalArgumentException.class,
//                () -> new Length(-1, LengthUnit.M));
//    }

    @Test
    void testConstructorNullUnit() {
        assertThrows(IllegalArgumentException.class,
                () -> new Length(10, null));
    }

    @Test
    void testPlusNull() {
        assertThrows(IllegalArgumentException.class,
                () -> l1.plus(null));
    }

    @Test
    void testMinusNull() {
        assertThrows(IllegalArgumentException.class,
                () -> l1.minus(null));
    }

    @Test
    void testConvertNull() {
        assertThrows(IllegalArgumentException.class,
                () -> l1.convert(null));
    }

//    @Test
//    void testGetCountNull() {
//        assertThrows(IllegalArgumentException.class,
//                () -> l1.getCount(null));
//    }

    @Test
    void testBetweenNull() {
        assertThrows(IllegalArgumentException.class,
                () -> LengthUnit.M.between(null, l1));

        assertThrows(IllegalArgumentException.class,
                () -> LengthUnit.M.between(l1, null));
    }

    @Test
    void testEqualsDifferentObjects() {
        assertNotEquals(l1, null);
        assertNotEquals(l1, "10000MM");
    }
}