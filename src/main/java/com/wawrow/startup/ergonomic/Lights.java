package com.wawrow.startup.ergonomic;

/**
 * Created by Wawrów on 21.09.2017.
 */
public class Lights{}/*
public class Lights implements Runnable{
    @Override
    public void run() {
        Date date = new Date();
        UDPSender udp = new UDPSender(7777, "192.168.0.10");
        byte[] val = new byte[2];

        while(true){
            val[1] = 50;
            udp.udpSend(val);
            sleep(1000);
            val[1] = 25;
            udp.udpSend(val);
            sleep(1000);
            val[1] = 10;
            udp.udpSend(val);
            sleep(1000);
        }

    }
    private void sleep(int millis){
        try {Thread.sleep(millis);} catch (InterruptedException e) {e.printStackTrace();}
    }
    public static void main(String args[]){
        Lights m1=new Lights();
        Thread t1 =new Thread(m1);
        t1.start();
    }
}
*/