
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LengthTest
{
    Length l1 = new Length(10, LengthUnit.M);
    Length l2 = new Length(1000, LengthUnit.CM);
    Length l3 = new Length(20, LengthUnit.M);
    Length l4 = new Length(0.5, LengthUnit.M);
    @Test
    void testToString()
    {
        assertEquals("10M", l1.toString());
        assertEquals("1000CM", l2.toString());
        assertEquals("20M", l3.toString());
        assertEquals("0.5M", l4.toString());
    }
    @Test
    void testPlus()
    {
        Length res = new Length(20, LengthUnit.M);
        assertEquals(res, l1.plus(l2));

        res = new Length(2000, LengthUnit.CM);
        assertEquals(res, l2.plus(l1));

        res = new Length(1050, LengthUnit.CM);
        assertEquals(res, l4.plus(l2));

        res = new Length(10.5, LengthUnit.M);
        assertEquals(res, l4.plus(l2));
    }
    @Test
    void testMinus()
    {
        Length res = new Length(0, LengthUnit.M);
        assertEquals(res, l1.minus(l2));

        res = new Length(0, LengthUnit.CM);
        assertEquals(res, l2.minus(l1));
    }
    @Test
    void testConvert()
    {
        assertEquals(l2, l1.convert(LengthUnit.CM));
        assertEquals(l1, l2.convert(LengthUnit.M));
    }
    @Test
    void testBetween()
    {
        assertEquals(-10000, LengthUnit.MM.between(l3, l2), 0.00001);
        assertEquals(10, LengthUnit.M.between(l2, l3), 0.00001);
        assertEquals(-1000, LengthUnit.CM.between(l3, l2), 0.00001);
        assertEquals(950, LengthUnit.CM.between(l4, l2), 0.00001);
        assertEquals(9.5, LengthUnit.M.between(l4, l2), 0.00001);
        assertEquals(-19500, LengthUnit.MM.between(l3, l4), 0.00001);
        assertEquals(-1950, LengthUnit.CM.between(l3, l4), 0.00001);
    }
    @Test
    void testEquals()
    {
        Length res = new Length(0, LengthUnit.CM);
        assertTrue(res.equals(l2.minus(l1)));

        res = new Length(0, LengthUnit.M);
        assertTrue(res.equals(l2.minus(l1)));

        res = new Length(0, LengthUnit.MM);
        assertTrue(res.equals(l2.minus(l1)));
    }
}

