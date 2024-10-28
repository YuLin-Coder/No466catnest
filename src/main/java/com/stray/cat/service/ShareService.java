package com.stray.cat.service;

import com.github.pagehelper.PageInfo;
import com.stray.cat.pojo.Post;
import com.stray.cat.pojo.Share;
import com.stray.cat.pojo.Sponsor;

import java.util.List;

public interface ShareService {

    List<Share> queryShareByPhone(String sharePhone);

    List<Share> queryShareAll();

    List<Share> findindexMaxShare(int limit);

    Share queryShareById(int shareId);

    int addShare(Share share);

    int updateShareCount(int shareCount,int shareId);

    int updateShare(Share share);

    int deleteShare(int shareId);

    PageInfo<Share> findPageShare(int page, int limit);

    PageInfo<Share> findPageAllShare(int page, int limit);

    PageInfo<Share> findPageSearchShare(int page, int limit,String search);

    List<Share> findMaxShare(int limit);
}
