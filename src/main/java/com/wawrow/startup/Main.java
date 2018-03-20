package com.wawrow.startup;

import com.wawrow.startup.audio.Stream;
import com.wawrow.startup.network.soc.SOCThread;
import com.wawrow.startup.services.logService;

public class Main {
    private static Stream audioStream;
    private static Thread logService;
    private static SOCThread server;
    public static void main(String[] args) {

//        SQL sql = new SQL("INSERT INTO test.temp_main_room (TEMP) VALUES(23.4)");
        logService = new Thread(new logService());
        logService.start();
//        Stream str = new Stream();
        audioStream = new Stream();
        server = new SOCThread(9000, audioStream);
        new Thread(server).start();

        }

    public void shutdown(){
        logService.stop();
        server.stop();
        audioStream.stop();
    }
    }

