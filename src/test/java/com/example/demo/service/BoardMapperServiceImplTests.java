package com.example.demo.service;

import com.example.demo.entity.Board;
import com.example.demo.mapper.BoardMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Sql("classpath:/test/sql/boardInit.sql") // MEMO: 2022/10/23 @Transaction 이 auto_increment 까지는 해주지 않아 별도로 추가 DDL 사용
@SpringBootTest
@Transactional
class BoardMapperServiceImplTests {

    /**
     * 참고 Url https://velog.io/@hanblueblue/%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B82-myBatis-%EC%84%A4%EC%A0%95
     */

    @Autowired
    @Qualifier("boardMapperServiceImpl")
    private BoardService boardMapperServiceImpl;

    @Autowired
    private BoardMapper boardMapper;

    @Test
    @DisplayName("저장")
    void test() {
        Board board;
        for (int i = 0; i < 20; i++) {
            board = Board.builder().title("게시글 " + i).build();
            boardMapper.save(board);
        }
    }

    @Test
    @DisplayName("단건 조회")
    void test2() {
        // board talbe get last index

        Board abc = Board.builder().title("abc").build();
        boardMapper.save(abc);

        boardMapper.findAll().forEach(board ->
                System.err.println("값: " + board)
        );

        Board board = boardMapperServiceImpl.getBoard(1);

    }

    @Test
    @DisplayName("리스트 조회")
    void test3() {
        Board board;
        for (int i = 0; i < 20; i++) {
            board = Board.builder().title("게시글 " + i).build();
            boardMapper.save(board);
        }

        List<Board> boards = boardMapperServiceImpl.getBoards();
        assertThat(boards).hasSize(20);
    }

    @Test
    @DisplayName("페이징 조회")
    void test4() {
        Integer boardId = 1;
        Page<Board> boardsWithPage = boardMapperServiceImpl.getBoardsWithPage(PageRequest.of(1, 10));
        System.out.println(boardsWithPage);
    }
}