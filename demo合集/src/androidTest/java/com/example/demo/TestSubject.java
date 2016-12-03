package com.example.demo;

import android.test.InstrumentationTestCase;
import android.util.Log;

import com.example.demo.utils.SuperHashMap;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TestSubject extends InstrumentationTestCase {
    private static final String TAG = "test";
    SuperHashMap<String,String> superHashMap= new SuperHashMap<String,String>();
    public void testPublishSubject() {
        superHashMap.put("123","234");
        superHashMap.put("123","234");
        superHashMap.put("123","234");
        superHashMap.put("1234","2345");
        superHashMap.put("1234","2345");
        HashMap<String,List<String>> map = superHashMap.getMap();
        Set<Map.Entry<String, List<String>>> entrySet = map.entrySet();
        Iterator<Map.Entry<String, List<String>>> iterator = entrySet.iterator();
        while (iterator.hasNext()){
            String key = iterator.next().getKey();
            for (String s:iterator.next().getValue()) {
                Log.d(TAG, "put: "+key+s);
            }
        }
    }
}