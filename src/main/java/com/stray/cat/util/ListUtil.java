package com.stray.cat.util;

import com.stray.cat.vo.IndexVo;


import java.util.ArrayList;
import java.util.List;

//数组逆排
public class ListUtil {

    public static List<IndexVo> getSort(List<IndexVo> indexVos) {
        List<IndexVo> indexVos1=new ArrayList<>();
        for(int i=indexVos.size();i>0;i--){
            indexVos1.add(indexVos.get(i-1));
        }
        return indexVos1;
    }

}
