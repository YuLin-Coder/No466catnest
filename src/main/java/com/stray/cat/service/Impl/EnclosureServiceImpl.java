package com.stray.cat.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.stray.cat.dao.EnclosureMapper;
import com.stray.cat.dto.CatConst;
import com.stray.cat.pojo.Enclosure;
import com.stray.cat.pojo.User;
import com.stray.cat.service.EnclosureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;

@Service
@Transactional(rollbackFor = RuntimeException.class)
public class EnclosureServiceImpl implements EnclosureService {
    private static final String ENCLOSURE_CACHE_NAME = "enclosure";

    @Autowired
    EnclosureMapper enclosureMapper;
    @Autowired
    HttpSession session;

    @Override
    @Cacheable(value = ENCLOSURE_CACHE_NAME, key = "'findPageEnclosure'+#page+#limit")
    public PageInfo<Enclosure> findPageEnclosure(int page, int limit) {
        User user= (User) session.getAttribute(CatConst.USER_SESSION_KEY);
        PageHelper.startPage(page,limit);
        return new PageInfo<Enclosure>(enclosureMapper.queryByNumber(user.getUserPhone()));
    }

    @Override
    public int addEnclosure(Enclosure enclosure) {
        return enclosureMapper.addEnclosure(enclosure);
    }

}
