package com.stray.cat.service.additional.Impl;

import com.stray.cat.dao.CommentMapper;
import com.stray.cat.dao.UserMapper;
import com.stray.cat.enums.IndexFrom;
import com.stray.cat.pojo.*;
import com.stray.cat.service.PostService;
import com.stray.cat.service.ShareService;
import com.stray.cat.service.SponsorService;
import com.stray.cat.service.additional.DetailsService;
import com.stray.cat.vo.CommentVo;
import com.stray.cat.vo.IndexVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(rollbackFor = RuntimeException.class)
public class DetailsServiceImpl implements DetailsService {
    @Autowired
    PostService postService;
    @Autowired
    SponsorService sponsorService;
    @Autowired
    ShareService shareService;
    @Autowired
    CommentMapper commentMapper;
    @Autowired
    UserMapper userMapper;

    @Override
    public IndexVo findDetails(int id, int status) {
        //status---0 post,1 share,2 sponsor
        IndexVo info1 =new IndexVo();
        if(status==0){
            Post info=postService.queryPostById(id);
            info1.setIndexUrl(info.getPostUrl());
            info1.setIndexFrom(IndexFrom.Post.getDesc());
            info1.setIndexTitle(info.getPostTitle());
            info1.setIndexCreateTime(info.getPostCreatetime());
            info1.setIndexCount(info.getPostCount());
            info1.setIndexContent(info.getPostContent());
            info1.setIndexId(info.getPostId());
        }else if(status==2){
            Sponsor info=sponsorService.querySponsorById(id);
            info1.setIndexUrl(info.getSponsorPicture());
            info1.setIndexFrom(IndexFrom.Sponsor.getDesc());
            info1.setIndexTitle(info.getSponsorTitle());
            info1.setIndexCreateTime(info.getSponsorCreatetime());
            info1.setIndexCount(info.getSponsorCount());
            info1.setIndexContent(info.getSponsorContent());
            info1.setIndexId(info.getSponsorId());
        }else if(status==1) {
            Share info = shareService.queryShareById(id);
            info1.setIndexUrl(info.getShareUrl());
            info1.setIndexFrom(IndexFrom.Share.getDesc());
            info1.setIndexTitle(info.getShareTitle());
            info1.setIndexCreateTime(info.getShareCreatetime());
            info1.setIndexCount(info.getShareCount());
            info1.setIndexContent(info.getShareContent());
            info1.setIndexId(info.getShareId());
        }
        return info1;
    }

    @Override
    public int addCount(int id, int status) {
        if(status==0){
            Post post=postService.queryPostById(id);
            int i=postService.updatePostCount(post.getPostCount()+1,id);
            return i;
        }else if(status==1){
            Share share=shareService.queryShareById(id);
            int i=shareService.updateShareCount(share.getShareCount()+1,id);
            return i;
        }else if(status==2){
            Sponsor sponsor=sponsorService.querySponsorById(id);
            int i=sponsorService.updateSponsorStatus(sponsor.getSponsorCount()+1,id);
            return i;
        }else {
            return 0;
        }
    }

    @Override
    public List<IndexVo> findRecommends(int status) {
        List<IndexVo> indexVos=new ArrayList<>();
        if(status==0){
            List<Post> list=postService.findMaxPost(5);
            for(Post post:list){
                IndexVo indexVo=new IndexVo();
                indexVo.setIndexId(post.getPostId());
                indexVo.setIndexTitle(post.getPostTitle());
                indexVo.setIndexFrom(IndexFrom.Post.getDesc());
                indexVos.add(indexVo);
            }
        }if(status==1){
            List<Share> list=shareService.findindexMaxShare(5);
            for(Share share:list){
                IndexVo indexVo=new IndexVo();
                indexVo.setIndexId(share.getShareId());
                indexVo.setIndexTitle(share.getShareTitle());
                indexVo.setIndexFrom(IndexFrom.Share.getDesc());
                indexVos.add(indexVo);
            }
        }if(status==2){
            List<Sponsor> list=sponsorService.findindexMaxSponsorse(5);
            for(Sponsor sponsor:list){
                IndexVo indexVo=new IndexVo();
                indexVo.setIndexId(sponsor.getSponsorId());
                indexVo.setIndexTitle(sponsor.getSponsorTitle());
                indexVo.setIndexFrom(IndexFrom.Sponsor.getDesc());
                indexVos.add(indexVo);
            }
        }
        return indexVos;
    }

    @Override
    public List<CommentVo> findComment(int status, int id) {
        List<CommentVo> comments=commentMapper.queryComment(status,id);
        for(CommentVo commentVo:comments){
            User user=userMapper.queryByPhone(commentVo.getCommentPhone());
            commentVo.setUserName(user.getUserNickname());
            commentVo.setUserUrl(user.getUserUrl());
        }
        return comments;
    }


}
