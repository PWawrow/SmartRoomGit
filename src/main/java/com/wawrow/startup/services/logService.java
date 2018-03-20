package com.wawrow.startup.services;

import com.pi4j.component.temperature.TemperatureSensor;
import com.pi4j.component.temperature.impl.TmpDS18B20DeviceType;
import com.pi4j.io.w1.W1Device;
import com.pi4j.io.w1.W1Master;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import com.wawrow.startup.network.sql.SQL;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by Wawrów on 28.01.2018.
 */
public class logService implements Runnable{

    public void run() {
        while(true){
            float temp = getDSVal();
            float hum = getHUMVal();
            SQL sql = new SQL("INSERT INTO logs.Temp_Hum_Main_Room (TEMP, HUM) VALUES(" + temp + ", " + hum +')');
            try {
                Thread.sleep(300000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public float getHUMVal(){
        float hum = 0;
        String fromSensor = "0";
        try{
            fromSensor = execCmd("sudo python /home/pi/etc/dht11/lib/examples/AdafruitDHT.py 11 9");
            }catch(IOException e){}
        hum = Float.parseFloat(fromSensor);
        return hum;
    }


    private float getDSVal(){
        float temp = 0;
        W1Master master = new W1Master();
        List<W1Device> w1Devices = master.getDevices(TmpDS18B20DeviceType.FAMILY_CODE);
        for (W1Device device : w1Devices) {
            temp = (float) ((TemperatureSensor) device).getTemperature();
        }

        return temp;
    }
    public static String execCmd(String cmd) throws java.io.IOException {
        java.util.Scanner s = new java.util.Scanner(Runtime.getRuntime().exec(cmd).getInputStream()).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }
}
