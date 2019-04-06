package com.magicfrost.knowledgecomb.ipc;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

/**
 * Created by MagicFrost.
 */
public class SocketService extends Service {

    private boolean isDestroyed = false;

    private String[] mDefault = new String[]{
            "在吗,今年的冬天很冷",
            "面对现在的紧迫感，唯有自己积累够深，不能不惧。",
            "希望你能加油，坚持自己"
    };

    @Override
    public void onCreate() {
        new Thread(new TCPServer()).start();
        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        isDestroyed = true;
        super.onDestroy();
    }

    private class TCPServer implements Runnable {

        @Override
        public void run() {
            ServerSocket serverSocket = null;
            try {
                serverSocket = new ServerSocket(8868);

            } catch (IOException e) {
                Log.e("SocketError:","establish tcp server failed,port:8868");
                e.printStackTrace();
                return;
            }

            while (!isDestroyed){
                try {
                    final Socket client = serverSocket.accept();
                    new Thread(){
                        @Override
                        public void run() {
                            try {
                                responseClient(client);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void responseClient(Socket client) throws IOException{
        //用于接收客户端消息
        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        //用于向客户端发送消息
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(client.getOutputStream())),true);
        out.println("你好，迷途者。");
        while (!isDestroyed){
            String str = in.readLine();
            Log.e("迷途者：",""+str);
            if (str == null){
                //客户端断开连接
                break;
            }
            int i = new Random().nextInt(mDefault.length);
            out.println(mDefault[i]);
            Log.e("魔鬼：",mDefault[i]);
        }
        Log.e("迷途者：","再见");
        in.close();
        out.close();
        client.close();
    }
}
