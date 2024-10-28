package com.stray.cat.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.stray.cat.dao.PostMapper;
import com.stray.cat.dao.ShareMapper;
import com.stray.cat.dto.CatConst;
import com.stray.cat.pojo.Post;
import com.stray.cat.pojo.Share;
import com.stray.cat.pojo.Sponsor;
import com.stray.cat.pojo.User;
import com.stray.cat.service.ShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
@Transactional(rollbackFor = RuntimeException.class)
public class ShareServiceImpl implements ShareService {

    private static final String POST_CACHE_NAME = "POST";
    private static final String POST_CACHE_NAME_SEARCH = "POST_SEARCH";
    @Autowired
    HttpSession session;
    @Autowired
    ShareMapper shareMapper;

    @Override
    public List<Share> queryShareByPhone(String sharePhone) {
        return shareMapper.queryShareByPhone(sharePhone);
    }

    @Override
    public List<Share> queryShareAll() {
        return shareMapper.queryShareAll();
    }

    @Override
    public List<Share> findindexMaxShare(int limit) {
        return  shareMapper.queryShareMaxCount(limit);
    }

    @Override
    public Share queryShareById(int shareId) {
        return shareMapper.queryShareById(shareId);
    }

    @Override
    public int addShare(Share share) {
        return shareMapper.addShare(share);
    }

    @Override
    public int updateShareCount(int shareCount, int shareId) {
        return shareMapper.updateShareCount(shareCount,shareId);
    }

    @Override
    public int updateShare(Share share) {
        return shareMapper.updateShare(share);
    }

    @Override
    public int deleteShare(int shareId) {
        return shareMapper.deleteShare(shareId);
    }

    @Override
    @Cacheable(value = POST_CACHE_NAME, key = "'findPageShare'+#page+#limit")
    public PageInfo<Share> findPageShare(int page, int limit) {
        User user= (User) session.getAttribute(CatConst.USER_SESSION_KEY);
        PageHelper.startPage(page,limit);
        return new PageInfo<>(shareMapper.queryShareByPhone(user.getUserPhone()));
    }

    @Override
    public PageInfo<Share> findPageAllShare(int page, int limit) {
        PageHelper.startPage(page,limit);
        return new PageInfo<>(shareMapper.queryShareAll());
    }

    @Override
    @Cacheable(value = POST_CACHE_NAME_SEARCH, key = "'findPageShareSearch'+#page+#limit+#search")
    public PageInfo<Share> findPageSearchShare(int page, int limit, String search) {
        PageHelper.startPage(page,limit);
        String information1="%"+search+"%";
        List<Share> shares=shareMapper.queryShareByTitle(information1);
        return new PageInfo<>(shares);
    }

    @Override
    public List<Share> findMaxShare(int limit) {
        return shareMapper.queryShareMaxCount(limit);

    }
}
