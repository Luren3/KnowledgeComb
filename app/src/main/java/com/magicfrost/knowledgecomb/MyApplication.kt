package com.magicfrost.knowledgecomb

import android.app.Application
import android.os.Debug
import android.os.Environment
import android.util.Log
import java.io.File

/**
 *
 * Created by MagicFrost.
 *
 */
class MyApplication:Application() {

    override fun onCreate() {
        super.onCreate()
        //通过TraceView来统计启动时间
        val file = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath()+"/app.trace")
        Log.e("trace",file.path)
        Debug.startMethodTracing(file.path)
        Thread.sleep(3000)
        Debug.stopMethodTracing()
    }
}