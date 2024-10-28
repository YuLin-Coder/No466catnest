package com.stray.cat.service.additional.Impl;

import com.stray.cat.dao.*;
import com.stray.cat.service.additional.AdminIndexService;
import com.stray.cat.vo.AdminIndexVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(rollbackFor = RuntimeException.class)
public class AdminIndexServiceImpl implements AdminIndexService {

    @Autowired
    CommentMapper commentMapper;//评论
    @Autowired
    UserMapper userMapper;//用户
    @Autowired
    PostMapper postMapper;//启示
    @Autowired
    ShareMapper shareMapper;//分享
    @Autowired
    EnclosureMapper enclosureMapper;//附件
    @Autowired
    SponsorMapper sponsorMapper;//赞助

    @Override
    public List<AdminIndexVo> queryAll() {
        List<AdminIndexVo> adminIndexVos=new ArrayList<>();
        adminIndexVos.add(new AdminIndexVo(commentMapper.queryCount(),"评论",commentMapper.queryNearDate(),"/images/title/title1.jpg"));
        adminIndexVos.add(new AdminIndexVo(userMapper.queryCount(),"用户",userMapper.queryNearDate(),"/images/title/title2.jpg"));
        adminIndexVos.add(new AdminIndexVo(postMapper.queryCount(),"启示",postMapper.queryNearDate(),"/images/title/title3.jpg"));
        adminIndexVos.add(new AdminIndexVo(shareMapper.queryCount(),"分享",shareMapper.queryNearDate(),"/images/title/title4.jpg"));
        adminIndexVos.add(new AdminIndexVo(enclosureMapper.queryCount(),"附件",enclosureMapper.queryNearDate(),"/images/title/title6.jpg"));
        adminIndexVos.add(new AdminIndexVo(sponsorMapper.queryCount(),"赞助",sponsorMapper.queryNearDate(),"/images/title/title7.jpg"));
        return adminIndexVos;
    }
}
