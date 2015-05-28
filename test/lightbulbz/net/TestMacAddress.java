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
}
