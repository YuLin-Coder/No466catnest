package com.stray.cat.util;

import com.stray.cat.vo.IndexVo;

import java.util.Comparator;

public class sortClass implements Comparator {
    public int compare(Object arg0,Object arg1){  
        IndexVo user0 = (IndexVo)arg0;
        IndexVo user1 = (IndexVo)arg1;
        int flag = user0.getIndexCreateTime().compareTo(user1.getIndexCreateTime());
        return flag;  
    }
}  