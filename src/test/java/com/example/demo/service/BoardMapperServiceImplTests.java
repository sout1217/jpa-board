package com.example.demo.service;

import com.example.demo.entity.Board;
import com.example.demo.mapper.BoardMapper;
import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BoardServiceTests {

    /**
     * 참고 Url https://velog.io/@hanblueblue/%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B82-myBatis-%EC%84%A4%EC%A0%95
     */

    @Autowired
    private BoardService boardService;

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
        Board abc = Board.builder().title("abc").build();
        boardMapper.save(abc);
        Board board = boardService.getBoard(1);
        System.err.println(board);
    }

    @Test
    @DisplayName("리스트 조회")
    void test3() {
        List<Board> boards = boardService.getBoards();
        assertThat(boards).hasSize(10);
    }

    @Test
    @DisplayName("페이징 조회")
    void test4() {
        Integer boardId = 1;
        PageInfo<Board> boardsWithPage = boardService.getBoardsWithPage(boardId, PageRequest.of(1, 10));


        System.out.println(boardsWithPage);
    }
}