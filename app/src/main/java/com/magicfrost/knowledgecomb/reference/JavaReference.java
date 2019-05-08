package com.magicfrost.knowledgecomb.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * Created by MagicFrost.
 */
public class JavaReference {


    /**
     * 软引用
     */
    public void softReference(){
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        Object object = new Object();
        SoftReference<Object> softReference = new SoftReference<>(object,referenceQueue);

        System.out.println("object:"+object);
        System.out.println("softReference:"+softReference.get());

        object = null;
        System.gc();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("referenceQueue:"+referenceQueue.poll());
    }

    /**
     * 弱引用
     */
    public void weakReference(){
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        Object object = new Object();
        WeakReference<Object> weakReference = new WeakReference<>(object,referenceQueue);

        System.out.println("object:"+object);
        System.out.println("weakReference:"+weakReference.get());

        object = null;
        System.gc();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("referenceQueue:"+referenceQueue.poll());
    }

    /**
     * 虚引用
     */
    public void phantomReference(){
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        Object object = new Object();
        PhantomReference<Object> phantomReference = new PhantomReference<>(object,referenceQueue);

        object = null;
        System.out.println("object:"+object);
        System.out.println("phantomReference:"+phantomReference.get());

        System.gc();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("referenceQueue:"+referenceQueue.poll());

    }
}
