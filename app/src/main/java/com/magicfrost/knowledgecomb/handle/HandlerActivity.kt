package com.magicfrost.knowledgecomb.handle

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.magicfrost.knowledgecomb.R

/**
 * Created by MagicFrost
 */
class HandlerActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_handler)

        Log.e("1","{${Thread.currentThread()}}")

        Thread(Runnable {

            Log.e("2","{${Thread.currentThread()}}")

            Looper.prepare()

            val handler = object :Handler(Looper.getMainLooper()){
                override fun handleMessage(msg: Message?) {
                    super.handleMessage(msg)

                    Log.e("3","{${msg?.what}},{${Thread.currentThread()}}")
                }
            }


            Thread(Runnable {
                Log.e("4","{${Thread.currentThread()}}")
                handler.sendEmptyMessage(1)

                handler.post{
                    Log.e("5","{${Thread.currentThread()}}")
                }
            }).start()

            Looper.loop()
        }).start()
    }
}