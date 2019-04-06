// INewBookAdd.aidl
package com.magicfrost.knowledgecomb.ipc;
import com.magicfrost.knowledgecomb.ipc.Book;

// Declare any non-default types here with import statements

interface INewBookAddListener {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);

    void onNewBookAdd(in Book book);
}
