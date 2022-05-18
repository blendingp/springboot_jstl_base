package com.example.board.model;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

public class Post {
    private Long id;

    @NotEmpty(message = "제목을 입력하여 주세요.")
    private String title;

    @NotEmpty(message = "내용을 입력하여 주세요.")
    private String content;

    private Date createdAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

}
