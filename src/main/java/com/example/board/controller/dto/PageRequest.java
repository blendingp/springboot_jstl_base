package com.example.board.controller.dto;

public class PageRequest {
    public static final int PAGE_SIZE = 10;
    private int pageNo;

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getOffset () {
        return (Math.max(pageNo, 1) - 1) * PAGE_SIZE;
    }
    public int getPageNo() {
        return pageNo;
    }
    public int getPageSize() {
        return PAGE_SIZE;
    }
}
