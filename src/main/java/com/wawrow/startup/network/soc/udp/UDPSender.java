package com.wawrow.startup.network.soc.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * Created by Wawrów on 21.09.2017.
 */
public class UDPSender {


    private DatagramSocket socket;
    private int port;
    private InetAddress ip;
    private String raddress;

    public UDPSender(int port, String address) {
        this.port = port;
        raddress = address;
        connect();
    }

    private void connect() {
        try {
            InetAddress ip = InetAddress.getByName(raddress);
            socket.connect(ip, port);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void udpSend(byte[] data) {
        DatagramPacket packet = new DatagramPacket(data, data.length, ip, port);
        try {
            socket.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void udpEnd() {
        socket.close();

    }


}
