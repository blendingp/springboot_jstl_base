package com.example.board.config;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 스케쥴링 설정
 */
@Component // 스프링 빈 선언
public class ScheduledTask {
    final static DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Autowired
    SqlSession sqlSession;

    // 해당 메서드가 끝나는 시간 기준, 1시간 후 실행
    @Scheduled(fixedDelay = 60 * 60 * 1000) // ms 단위
    public void reportTime () {
        String msg = "Now Time is " + LocalDateTime.now().format(DATE_FORMAT);
        sqlSession.insert("schedule.insert", msg);
    }
}
