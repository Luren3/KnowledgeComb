package com.magicfrost.knowledgecomb.proxy;

import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by MagicFrost.
 */
public class DynamicProxy implements InvocationHandler {

    //　这个就是我们要代理的真实对象
    private Object subject;

    public DynamicProxy(Object subject){
        this.subject = subject;
    }

    /**
     * @param proxy 指代我们所代理的那个真实对象
     * @param method 指代的是我们所要调用真实对象的某个方法的Method对象
     * @param args 指代的是调用真实对象某个方法时接受的参数
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        //在代理真实对象前我们可以添加一些自己的操作
        Log.e("DynamicProxy","before "+method.getName());

        //当代理对象调用真实对象的方法时，其会自动的跳转到代理对象关联的handler对象的invoke方法来进行调用

        method.invoke(subject,args);

        //在代理真实对象后我们也可以添加一些自己的操作
        Log.e("DynamicProxy","after "+method.getName());

        return proxy;
    }
}
