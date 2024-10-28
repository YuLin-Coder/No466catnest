package com.stray.cat.service;

import com.github.pagehelper.PageInfo;
import com.stray.cat.pojo.Post;
import com.stray.cat.pojo.Sponsor;
import com.stray.cat.vo.SponsorVo;

import java.util.List;

/**
 * @description:
 * @author: Code-zyc
 * @date: Created in 2021/2/20 17:31
 * @version: v1.0
 * @modified By:
 */
public interface SponsorService {

    PageInfo<Sponsor> findPageSponsorTowrite(int page, int limit);

    int addSponsor(Sponsor sponsor);

    void deletesponsor(int sponsorId);

    Sponsor querySponsorById(int sponsorId);

    int updateSponsor(Sponsor sponsor);

    int updateSponsorStatus(int sponsorStatus,int sponsorId);

    PageInfo<Sponsor> findPageSponsorByState(int page, int limit,int status);

    PageInfo<Sponsor> findPagePostSponsorse(int page, int limit);

    List<Sponsor> findindexMaxSponsorse(int limit);

    PageInfo<Sponsor> findPagePostSponsorseByname(int page, int limit, String information);

    PageInfo<SponsorVo> findPagePostSponsorVo(int page, int limit);
}
