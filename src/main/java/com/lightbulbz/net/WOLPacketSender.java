package com.lightbulbz.net;

import java.io.IOException;
import java.net.*;

/**
 * Created by kevin on 6/3/15.
 */
public class WOLPacketSender {
    private static final byte FF = (byte) 0xff;
    private static final byte[] PAYLOAD_START = {FF, FF, FF, FF, FF, FF};
    private static final int WOL_PACKET_SIZE = 6 + 16 * 6;
    private DatagramPacket packet;
    private final DatagramSocket sock;

    public WOLPacketSender(InetAddress targetAddr, MacAddress targetMac) throws SocketException {
        sock = new DatagramSocket();
        byte[] packetBytes = new byte[WOL_PACKET_SIZE];
        byte[] macBytes = targetMac.getAddressBytes();
        System.arraycopy(PAYLOAD_START, 0, packetBytes, 0, PAYLOAD_START.length);
        for (int i = PAYLOAD_START.length; i < WOL_PACKET_SIZE; i += macBytes.length) {
            System.arraycopy(macBytes, 0, packetBytes, i, macBytes.length);
        }
        packet = new DatagramPacket(packetBytes, packetBytes.length, new InetSocketAddress(targetAddr, 9));
    }

    public void sendPacket() throws IOException {
        sock.send(packet);
    }
}
