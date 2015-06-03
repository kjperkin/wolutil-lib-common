package com.lightbulbz.net;

public class MacAddress
{
    private byte[] addrBytes;

    public static MacAddress parseMacAddress(String addr) throws MacAddressFormatException {
        String[] parts = addr.split("\\s*:\\s*", 0);
        if (parts.length != 6) {
            throw new MacAddressFormatException("Mac address must consist of 6 hex numbers separated by \":\"");
        }

        byte[] addrBytes = new byte[6];
        for (int i = 0; i < 6; ++i) {
            try {
                addrBytes[i] = MacAddress.parseByte(parts[i], 16);
            } catch (java.lang.NumberFormatException ex) {
                throw new MacAddressFormatException("Invalid hex byte " + parts[i], ex);
            }
        }
        return new MacAddress(addrBytes);
    }

    public static MacAddress fromBytes(byte[] bs) throws java.lang.IllegalArgumentException {
        return new MacAddress(bs);
    }

    public MacAddress(byte[] bs) throws java.lang.IllegalArgumentException {
        if (bs.length != 6) {
            throw new java.lang.IllegalArgumentException("Mac address must be exactly six (6) bytes.");
        }

        addrBytes = bs.clone();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        // Append each byte followed by a ':'
        for (byte b : addrBytes) {
            sb.append(String.format("%02x:", b));
        }

        // Trim the trailing ':'
        return sb.substring(0,sb.length()-1);
    }

    public byte[] getAddressBytes()
    {
        return addrBytes.clone();
    }

    static byte parseByte(String s, int radix) {
        return (byte) Integer.parseInt(s, radix);
    }

}
