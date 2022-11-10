package com.example.demo.controller;

import com.example.demo.dto.BoardRequestDTO;
import com.example.demo.dto.BoardResponseDTO;
import com.example.demo.result.Result;
import com.example.demo.service.BoardService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BoardController {

    private final BoardService boardService;
    private final BoardService boardMapperService;

    public BoardController(
            @Qualifier("boardJpaServiceImpl") BoardService boardService,
            @Qualifier("boardMapperServiceImpl") BoardService boardMapperService
    ) {
        this.boardService = boardService;
        this.boardMapperService = boardMapperService;
    }


    /**
     * @apiNote 보드 생성
     */
    @PostMapping("/boards")
    public ResponseEntity<String> createBoard(
            @RequestBody BoardRequestDTO dto
    ) {
        return Result.created(boardService.save(1, dto.getTitle()));
    }

    /**
     * @apiNote 보드 리스트로 조회
     */
    @GetMapping("/boardList")
    public ResponseEntity<Result<List<BoardResponseDTO>>> getBoards() {
        // MEMO: 2022/10/23 데이터를 일관되게 내려주기 위해 Result 로 한번 감싼다
        return Result.ok(boardService.getBoards());
    }

    /**
     * @apiNote 보드 리스트로 조회
     */
    @GetMapping("/boards")
    public ResponseEntity<Result<Page<BoardResponseDTO>>> getBoardWithPage(
            @PageableDefault Pageable pageable
    ) {
        // MEMO: 2022/10/23 데이터를 일관되게 내려주기 위해 Result 로 한번 감싼다
        return Result.ok(boardService.getBoardsWithPage(pageable));
    }

}
