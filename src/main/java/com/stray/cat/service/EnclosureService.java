package com.stray.cat.service;

import com.github.pagehelper.PageInfo;
import com.stray.cat.pojo.Enclosure;

public interface EnclosureService {

    PageInfo<Enclosure> findPageEnclosure(int page, int limit);

    int addEnclosure(Enclosure enclosure);
}
