package com.example.demo.utils;

import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by crxc on 2016/11/29.
 */

public class SuperHashMap<K,T>{
    private HashMap<K,List<T>> hashMap;
    private HashMap<K,T> ktHashMap;
    {
        hashMap=new HashMap<>();
        ktHashMap=new HashMap<>();
    }

    public boolean put(K k, T t){
        T put = ktHashMap.put(k,t);
        if(put==null){
            ArrayList<T> ts = new ArrayList<>();
            ts.add(t);
            hashMap.put(k,ts);
        }else {
            List<T> ts = hashMap.get(k);
            ts.add(t);
        }
        return true;
    }

    public HashMap<K,List<T>> getMap(){
        return hashMap;
    }


    public int size() {
        return hashMap.size();
    }
}
