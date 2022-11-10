package com.example.demo.mapper;

import com.example.demo.config.AuditingConfig;
import com.example.demo.entity.Board;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@MockBean(AuditingConfig.class)
class ForEachEmptyCloseTest {

    @Autowired
    private BoardMapper boardMapper;


   @BeforeEach
    void test1() {

        List<Board> boards = List.of(
                Board.builder()
                        .title("AAA")
                        .content("1")
                        .isTop(0)
                        .build(),
                Board.builder()
                        .title("AAA")
                        .content("2")
                        .isTop(1)
                        .build(),
                Board.builder()
                        .title("AAA")
                        .content("3")
                        .isTop(0)
                        .build(),
                Board.builder()
                        .title("AAA")
                        .content("4")
                        .isTop(1)
                        .build(),
                Board.builder()
                        .title("AAA")
                        .content("")
                        .isTop(0)
                        .build()
        );

        for (Board board : boards) {
            boardMapper.save(board);
        }

    }

    /**
     * <foreach item="order" collection="pageable.sort" separator=", " close=",">
     *  open 과 close 는 collections 이 1개이상 존재할 때만 사용된다.
     *  즉 없는 경우 close 의 , 는 쿼리문에 작성되지 않는다
     *  따라서 order by  A, <foreach />, B 를 해야하는 상황에서 B 앞의 , 를 생략해서 사용하면 된다
     *  order by A <foreach close=","/> B
     */
    @Test
    @DisplayName("순회 돌았을 때 테스트")
    void test2() {

        List<Sort.Order> orders = List.of(
                Sort.Order.desc("title"),
                Sort.Order.desc("content")
        );

//        Sort.by(Sort.Order.by("title"));
//        Sort.by(Sort.Direction.DESC, "title")

        PageRequest title = PageRequest.of(0, 10, Sort.by(orders));

        List<Board> boards = boardMapper.findAllWithPageAndSort(title);

        boards.forEach(System.err::println);

    }

    @Test
    @DisplayName("순회 없을 때 테스트")
    void test3() {

        PageRequest pageable = PageRequest.of(0, 10);

        List<Board> boards = boardMapper.findAllWithPageAndSort(pageable);

        boards.forEach(System.err::println);
    }
}