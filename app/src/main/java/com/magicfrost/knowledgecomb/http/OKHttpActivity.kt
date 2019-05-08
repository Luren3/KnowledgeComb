package com.magicfrost.knowledgecomb.http

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.magicfrost.knowledgecomb.R
import kotlinx.android.synthetic.main.activity_okhttp.*
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import java.io.IOException

/**
 *
 * Created by MagicFrost.
 *
 */
class OKHttpActivity:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_okhttp)

        button.setOnClickListener {
            requestToNet()
        }
    }

    private fun requestToNet(){
        val client = OkHttpClient.Builder()
            .addNetworkInterceptor(HttpLoggingInterceptor().apply {
                this.level = HttpLoggingInterceptor.Level.BODY
            })
            .build()

        val formBody = FormBody.Builder().build()

        val request = Request.Builder().url("https://magicfrost.cn/").post(formBody).build()

        val call = client.newCall(request)


        call.enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
//                    Toast.makeText(this@MainActivity,"Success",Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call, e: IOException) {
//                    Toast.makeText(this@MainActivity,"onFailure",Toast.LENGTH_SHORT).show()
            }
        })
    }
}