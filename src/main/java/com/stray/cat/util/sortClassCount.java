package com.stray.cat.util;

import com.stray.cat.vo.IndexVo;

import java.util.Comparator;

public class sortClassCount implements Comparator {
    public int compare(Object arg0,Object arg1){
        IndexVo user0 = (IndexVo)arg0;
        IndexVo user1 = (IndexVo)arg1;
        boolean flag1 = user0.getIndexCount()>(user1.getIndexCount());
        int flag;
        if(flag1){
            flag=1;
        }else {
            flag=-1;
        }
        return flag;  
    }
}  