package com.stray.cat.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.stray.cat.dao.NoticeMapper;
import com.stray.cat.pojo.Notice;
import com.stray.cat.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = RuntimeException.class)
public class NoticeServiceImpl implements NoticeService {
    private static final String NOTICE_CACHE_NAME = "NOTICE";
    @Autowired
    NoticeMapper noticeMapper;

    @Override
    public List<Notice> queryNotice() {
        return noticeMapper.queryNotice();
    }

    @Override
    public int deleteNotice(int noticeId) {
        return noticeMapper.deleteNotice(noticeId);
    }

    @Override
    public int addNotice(Notice notice) {
        return noticeMapper.addNotice(notice);
    }

    @Override
    public int updateNotice(Notice notice) {
        return noticeMapper.updateNotice(notice);
    }

    @Override
    @Cacheable(value = NOTICE_CACHE_NAME, key = "'findPageNotice'+#page+#limit")
    public PageInfo<Notice> findPageNotice(int page, int limit) {
        PageHelper.startPage(page,limit);
        return new PageInfo<Notice>(noticeMapper.queryNotice1());
    }
}
