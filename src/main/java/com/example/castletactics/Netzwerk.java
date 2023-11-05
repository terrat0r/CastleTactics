package com.example.castletactics;
import java.io.*;
import java.net.*;


public class Netzwerk {
    public static String GetIP() {
        try {
            InetAddress localhost = InetAddress.getLocalHost();
            System.out.println("System IP Address : " + (localhost.getHostAddress()).trim());
            return (localhost.getHostAddress()).trim();
        }
        catch (UnknownHostException e) {
            System.err.println(e.getClass().getCanonicalName());
        }
        return null;
    }
    ServerSocket ss;
    Socket s;
    InputStreamReader in;
    OutputStreamWriter out;
    BufferedReader bf;
    BufferedWriter bw;
    PrintWriter pr;
    public Netzwerk() {
        try {
            ss = new ServerSocket(4998);
            s = ss.accept();

            System.out.println("client connected");

            in = new InputStreamReader(s.getInputStream());
            bf = new BufferedReader(in);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
    }

    public Netzwerk(int[]IP) {
        try {
            s = new Socket(IP[0] + "." + IP[1] + "." + IP[2] + "." + IP[3], 4998);

            in = new InputStreamReader(s.getInputStream());
            bf = new BufferedReader(in);
            out = new OutputStreamWriter(s.getOutputStream());
            bw = new BufferedWriter(out);
        } catch(IOException e) {
            e.printStackTrace();
            return;
        }
    }


    public void senden(String str) {
        try {
            System.err.println("Print");
            pr = new PrintWriter(s.getOutputStream());
            System.err.println("Print");
            pr.println(str);
            System.err.println("Print");
            //pr.flush();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public String empfangen(){

        try {
            String str = bf.readLine();
            System.out.println("server: " + str);
            return str;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}