package com.example.demo.service;

import com.example.demo.dto.BoardRequestDTO;
import com.example.demo.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BoardService {
    Integer save(BoardRequestDTO board);

    List<Board> getBoards();

    Page<Board> getBoardsWithPage(Pageable pageable);

    Board getBoard(Integer id);

    Integer save(int channelId, String title);
}
