package com.magicfrost.knowledgecomb.ipc;

import android.app.Service;
import android.content.Intent;
import android.os.*;
import android.util.Log;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by MagicFrost.
 */
public class AIDLServices extends Service {

    private CopyOnWriteArrayList<Book> list = new CopyOnWriteArrayList<>();

    private RemoteCallbackList<INewBookAddListener> listeners = new RemoteCallbackList<>();


    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    private Binder mBinder = new IBookManager.Stub() {

        @Override
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            return super.onTransact(code, data, reply, flags);
        }

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public List<Book> getBookList() throws RemoteException {
            SystemClock.sleep(15000);
            Log.e("service","list="+list);
            return list;
        }

        @Override
        public void addBook(Book book) throws RemoteException {
            list.add(book);
            Log.e("service","book="+book);

            //beginBroadcast,finishBroadcast必须配套使用
            int N = listeners.beginBroadcast();
            for (int i = 0;i < N;i ++){
                listeners.getBroadcastItem(i).onNewBookAdd(book);
            }
            listeners.finishBroadcast();
        }

        @Override
        public void registerListener(INewBookAddListener listener) throws RemoteException {
            listeners.register(listener);
        }

        @Override
        public void unregisterListener(INewBookAddListener listener) throws RemoteException {
            listeners.unregister(listener);
        }
    };
}
