package com.stray.cat.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.stray.cat.dao.PostMapper;
import com.stray.cat.dao.SponsorMapper;
import com.stray.cat.dao.UserMapper;
import com.stray.cat.dto.CatConst;
import com.stray.cat.enums.SponsorStatus;
import com.stray.cat.pojo.Post;
import com.stray.cat.pojo.Sponsor;
import com.stray.cat.pojo.User;
import com.stray.cat.service.SponsorService;
import com.stray.cat.vo.SponsorVo;
import org.apache.logging.log4j.message.ReusableMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
@Transactional(rollbackFor = RuntimeException.class)
public class SponsorServiceimpl implements SponsorService {
    private static final String POST_CACHE_NAME = "POST2";
    private static final String SPOSORVO_CACHE_NAME = "SPOSORVO";

    @Autowired
    HttpSession session;
    @Autowired
    SponsorMapper sponsorMapper;
    @Autowired
    UserMapper userMapper;


    @Override
    @Cacheable(value = POST_CACHE_NAME, key = "'findPageSponsor'+#page+#limit")
    public PageInfo<Sponsor> findPageSponsorTowrite(int page, int limit) {
        User user= (User) session.getAttribute(CatConst.USER_SESSION_KEY);
        PageHelper.startPage(page,limit);
        return new PageInfo<Sponsor>(sponsorMapper.querySponsorByPhone(user.getUserPhone()));
    }

    @Override
    public int addSponsor(Sponsor sponsor) {
        return sponsorMapper.addSponsor(sponsor);
    }

    @Override
    public void deletesponsor(int sponsorId) {
        sponsorMapper.deleteSponsor(sponsorId);
    }

    @Override
    public Sponsor querySponsorById(int sponsorId) {
        return  sponsorMapper.querySponsorById(sponsorId);
    }

    @Override
    public int updateSponsor(Sponsor sponsor) {
        return sponsorMapper.updateSponsor(sponsor);
    }

    @Override
    public int updateSponsorStatus(int sponsorStatus, int sponsorId) {
        return sponsorMapper.updateSponsorStatus(sponsorStatus,sponsorId);
    }

    @Override
    @Cacheable(value = POST_CACHE_NAME, key = "'findPageSponsorByState'+#page+#limit")
    public PageInfo<Sponsor> findPageSponsorByState(int page, int limit,int status) {
        PageHelper.startPage(page,limit);
        return new PageInfo<Sponsor>(sponsorMapper.querySponsorBystatu(status));
    }

    @Override
    @Cacheable(value = POST_CACHE_NAME, key = "'findPageALLSponsorse'+#page+#limit")
    public PageInfo<Sponsor> findPagePostSponsorse(int page, int limit) {
        PageHelper.startPage(page,limit);
        return new PageInfo<Sponsor>(sponsorMapper.queryPostSponsors());
    }

    @Override
    public List<Sponsor> findindexMaxSponsorse(int limit) {

        return sponsorMapper.querySponsorsOrderByCount(limit);
    }

    @Override
    @Cacheable(value = POST_CACHE_NAME, key = "'findPageSponsorseByname'+#page+#limit")
    public PageInfo<Sponsor> findPagePostSponsorseByname(int page, int limit, String information) {
        PageHelper.startPage(page,limit);
        return new PageInfo<Sponsor>(sponsorMapper.querySponsorByTitle(1,information));

    }

    @Override
    @Cacheable(value = SPOSORVO_CACHE_NAME, key = "'findPageSponsorVo'+#page+#limit")
    public PageInfo<SponsorVo> findPagePostSponsorVo(int page, int limit) {
        PageHelper.startPage(page,limit);
        List<SponsorVo> sponsorVos=sponsorMapper.querySponsorVoByStatus1(SponsorStatus.Examine.getStatus());
        for(int i=0;i<sponsorVos.size();i++){
            User user=userMapper.queryByPhone(sponsorVos.get(i).getSponsorPhone());
            sponsorVos.get(i).setQq(user.getUserQq());
            sponsorVos.get(i).setSponsorUserName(user.getUserNickname());
        }
        return new PageInfo<SponsorVo>(sponsorVos);
    }
}
