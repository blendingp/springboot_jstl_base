package com.example.board.controller.dto;

import com.example.board.model.Post;

import java.util.List;

public class PostPage {
    private int pageSize;
    private int pageNo;
    private int totalCount;
    private List<Post> posts;

    public int getTotalPage () {
        int totalPage = totalCount / pageSize;
        if (totalCount % pageSize > 0) {
            return totalPage + 1;
        }
        return totalPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNo() {
        return pageNo;
    }
    public void setPageNo(int pageNo) {
        this.pageNo = Math.max(pageNo, 1);
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

}
