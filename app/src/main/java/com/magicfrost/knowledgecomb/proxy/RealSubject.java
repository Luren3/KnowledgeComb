package com.magicfrost.knowledgecomb.proxy;

import android.util.Log;

/**
 * Created by MagicFrost.
 */
public class RealSubject implements Subject{

    @Override
    public void hello(String str) {
        Log.e("RealSubject","您好，"+str);
    }

    @Override
    public void battle() {
        Log.e("RealSubject","开始战斗");
    }
}
