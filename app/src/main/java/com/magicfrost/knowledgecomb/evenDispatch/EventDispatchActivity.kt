package com.magicfrost.knowledgecomb.evenDispatch

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.magicfrost.knowledgecomb.R
import kotlinx.android.synthetic.main.activity_event_dispatch.*

/**
 *
 * Created by MagicFrost.
 *
 */
class EventDispatchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_dispatch)

        button.setOnClickListener {
            Log.e("EventDispatchActivity","onClick")
        }
    }
}