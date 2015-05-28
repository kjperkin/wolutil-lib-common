package lightbulbz.net.wol;

import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.NetworkInterface;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.util.Set;
import java.util.HashSet;
import java.net.SocketException;
import java.util.Enumeration;

import lightbulbz.net.MacAddress;
import lightbulbz.net.MacAddressFormatException;

public class WolUtil
{
    public static void main(String[] argv) {
        Set<InetAddress> broadcastAddresses = new HashSet<InetAddress>();
        try {
            Enumeration<NetworkInterface> ifaces = NetworkInterface.getNetworkInterfaces();
            while (ifaces.hasMoreElements()) {
                NetworkInterface iface = ifaces.nextElement();
                if (iface.isUp() && !iface.isLoopback() && !iface.isPointToPoint()) {
                    for (InterfaceAddress addr : iface.getInterfaceAddresses()) {
                        System.out.println("Found interface address " + addr.getAddress().toString());
                        if (addr.getBroadcast() != null) {
                            System.out.println("Broadcast address is " + addr.getBroadcast().toString());
                            broadcastAddresses.add(addr.getBroadcast());
                        }
                    }
                }
            }
        } catch (SocketException ex) {
            ex.printStackTrace();
        }
        for (InetAddress addr : broadcastAddresses) {
            System.out.println(addr);
        }
        MacAddress amac = new MacAddress(new byte[]{(byte)0xDE, (byte)0xAD, (byte)0xBE, (byte)0xEF, (byte)0x00, (byte)0xFF});
        System.out.println(amac);
    }

    public void SendWolPacket(MacAddress mac) throws SocketException {
        DatagramSocket mySock = new DatagramSocket();
    }
}

