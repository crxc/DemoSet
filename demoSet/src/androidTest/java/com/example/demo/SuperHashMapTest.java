package com.example.demo;

import android.util.Log;

import com.example.demo.utils.SuperHashMap;

import org.junit.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by crxc on 2016/11/30.
 */
public class SuperHashMapTest {
    private static final String TAG = "SuperHashMapTest";
    SuperHashMap<String,String> superHashMap= new SuperHashMap<String,String>();
    @Test
    public void put() throws Exception {
        superHashMap.put("123","234");
        superHashMap.put("123","234");
        superHashMap.put("123","234");
        superHashMap.put("1234","2345");
        superHashMap.put("1234","2345");
        HashMap<String,List<String>> map = superHashMap.getMap();
        Set<Map.Entry<String, List<String>>> entrySet = map.entrySet();
        Iterator<Map.Entry<String, List<String>>> iterator = entrySet.iterator();
        while (iterator.hasNext()){
            Map.Entry<String, List<String>> next = iterator.next();
            String key = next.getKey();
            for (String s:next.getValue()) {
                Log.d(TAG, "put: "+key+s);
            }
        }
    }
    int[] ints=new int[]{3,1,6,4,2,5};
    int count=0;
    int num=0;
    @Test
    public void getMap() throws Exception {

        for(int i=0;i<ints.length-1;i++){
            if(ints[i]<ints[i+1]){
                count++;
                compare(i,i+1);
            }
        }
        for(int i:ints){
            Log.e(TAG, "getMap: "+i );
        }
        Log.e(TAG, "getMap: "+"--------------");
        Log.e(TAG, "getMap: "+num );
        Log.e(TAG, "getMap: "+count);
    }

    private void compare(int i,int j) {
        if(i==0){
            insert(0,j);
            return;
        }
        if(ints[i-1]<ints[j]){
            count++;
            compare(i-1,j);
        }else {
            insert(i,j);
        }

    }

    private void insert(int k,int j) {
        num++;
        int temp=ints[j];
        for(;j>k;j--){
            ints[j]=ints[j-1];
        }
        ints[k]=temp;
    }

}