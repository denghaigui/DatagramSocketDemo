package com.example.udp_chat;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * @author: Denghg  @createDate: 2021/11/5 下午4:33
 * @description
 **/
public class UdpServer {
    private InetAddress mInetAddress;
    private int mPort = 7777;//尽可能用5000以后的

    private DatagramSocket mSocket;

    private Scanner mScanner;

    public UdpServer() {
        try {
            mInetAddress = InetAddress.getLocalHost();
            System.out.println("Server mInetAddress is:"+mInetAddress.getHostAddress());
            mSocket = new DatagramSocket(mPort,mInetAddress);
            mScanner = new Scanner(System.in);
            mScanner.useDelimiter("\n");//指定控制面板的输入以换行来结束
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void start() {
        while (true) {
            byte[] buf = new byte[1024];
            DatagramPacket datagramPacket = new DatagramPacket(buf, buf.length);
            try {
                mSocket.receive(datagramPacket);
                InetAddress address = datagramPacket.getAddress();
                int port = datagramPacket.getPort();
                //收到的数据
                byte[] data = datagramPacket.getData();
                String clientMsg = new String(data);
                System.out.println("address = " + address +
                        ", port = " + port + ",(Client's) msg = " + clientMsg);
                //回数据给client
                String returnedMsg = mScanner.next();
                byte[] returnedMsgBytes = returnedMsg.getBytes();//将String转换成byte数组
                DatagramPacket datagramPackeRsp = new DatagramPacket(returnedMsgBytes, returnedMsgBytes.length, address, port);
                mSocket.send(datagramPackeRsp);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new UdpServer().start();
    }
}
