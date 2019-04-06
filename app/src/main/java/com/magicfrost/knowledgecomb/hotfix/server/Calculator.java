package com.magicfrost.knowledgecomb.hotfix.server;

import com.magicfrost.knowledgecomb.hotfix.Replace;

public class Calculator {

    @Replace(clazz = "com.sflin.androiddevartex.hotfix.Calculator",method = "calculator")
    public int calculator(){
        int i = 0;
        int j = 10;
        return j/i;
    }
}
