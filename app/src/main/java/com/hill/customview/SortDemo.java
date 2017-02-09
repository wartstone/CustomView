package com.hill.customview;

import android.util.Log;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by hill on 16/11/22.
 */

public class SortDemo {
    private String TAG = "Hill/SortDemo";

    HashMap<String, Integer> map = new HashMap<>();

    public SortDemo() {
        setup();
        sort();
    }

    public void setup() {
        map.put("5", 8);
        map.put("8", 1);
        map.put("9", 2);
        map.put("3", 10);
        map.put("2", 9);
    }

    public void sort() {
        Map<String, Integer> sortedmap = new TreeMap<String, Integer>(map);
        Iterator iterator = sortedmap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            Log.e(TAG, "key = " + entry.getKey() + ", value = " + entry.getValue());
        }
    }
}
