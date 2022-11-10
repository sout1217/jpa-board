package com.example.demo.service;

import com.example.demo.entity.Board;
import com.example.demo.repository.BoardRepository;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardJpaService implements BoardService{

    private final BoardRepository boardRepository;

    private final TransactionStatus transactionStatus;

    @Override
    @Transactional
    public Integer save(Board board) {

        return boardRepository.save(board).getId();
    }

    @Override
    public List<Board> getBoards() {
        return null;
    }

    @Override
    public PageInfo<Board> getBoardsWithPage(Integer boardId, Pageable pageable) {
        return null;
    }

    @Override
    public Board getBoard(Integer id) {
        return null;
    }
}
