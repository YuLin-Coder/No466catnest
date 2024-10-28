package com.stray.cat.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.stray.cat.dao.PostMapper;
import com.stray.cat.dto.CatConst;
import com.stray.cat.pojo.Enclosure;
import com.stray.cat.pojo.Post;
import com.stray.cat.pojo.Sponsor;
import com.stray.cat.pojo.User;
import com.stray.cat.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
@Transactional(rollbackFor = RuntimeException.class)
public class PostServiceImpl implements PostService {
    private static final String POST_CACHE_NAME = "POST";
    private static final String POST_CACHE_NAME_SEARCH = "POST_SEARCH";
    @Autowired
    HttpSession session;
    @Autowired
    PostMapper postMapper;

    @Override
    public List<Post> queryPostByPhone(String postPhone) {
        return postMapper.queryPostByPhone(postPhone);
    }

    @Override
    public List<Post> queryPostAll() {
        return postMapper.queryPostAll();
    }

    @Override
    public Post queryPostById(int postId) {
        return postMapper.queryPostById(postId);
    }

    @Override
    public int addPost(Post post) {
        return postMapper.addPost(post);
    }

    @Override
    public int updatePostCount(int postCount, int postId) {
        return postMapper.updatePostCount(postCount,postId);
    }

    @Override
    public int updatePost(Post post) {
        return postMapper.updatePost(post);
    }

    @Override
    public int deletePost(int postId) {
        return postMapper.deletePost(postId);
    }

    @Override
    @Cacheable(value = POST_CACHE_NAME, key = "'findPagePost'+#page+#limit")
    public PageInfo<Post> findPagePost(int page, int limit) {
        User user= (User) session.getAttribute(CatConst.USER_SESSION_KEY);
        PageHelper.startPage(page,limit);
        return new PageInfo<>(postMapper.queryPostByPhone(user.getUserPhone()));
    }

    @Override
    public PageInfo<Post> findPageAllPost(int page, int limit) {
        PageHelper.startPage(page,limit);
        return new PageInfo<>(postMapper.queryPostAll());
    }

    @Override
    @Cacheable(value = POST_CACHE_NAME_SEARCH, key = "'findPagePostSearch'+#page+#limit+#search")
    public PageInfo<Post> findPageSearchPost(int page, int limit, String search) {
        PageHelper.startPage(page,limit);
        String information1="%"+search+"%";
        List<Post> posts=postMapper.queryPostByTitle(information1);
        return new PageInfo<>(posts);
    }

    @Override
    public List<Post> findMaxPost(int limit) {
        return postMapper.queryPostMaxCount(limit);

    }
}
