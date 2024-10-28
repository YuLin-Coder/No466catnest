package com.stray.cat.service;

import com.github.pagehelper.PageInfo;
import com.stray.cat.pojo.Enclosure;
import com.stray.cat.pojo.Post;
import com.stray.cat.pojo.Sponsor;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface PostService {

    List<Post> queryPostByPhone(String postPhone);

    List<Post> queryPostAll();

    Post queryPostById(int postId);

    int addPost(Post post);

    int updatePostCount(int postCount,int postId);

    int updatePost(Post post);

    int deletePost(int postId);

    PageInfo<Post> findPagePost(int page, int limit);

    PageInfo<Post> findPageAllPost(int page, int limit);

    PageInfo<Post> findPageSearchPost(int page, int limit,String search);

    List<Post> findMaxPost(int limit);
}
