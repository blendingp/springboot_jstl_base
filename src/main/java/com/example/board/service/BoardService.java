package com.example.board.service;

import com.example.board.controller.dto.PageRequest;
import com.example.board.controller.dto.PostPage;
import com.example.board.model.Post;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BoardService {
    @Autowired
    SqlSession sqlSession;

    public void save(Post post) {
        if (post.getId() == null) {
            sqlSession.insert("board.insert", post);
        } else {
            sqlSession.update("board.update", post);
        }
    }

    /*
        bean 으로 파라미터 전달하는 샘플
     */
//    @Transactional(readOnly = true) // 트랜젝션을 읽기 전용 모드로 설정
//    public PostPage getPage(PageRequest pageRequest) {
//        int totalCount = sqlSession.selectOne("board.totalCount");
//        List<Post> posts = sqlSession.selectList("board.posts", pageRequest);
//
//        PostPage page = new PostPage();
//        page.setPageSize(pageRequest.getPageSize());
//        page.setPageNo(pageRequest.getPageNo());
//        page.setTotalCount(totalCount);
//        page.setPosts(posts);
//
//        return page;
//    }

    /*
        bean 대신에 Map 으로 파라미터 전달하는 샘플
     */
    @Transactional(readOnly = true) // 트랜젝션을 읽기 전용 모드로 설정
    public PostPage getPage(PageRequest pageRequest) {
        int totalCount = sqlSession.selectOne("board.totalCount");

        Map<String, Object> params = new HashMap<>();
        params.put("offset", pageRequest.getOffset());
        params.put("pageSize", pageRequest.getPageSize());

        List<Post> posts = sqlSession.selectList("board.posts", params);

        PostPage page = new PostPage();
        page.setPageSize(pageRequest.getPageSize());
        page.setPageNo(pageRequest.getPageNo());
        page.setTotalCount(totalCount);
        page.setPosts(posts);

        return page;
    }

    public Post findById(long id) {
        return sqlSession.selectOne("board.post", id);
    }

    public void deleteById(long id) {
        sqlSession.delete("board.delete", id);
    }
}
