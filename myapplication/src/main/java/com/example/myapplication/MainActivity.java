package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getFragment("主界面");
    }

    public void click(View view) {
        switch (view.getId()){
            case R.id.btn1:
                getFragment("主界面");
                break;
            case R.id.btn2:
                getFragment("朋友");
                break;
            case R.id.btn3:
                getFragment("通讯录");
                break;
            case R.id.btn4:
                getFragment("朋友圈");
                break;
        }
    }

    private void getFragment(String s) {
        BaseFragment baseFragment = new BaseFragment();
        Bundle bundle=new Bundle();
        bundle.putString("title",s);
        baseFragment.setArguments(bundle);
        getFragmentManager().beginTransaction()
                .replace(R.id.activity_main, baseFragment)
                .commit();
    }
}
