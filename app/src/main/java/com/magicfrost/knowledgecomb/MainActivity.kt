package com.magicfrost.knowledgecomb

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.magicfrost.knowledgecomb.adapter.ListAdapter
import com.magicfrost.knowledgecomb.evenDispatch.EventDispatchActivity
import com.magicfrost.knowledgecomb.handle.HandlerActivity
import com.magicfrost.knowledgecomb.http.OKHttpActivity
import com.magicfrost.knowledgecomb.http.RetrofitActivity
import com.magicfrost.knowledgecomb.ipc.SocketActivity
import com.magicfrost.knowledgecomb.livedatabus.LiveDataBus
import com.magicfrost.knowledgecomb.proxy.ProxyActivity
import com.magicfrost.knowledgecomb.thread.ThreadActivity
import kotlinx.android.synthetic.main.activity_main.*


/**
 * Created by MagicFrost
 */
class MainActivity : AppCompatActivity() {

    private var adapter: ListAdapter? = null

    private val listData = arrayListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()

        LiveDataBus.getInstance().with("test", String::class.java).observe(this,object :Observer<String>{
            override fun onChanged(t: String?) {

            }
        })
    }

    private fun init() {
        listData.add("AIDL")
        listData.add("Socket")
        listData.add("Handler")
        listData.add("Thread")
        listData.add("OkHttp")
        listData.add("Retrofit")
        listData.add("代理")
        listData.add("事件分发")

        adapter = ListAdapter(listData)
        adapter!!.setOnClickListener(object : ListAdapter.OnCallBack {
            @RequiresApi(Build.VERSION_CODES.N)
            override fun onClick(name: String) {
                switch(name)
            }
        })
        list
        list.layoutManager = LinearLayoutManager(this)
        list.adapter = adapter
    }

    private fun switch(name: String) {
        when (name) {
            "AIDL" ->{
                aidl()
            }
            "Socket" ->{
                socket()
            }
            "Handler" ->{
                handler()
            }
            "Thread" ->{
                thread()
            }
            "OkHttp" ->{
                okhttp()
            }
            "Retrofit" ->{
                retrofit()
            }
            "代理" ->{
                proxy()
            }
            "代理" ->{
                proxy()
            }
        }
    }

    private fun aidl(){
        startActivity(Intent(this, BookManagerActivity::class.java))
    }

    private fun socket(){
        startActivity(Intent(this, SocketActivity::class.java))
    }

    private fun handler(){
        startActivity(Intent(this, HandlerActivity::class.java))
    }

    private fun thread(){
        startActivity(Intent(this, ThreadActivity::class.java))
    }

    private fun okhttp(){
        startActivity(Intent(this, OKHttpActivity::class.java))
    }

    private fun retrofit(){
        startActivity(Intent(this, RetrofitActivity::class.java))
    }

    private fun proxy() {
        startActivity(Intent(this, ProxyActivity::class.java))
    }

    private fun eventDispatch() {
        startActivity(Intent(this, EventDispatchActivity::class.java))
    }
}
