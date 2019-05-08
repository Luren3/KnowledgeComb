package com.magicfrost.knowledgecomb.thread

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.magicfrost.knowledgecomb.R
import okhttp3.internal.Util
import java.util.concurrent.ExecutorService
import java.util.concurrent.SynchronousQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

/**
 *
 * Created by MagicFrost.
 *
 */
class ThreadActivity:AppCompatActivity() {

    val TAG = "ThreadActivity"

    lateinit var executorService: ExecutorService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thread)

        executorService = ThreadPoolExecutor(0, Integer.MAX_VALUE, 60, TimeUnit.SECONDS,
            SynchronousQueue(), Util.threadFactory("OkHttp Dispatcher", false)
        )
        executorService.execute(Runnable {
            Log.e(TAG,"-------")
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        executorService.shutdown()
    }
}