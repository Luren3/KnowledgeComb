package com.magicfrost.knowledgecomb.http

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.magicfrost.knowledgecomb.R
import com.magicfrost.knowledgecomb.http.api.Api
import kotlinx.android.synthetic.main.activity_retrofit.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 *
 * Created by MagicFrost.
 *
 */
class RetrofitActivity:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit)

        button.setOnClickListener {
            requestToNet()
        }
    }

    private fun requestToNet(){
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .client(OkHttpClient.Builder()
                .addNetworkInterceptor(HttpLoggingInterceptor().apply {
                    this.level = HttpLoggingInterceptor.Level.BODY
                })
                .build())
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .build()

        val api = retrofit.create(Api::class.java)

        api.test().enqueue(object :Callback<List<Map<String,Any>>>{
            override fun onFailure(call: Call<List<Map<String, Any>>>, t: Throwable) {
                Log.e("onFailure","${t.message}")
            }

            override fun onResponse(call: Call<List<Map<String, Any>>>, response: Response<List<Map<String, Any>>>) {
                Log.e("onResponse","${response.body()}")
            }
        })

    }
}