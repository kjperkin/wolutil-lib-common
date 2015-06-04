package com.lightbulbz.net;

import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by kevin on 6/3/15.
 */
public class NetUtils {
    public static Collection<InetAddress> getBroadcastAddresses() {
        Set<InetAddress> broadcastAddresses = new HashSet<>();
        try {
            Enumeration<NetworkInterface> ifaces = NetworkInterface.getNetworkInterfaces();
            while (ifaces.hasMoreElements()) {
                NetworkInterface iface = ifaces.nextElement();
                if (iface.isUp() && !iface.isLoopback() && !iface.isPointToPoint()) {
                    for (InterfaceAddress addr : iface.getInterfaceAddresses()) {
                        if (addr.getBroadcast() != null) {
                            broadcastAddresses.add(addr.getBroadcast());
                        }
                    }
                }
            }
        } catch (SocketException ex) {
            ex.printStackTrace();
        }
        return broadcastAddresses;
    }
}
