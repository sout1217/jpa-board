package com.example.demo.service;

import com.example.demo.DemoApplication;
import com.example.demo.dto.BoardRequestDTO;
import com.example.demo.entity.Board;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

//@ActiveProfiles("test")
@Sql("classpath:/test/sql/boardInit.sql")
@Transactional
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = DemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
class BoardJpaServiceImplTests {

    @Autowired
    @Qualifier("boardJpaServiceImpl")
    private BoardService boardService;

    @PersistenceContext
    private EntityManager em;


    @BeforeEach
    void setUp() {
    }

    @Test
    void save() {

        String title = "제목입니다.";

        BoardRequestDTO dto = BoardRequestDTO.builder()
                .title(title)
                .build();

        Integer saveId = boardService.save(dto);

        assertThat(saveId).isEqualTo(1);

    }

    @Test
    void getBoards() {
        var dataBoards = List.of(
                BoardRequestDTO.builder().title("board1").build(),
                BoardRequestDTO.builder().title("board2").build(),
                BoardRequestDTO.builder().title("board3").build()
        );

        for (BoardRequestDTO dataBoard : dataBoards) {
            boardService.save(dataBoard);
        }
        em.clear();

        List<Board> boards = boardService.getBoards();

        assertThat(boards).hasSize(3);
    }


    @Test
    void getBoardsWithPage() {
        for (int i = 0; i < 20; i++) {
            boardService.save(BoardRequestDTO.builder().title("board " + i).build());
        }
        em.clear();

        Page<Board> boardsWithPage = boardService.getBoardsWithPage(PageRequest.of(0, 10));

        // paging check
        // MEMO: 2022/10/23 리팩토링 대상
        assertThat(boardsWithPage.getContent()).hasSize(10);
        assertThat(boardsWithPage.getTotalPages()).isEqualTo(2);
        assertThat(boardsWithPage.getNumber()).isEqualTo(0);
        assertThat(boardsWithPage.getNumberOfElements()).isEqualTo(10);
        assertThat(boardsWithPage.getSize()).isEqualTo(10);

    }

    @Test
    void getBoard() {
    }
}