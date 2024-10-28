package com.stray.cat.service.additional;

import com.github.pagehelper.PageInfo;
import com.stray.cat.vo.IndexVo;

import java.util.List;

public interface IndexService {

    PageInfo<IndexVo> findPageIndex();

    List<IndexVo> findMaxCount();

    PageInfo<IndexVo> findPageIndexSearch(String information);
}
