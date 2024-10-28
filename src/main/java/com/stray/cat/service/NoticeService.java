package com.stray.cat.service;

import com.github.pagehelper.PageInfo;
import com.stray.cat.pojo.Enclosure;
import com.stray.cat.pojo.Notice;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface NoticeService {

    List<Notice> queryNotice();

    int deleteNotice(int noticeId);

    int addNotice(Notice notice);

    int updateNotice(Notice notice);


    PageInfo<Notice> findPageNotice(int page, int limit);
}
