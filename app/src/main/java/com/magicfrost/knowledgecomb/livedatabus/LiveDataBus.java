package com.magicfrost.knowledgecomb.livedatabus;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by MagicFrost.
 */
public class LiveDataBus {
    //创建一个map来装载订阅者
    private Map<String, MutableLiveData<Object>> bus;

    private LiveDataBus(){
        bus = new HashMap<>();
    }

    private static class SingletonHolder{
        private static final LiveDataBus DEFAULT_BUS = new LiveDataBus();
    }

    public static LiveDataBus getInstance(){
        return SingletonHolder.DEFAULT_BUS;
    }

    //提供给订阅者的方法
    public synchronized <T> MutableLiveData<T> with(String key,Class<T> type){
        if (!bus.containsKey(key)){
            bus.put(key,new MutableLiveData<Object>());
        }

        return (MutableLiveData<T>) bus.get(key);
    }

    /**
     * 重写MutableLiveData 在observe方法中进行hook
     */
    public static class BusMutableLiveData<T> extends MutableLiveData<T>{

        @Override
        public void observe(@NonNull LifecycleOwner owner, @NonNull Observer<? super T> observer) {
            super.observe(owner, observer);
            try {
                hook((Observer<T>) observer);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /**
         * hook方法
         */
        private void hook(Observer<T> observer) throws Exception{
            //获取到LiveData类的class对象
            Class<LiveData> liveDataClass = LiveData.class;
            //通过反射获取LiveData里面的observe
            Field mObservers = liveDataClass.getDeclaredField("mObservers");
            //设置成员变量可以被访问
            mObservers.setAccessible(true);
            //获取这个成员变量的值 它的值是一个Map
            Object objectObserves = mObservers.get(this);
            //获取objectObserves的class对象
            Class<?> observeClass = objectObserves.getClass();
            //获取observeClass里面的get方法
            Method observerGet = observeClass.getDeclaredMethod("get",Object.class);
            //设置这个observerGet可以访问
            observerGet.setAccessible(true);
            //执行该方法 传一个方法执行在哪个对象中的这个对象 传入执行这个方法所需要的参数
            Object invokeEntry = observerGet.invoke(observeClass,observer);
            //定义一个空的对象
            Object objectWrapper = null;
            if (invokeEntry instanceof Map.Entry){
                objectWrapper = ((Map.Entry)invokeEntry).getValue();
            }
            if (objectWrapper == null){
                throw new NullPointerException("Wrapper can not be null!!");
            }
            Class<?> superclass = objectWrapper.getClass().getSuperclass();
            //通过superclass获取mLastVersion
            Field mLastVersion = superclass.getDeclaredField("mLastVersion");
            mLastVersion.setAccessible(true);
            Field mVersion = liveDataClass.getDeclaredField("mVersion");
            mVersion.setAccessible(true);
            Object object = mVersion.get(this);
            //mVersion的值赋值给mLastVersion成员变量
            mLastVersion.set(objectWrapper,object);
        }
    }
}
