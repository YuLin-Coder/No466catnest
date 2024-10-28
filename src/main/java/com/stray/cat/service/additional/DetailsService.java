package com.stray.cat.service.additional;

import com.stray.cat.pojo.Comment;
import com.stray.cat.vo.CommentVo;
import com.stray.cat.vo.IndexVo;

import java.util.List;

public interface DetailsService {

    IndexVo findDetails(int id,int status);

    int addCount(int id,int status);

    List<IndexVo> findRecommends(int status);

    List<CommentVo> findComment(int status, int id);
}
