package com.wawrow.startup.network.soc;

import com.wawrow.startup.audio.Stream;

import java.io.*;
import java.net.Socket;
import java.nio.charset.Charset;

/**

 */
public class WorkerRunnable implements Runnable{

    protected Socket clientSocket = null;
    protected String serverText   = null;
    protected Stream audioStream;
    protected int id;

    public WorkerRunnable(Socket clientSocket, String serverText, Stream audioStream, int id) {
        this.clientSocket = clientSocket;
        this.serverText   = serverText;
        this.audioStream = audioStream;
        this.id = id;
    }

    public void run() {
        try {
            System.out.println("####SERVER THREAD INSTANCE no."+id+" STARTED####");
            InputStream input = clientSocket.getInputStream();
            OutputStream output = clientSocket.getOutputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            PrintStream writer = new PrintStream(output);
            boolean intterrupted = false;
            String line = reader.readLine();
            if (line.equals("**")) {
                writer.println("**");
                while (clientSocket.isBound() && !intterrupted) {
                    String response = reader.readLine();
                    switch (response) {
                        case "strCon":
                            strCon(writer, reader);
                            break;
                        case "strDis":
                            strDis(writer, reader);
                            break;
                        case "end":
                            intterrupted = true;
                            break;
                        case "**":
                            writer.println("**");
                            break;

                    }
                }
            }
            input.close();
            output.close();
            reader.close();
            writer.close();
            clientSocket.close();
            System.out.println("####SERVER THREAD INSTANCE no."+id+" ENDED####");
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    private void strCon(PrintStream out, BufferedReader in) throws IOException{
        if(!audioStream.isConn()) {
            out.println("url");
            String address = in.readLine();
            if (address.indexOf("http") > -1) {
                audioStream.start(address);
                out.println("dn");
            }
        }else{
            out.println("err: 1");
        }


    }
    private void strDis(PrintStream out, BufferedReader in){
        if(audioStream.isConn()) {
            audioStream.stop();
            out.println("dn");
        }else {
            out.println("err: 1");
        }

    }

}
