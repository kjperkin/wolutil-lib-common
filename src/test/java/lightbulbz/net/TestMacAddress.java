package lightbulbz.net;

import junit.framework.TestCase;
import org.junit.Assert;

/**
 * Created by kevin on 5/28/15.
 */
public class TestMacAddress extends TestCase {

    private static byte[] testBytes = {(byte)0xDE, (byte)0xAD, (byte)0xBE, (byte)0xEF, (byte)0x00, (byte)0xFF};

    public void testMacAddressFromBytes() {
        MacAddress addr = new MacAddress(testBytes);
        Assert.assertArrayEquals(testBytes, addr.getAddressBytes());
    }

    public void testMacAddressFromString() throws Exception {
        MacAddress macAddress = MacAddress.parseMacAddress("de:ad:be:ef:00:ff");
        MacAddress expected = MacAddress.fromBytes(testBytes);
        Assert.assertArrayEquals(expected.getAddressBytes(), macAddress.getAddressBytes());
    }

    public void testParseByte() throws Exception {
        Assert.assertEquals((byte)0, MacAddress.parseByte("00", 16));
        Assert.assertEquals((byte)0xff, MacAddress.parseByte("ff", 16));
        Assert.assertEquals((byte)0x7f, MacAddress.parseByte("7f", 16));
        Assert.assertEquals((byte)0, MacAddress.parseByte("0", 10));
        Assert.assertEquals((byte)255, MacAddress.parseByte("255", 10));
        Assert.assertEquals((byte)-128, MacAddress.parseByte("-128", 10));
        Assert.assertEquals((byte)-128, MacAddress.parseByte("-128", 10));
        Assert.assertEquals((byte)127, MacAddress.parseByte("127", 10));
    }
}
