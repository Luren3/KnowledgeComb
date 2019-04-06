package com.magicfrost.knowledgecomb.ipc;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.magicfrost.knowledgecomb.R;

import java.io.*;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by MagicFrost
 */
public class SocketActivity extends AppCompatActivity {

    private static final int MESSAGE_RECEIVE_NEW_MSG = 1;
    private static final int MESSAGE_SOCKET_CONNECTED = 2;

    private Button mSend;
    private TextView mContent;
    private EditText mMSG;

    private PrintWriter mPrintWriter;
    private Socket clientSocket;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case MESSAGE_RECEIVE_NEW_MSG:
                    mContent.setText(""+mContent.getText() + "\n" + msg.obj);
                    break;
                case MESSAGE_SOCKET_CONNECTED:
                    mSend.setEnabled(true);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ipc_socket);

        startService(new Intent(this,SocketService.class));

        mSend = findViewById(R.id.send);
        mContent = findViewById(R.id.content);
        mMSG = findViewById(R.id.msg);
        mSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String msg = mMSG.getText().toString();
                if (mPrintWriter != null && !TextUtils.isEmpty(msg)){
                    new Thread(){
                        @Override
                        public void run() {
                            mPrintWriter.println(msg);
                        }
                    }.start();
                    String time = formatDateTime(System.currentTimeMillis());
                    String content = "迷途者 " + time + ":" + msg;
                    mContent.setText(""+mContent.getText() + "\n" + content);
                }
            }
        });

        new Thread(){
            @Override
            public void run() {
                connectTCPServer();
            }
        }.start();
    }

    private String formatDateTime(long time){
        return new SimpleDateFormat("HH:mm:ss").format(new Date());
    }

    private void connectTCPServer(){
        Socket socket = null;
        while (socket == null){
            try {
                socket = new Socket("localhost",8868);
                clientSocket = socket;
                mPrintWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);
                mHandler.sendEmptyMessage(MESSAGE_SOCKET_CONNECTED);
            } catch (IOException e) {
                //重连机制
                SystemClock.sleep(1000);
                e.printStackTrace();
            }
        }
        try {
            //接收服务端消息
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while (!this.isFinishing()){
                String msg = reader.readLine();
                if (msg != null){
                    String time = formatDateTime(System.currentTimeMillis());
                    String content = "魔鬼 " + time + ":" + msg;
                    mHandler.obtainMessage(MESSAGE_RECEIVE_NEW_MSG,content).sendToTarget();
                }
            }
            reader.close();
            mPrintWriter.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onDestroy() {
        if (clientSocket != null){
            try {
                clientSocket.shutdownInput();
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        super.onDestroy();
    }
}
