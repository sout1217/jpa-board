package com.example.demo.mapper;

import com.example.demo.entity.Board;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.util.List;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class BoardMapperTests {

    @Autowired
    private BoardMapper boardMapper;

    @Test
    @DisplayName("전체 조회 테스트")
    void test() {
        List<Board> all = boardMapper.findAll();
        all.forEach(System.err::println);
    }

    @Test
    @DisplayName("저장 테스트 2")
    void test2() {
        Board board = Board.builder()
                .title("게시글 1")
                .build();

        boardMapper.save(board);

        List<Board> all = boardMapper.findAll();
        all.forEach(System.err::println);
    }

    @Test
    @DisplayName("저장 테스트 3")
    void test3() {
        Board board = Board.builder()
                .title("게시글 이다")
                .build();

        boardMapper.save(board);
        boardMapper.save(board);
        boardMapper.save(board);
        boardMapper.save(board);

        List<Board> all = boardMapper.findAll();
        all.forEach(System.err::println);
    }

}