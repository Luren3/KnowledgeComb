package com.magicfrost.processor;

import com.google.auto.service.AutoService;
import com.magicfrost.annotations.BindView;

import javax.annotation.processing.*;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.Elements;
import java.util.*;

/**
 * Copyright 杭州九爱科技有限公司. All rights reserved
 * <p>
 * Created by MagicFrost.
 */
@AutoService(Processor.class)
public class ButterknifeProcess extends AbstractProcessor {

    private Filer mFiler;
    private Messager mMessager;
    private Elements mElementUtils;

    private Map<String,String> moduleMap;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        mFiler = processingEnv.getFiler();
        mMessager = processingEnv.getMessager();
        mElementUtils = processingEnv.getElementUtils();
        moduleMap = new HashMap<>();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        if (!roundEnv.processingOver()) {
            //获取与annotation相匹配的TypeElement,即有注释声明的class
            Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(BindView.class);

            Map<String, List<VariableElement>> map = new HashMap<>();

            for (Element element:elements){
                VariableElement variableElement = (VariableElement) element;
                String activityName = getActivityName(variableElement);
                List<VariableElement> list = map.get(activityName);
                if (list == null) {
                    list = new ArrayList<>();
                    map.put(activityName,list);
                }
                list.add(variableElement);
            }

            for (String activityName:map.keySet()){
                List<VariableElement> list = map.get(activityName);
                String packageName = getPackageName(list.get(0));
                String newActivityBinder = activityName+"_ViewBinder";

            }
        }
        return false;
    }

    private String getPackageName(VariableElement element){
        TypeElement typeElement = (TypeElement) element.getEnclosingElement();
        return mElementUtils.getPackageOf(typeElement).toString();
    }

    private String getActivityName(VariableElement element){
        TypeElement typeElement = (TypeElement) element.getEnclosingElement();
        return getPackageName(element)+"."+typeElement.getSimpleName().toString();
    }
}
