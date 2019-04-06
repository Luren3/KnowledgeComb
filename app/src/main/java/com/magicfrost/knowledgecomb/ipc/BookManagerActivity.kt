package com.magicfrost.knowledgecomb

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.magicfrost.knowledgecomb.ipc.AIDLServices
import com.magicfrost.knowledgecomb.ipc.Book
import com.magicfrost.knowledgecomb.ipc.IBookManager
import com.magicfrost.knowledgecomb.ipc.INewBookAddListener
import kotlinx.android.synthetic.main.activity_ipc_book_manager.*

/**
 * Created by MagicFrost
 */
class BookManagerActivity : AppCompatActivity() {

    private var mBookManger: IBookManager? = null

    private var index = 1

    val serviceConnection = object :ServiceConnection{
        override fun onServiceDisconnected(name: ComponentName?) {

        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            mBookManger = IBookManager.Stub.asInterface(service)
            service?.linkToDeath(mDeathRecipient,0)
            mBookManger?.registerListener(mINewBookAddListener)
            mBookManger?.addBook(Book(index,"${index}"))

        }
    }

    val mDeathRecipient = object : IBinder.DeathRecipient{
        override fun binderDied() {
            if (mBookManger == null){
                return
            }
            mBookManger!!.asBinder().unlinkToDeath(this,0)
            mBookManger = null
            bindService()
        }
    }

    val mINewBookAddListener = object : INewBookAddListener.Stub(){
        override fun basicTypes(
            anInt: Int,
            aLong: Long,
            aBoolean: Boolean,
            aFloat: Float,
            aDouble: Double,
            aString: String?
        ) {

        }

        override fun onNewBookAdd(book: Book?) {
            Log.e("onNewBookAdd","----------")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ipc_book_manager)

        bindService()

        button.setOnClickListener {
            index++
            Thread(object :Runnable{
                override fun run() {
                    Log.e("client","list=${mBookManger?.bookList}")
                }

            }).start()
            mBookManger?.addBook(Book(index,"${index}"))
        }
    }

    fun bindService(){
        val intent = Intent(this, AIDLServices::class.java)
        bindService(intent,serviceConnection, Context.BIND_AUTO_CREATE)
    }

    override fun onDestroy() {
        mBookManger?.unregisterListener(mINewBookAddListener)
        unbindService(serviceConnection)
        super.onDestroy()
    }
}
