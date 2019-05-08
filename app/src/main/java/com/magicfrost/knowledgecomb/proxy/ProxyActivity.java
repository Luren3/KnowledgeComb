package com.magicfrost.knowledgecomb.proxy;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.magicfrost.knowledgecomb.R;

import java.lang.reflect.Proxy;

/**
 * Created by MagicFrost.
 */
public class ProxyActivity extends AppCompatActivity {

    private Subject subject;

    private Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proxy);

        Subject realSubject = new RealSubject();
        subject = (Subject) Proxy.newProxyInstance(realSubject.getClass().getClassLoader(),realSubject.getClass().getInterfaces(),new DynamicProxy(realSubject));

        Log.e("ProxyActivity",subject.getClass().getName());

        init();
    }

    private void init(){
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                proxy();
            }
        });
    }

    private void proxy(){
        subject.hello("xxx");
        subject.battle();
    }
}
