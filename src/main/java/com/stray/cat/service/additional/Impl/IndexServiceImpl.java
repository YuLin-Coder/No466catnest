package com.stray.cat.service.additional.Impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.stray.cat.dao.PostMapper;
import com.stray.cat.dao.ShareMapper;
import com.stray.cat.dao.SponsorMapper;
import com.stray.cat.enums.IndexFrom;
import com.stray.cat.pojo.Enclosure;
import com.stray.cat.pojo.Post;
import com.stray.cat.pojo.Share;
import com.stray.cat.pojo.Sponsor;
import com.stray.cat.service.additional.IndexService;
import com.stray.cat.util.ListUtil;
import com.stray.cat.util.sortClass;
import com.stray.cat.util.sortClassCount;
import com.stray.cat.vo.IndexVo;
import org.jboss.jandex.Index;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Transactional(rollbackFor = RuntimeException.class)
public class IndexServiceImpl implements IndexService {
    @Autowired
    PostMapper postMapper;
    @Autowired
    ShareMapper shareMapper;
    @Autowired
    SponsorMapper sponsorMapper;


    @Override
    public PageInfo<IndexVo> findPageIndex() {
        List<IndexVo> indexVos=getInformation();
        return new PageInfo<>(indexVos);
    }

    @Override
    public List<IndexVo> findMaxCount() {
        List<IndexVo> indexVos=getInformation();
        sortClassCount sort=new sortClassCount();
        Collections.sort(indexVos,sort);

        List<IndexVo> indexVos1=new ArrayList<>();
        if(indexVos.size()>=4){
            for(int i=indexVos.size();i>indexVos.size()-4;i--){
                indexVos1.add(indexVos.get(i-1));
            }
        }else {
            for(int i=indexVos.size();i>0;i--){
                indexVos1.add(indexVos.get(i-1));
            }
        }
        return indexVos1;
    }

    @Override
    public PageInfo<IndexVo> findPageIndexSearch(String search) {
        List<IndexVo> indexVos=getInformation1(search);
        return new PageInfo<>(indexVos);
    }

    public List<IndexVo> getInformation() {
        List<Post> posts=postMapper.queryPostAll();
        List<Share> shares=shareMapper.queryShareAll();
        List<Sponsor> sponsors=sponsorMapper.querySponsorByStatus(1);
        List<IndexVo> indexVos=new ArrayList<>();
        for(Post post:posts){
            IndexVo indexVo=new IndexVo();
            indexVo.setIndexContent(post.getPostContent());
            indexVo.setIndexCount(post.getPostCount());
            indexVo.setIndexId(post.getPostId());
            indexVo.setIndexCreateTime(post.getPostCreatetime());
            indexVo.setIndexTitle(post.getPostTitle());
            indexVo.setIndexFrom(IndexFrom.Post.getDesc());
            indexVo.setIndexUrl(post.getPostUrl());
            indexVos.add(indexVo);
        }
        for(Share share:shares){
            IndexVo indexVo=new IndexVo();
            indexVo.setIndexContent(share.getShareContent());
            indexVo.setIndexCount(share.getShareCount());
            indexVo.setIndexId(share.getShareId());
            indexVo.setIndexCreateTime(share.getShareCreatetime());
            indexVo.setIndexTitle(share.getShareTitle());
            indexVo.setIndexFrom(IndexFrom.Share.getDesc());
            indexVo.setIndexUrl(share.getShareUrl());
            indexVos.add(indexVo);
        }
        for(Sponsor sponsor:sponsors){
            IndexVo indexVo=new IndexVo();
            indexVo.setIndexContent(sponsor.getSponsorContent());
            indexVo.setIndexCount(sponsor.getSponsorCount());
            indexVo.setIndexId(sponsor.getSponsorId());
            indexVo.setIndexCreateTime(sponsor.getSponsorCreatetime());
            indexVo.setIndexTitle(sponsor.getSponsorTitle());
            indexVo.setIndexFrom(IndexFrom.Sponsor.getDesc());
            indexVo.setIndexUrl(sponsor.getSponsorPicture());
            indexVos.add(indexVo);
        }
        sortClass sort = new sortClass();
        Collections.sort(indexVos,sort);
        return ListUtil.getSort(indexVos);
    }


    public List<IndexVo> getInformation1(String search) {
        String information1="%"+search+"%";
        List<Post> posts=postMapper.queryPostByTitle(information1);
        List<Share> shares=shareMapper.queryShareByTitle(information1);
        List<Sponsor> sponsors=sponsorMapper.querySponsorByTitle(1,information1);
        List<IndexVo> indexVos=new ArrayList<>();
        for(Post post:posts){
            IndexVo indexVo=new IndexVo();
            indexVo.setIndexContent(post.getPostContent());
            indexVo.setIndexCount(post.getPostCount());
            indexVo.setIndexId(post.getPostId());
            indexVo.setIndexCreateTime(post.getPostCreatetime());
            indexVo.setIndexTitle(post.getPostTitle());
            indexVo.setIndexFrom(IndexFrom.Post.getDesc());
            indexVo.setIndexUrl(post.getPostUrl());
            indexVos.add(indexVo);
        }
        for(Share share:shares){
            IndexVo indexVo=new IndexVo();
            indexVo.setIndexContent(share.getShareContent());
            indexVo.setIndexCount(share.getShareCount());
            indexVo.setIndexId(share.getShareId());
            indexVo.setIndexCreateTime(share.getShareCreatetime());
            indexVo.setIndexTitle(share.getShareTitle());
            indexVo.setIndexFrom(IndexFrom.Share.getDesc());
            indexVo.setIndexUrl(share.getShareUrl());
            indexVos.add(indexVo);
        }
        for(Sponsor sponsor:sponsors){
            IndexVo indexVo=new IndexVo();
            indexVo.setIndexContent(sponsor.getSponsorContent());
            indexVo.setIndexCount(sponsor.getSponsorCount());
            indexVo.setIndexId(sponsor.getSponsorId());
            indexVo.setIndexCreateTime(sponsor.getSponsorCreatetime());
            indexVo.setIndexTitle(sponsor.getSponsorTitle());
            indexVo.setIndexFrom(IndexFrom.Sponsor.getDesc());
            indexVo.setIndexUrl(sponsor.getSponsorPicture());
            indexVos.add(indexVo);
        }
        sortClass sort = new sortClass();
        Collections.sort(indexVos,sort);
        return ListUtil.getSort(indexVos);
    }
}
