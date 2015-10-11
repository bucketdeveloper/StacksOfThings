import com.sot.thing.stack.TheQuantificator;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by kevin on 9/28/2015.
 */
public class TheQuantificatorTest {

    @Test
    public void testFriendlyNumberFormatter_notEmpty() {
        assertNotNull(TheQuantificator.friendlyNumberFormatter(new BigDecimal(1.0)));
    }

    @Test
    public void testFriendlyNumberFormatter_emptyPrecision() {
        assertEquals(TheQuantificator.friendlyNumberFormatter(new BigDecimal(10.000)),"10");
    }

    @Test
    public void testFriendlyNumberFormatter_dozen() {
        List<String> validResponses =  new ArrayList<>();
        validResponses.add("A Dozen");
        validResponses.add("12");
        String response = TheQuantificator.friendlyNumberFormatter(new BigDecimal(12.000));
        assertTrue(validResponses.contains(response));
    }

    @Test
    public void testFriendlyNumberFormatter_fractions() {
        String fraction = TheQuantificator.friendlyNumberFormatter(new BigDecimal(1.33));
        assertEquals(fraction,"1-1/3");
        fraction = TheQuantificator.friendlyNumberFormatter(new BigDecimal(2.20));
        assertEquals(fraction,"2-1/4");
        fraction = TheQuantificator.friendlyNumberFormatter(new BigDecimal(6.60));
        assertEquals(fraction,"6-1/2");
        fraction = TheQuantificator.friendlyNumberFormatter(new BigDecimal(0.60));
        assertEquals(fraction,"2/3");
        fraction = TheQuantificator.friendlyNumberFormatter(new BigDecimal(0.20));
        assertEquals(fraction,"1/4");
    }

    @Test
    public void testFriendlyNumberFormatter_high() {
        String fraction = TheQuantificator.friendlyNumberFormatter(new BigDecimal(1.33));
        assertEquals(fraction,"1-1/3");
    }

    @Test
    public void getFillQty_small() {
//
        String value = TheQuantificator.getFillQty(0.026,12.77).getQtyString();
        assertEquals(value,"<sup>1</sup>&frasl;<sub>490</sub>");

        // , 6.0
        value = TheQuantificator.getFillQty(0.75,6.0).getQtyString();
        assertEquals(value,"0.1");
    }

    @Test
    public void getFractions() {
        // 2.752729771373149E-6
        String fraction = TheQuantificator.createFractionString(new BigDecimal(2.752729771373149E-6));
        assertEquals(fraction,"<sup>1</sup>&frasl;<sub>363,000</sub>");

        // 8573.553719008265
        fraction = TheQuantificator.createFractionString(new BigDecimal(1.1663774821669558E-4));
        assertEquals(fraction,"<sup>1</sup>&frasl;<sub>8,600</sub>");

        //0.0012393162393162392
        fraction = TheQuantificator.createFractionString(new BigDecimal(0.0012393162393162392));
        assertEquals(fraction,"<sup>1</sup>&frasl;<sub>810</sub>");

        fraction = TheQuantificator.createFractionString(new BigDecimal(3.0239699999544902222242448790122557831550108E-7));
        assertEquals(fraction,"<sup>1</sup>&frasl;<sub>3,307,000</sub>");
    }

    @Test
    public void testEiffelDuracell() {
        String value = TheQuantificator.getFillQty(286526563512.0,3.21).getQtyString();
        assertEquals(value,"89,260,611,686");
    }

    @Test
    public void testSmallFraction() {
//        String value = TheQuantificator.createFractionString(new BigDecimal(0.002564102564102564));
        String value = TheQuantificator.createFractionString(new BigDecimal(0.002564102564102564));
        assertEquals(value,"<sup>1</sup>&frasl;<sub>390</sub>");
    }
}
