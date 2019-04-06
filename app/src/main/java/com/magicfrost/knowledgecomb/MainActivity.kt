package com.magicfrost.knowledgecomb

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.magicfrost.knowledgecomb.handle.HandlerActivity
import com.magicfrost.knowledgecomb.ipc.SocketActivity
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by MagicFrost
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        aidl.setOnClickListener {
            startActivity(Intent(this,BookManagerActivity::class.java))
        }

        socket.setOnClickListener {
            startActivity(Intent(this, SocketActivity::class.java))
        }

        handler.setOnClickListener {
            startActivity(Intent(this, HandlerActivity::class.java))
        }
    }
}
