package com.magicfrost.knowledgecomb.hotfix;

import android.content.Context;
import dalvik.system.DexFile;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Enumeration;

public class DxManager {

    private Context mContext;

    public DxManager(Context mContext){
        this.mContext = mContext;
    }

    public void loadDex(File dexFilePath){
        File optFile = new File(mContext.getCacheDir(),dexFilePath.getName());
        if (optFile.exists()){
            optFile.delete();
        }

        try {
            DexFile dexFile = DexFile.loadDex(dexFilePath.getAbsolutePath(),optFile.getAbsolutePath(),Context.MODE_PRIVATE);
            Enumeration<String> entry = dexFile.entries();
            while (entry.hasMoreElements()){
                String className = entry.nextElement();
                Class realClazz = dexFile.loadClass(className,mContext.getClassLoader());

            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void fix(Class realClazz){
        Method[] methods = realClazz.getDeclaredMethods();
        for (Method method:methods){
            Replace replace = method.getAnnotation(Replace.class);
            if (replace == null) continue;

            String wrongClazzName = replace.clazz();
            String wrongMethodName = replace.method();

            try {
                Class wrongClass = Class.forName(wrongClazzName);
                //拿到错误的Method对象
                Method wrongMethod = wrongClass.getMethod(wrongMethodName,method.getParameterTypes());

                replace(wrongMethod,method);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
    }

    private native void replace(Method wrongMethod, Method method);
}
