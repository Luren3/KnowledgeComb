package com.magicfrost.knowledgecomb.ipc;

import android.os.IInterface;
import android.os.RemoteException;

import java.util.List;

/**
 * Created by MagicFrost
 */
public interface IBookManager2 extends IInterface {

    static final String DESCRIPTOR = "com.magicfrost.knowledgecomb.ipc.IBookManager2";



    public List<Book> getBookList() throws RemoteException;

    public void addBook(Book book) throws RemoteException;

    static final int TRANSACTION_getBookList = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
    static final int TRANSACTION_addBook = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
}
