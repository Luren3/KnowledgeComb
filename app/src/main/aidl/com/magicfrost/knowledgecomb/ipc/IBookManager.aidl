// IBookManager.aidl
package com.magicfrost.knowledgecomb.ipc;
import com.magicfrost.knowledgecomb.ipc.Book;
import com.magicfrost.knowledgecomb.ipc.INewBookAddListener;

// Declare any non-default types here with import statements

interface IBookManager {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);

    List<Book> getBookList();
    void addBook(in Book book);

    void registerListener(INewBookAddListener listener);
    void unregisterListener(INewBookAddListener listener);
}
