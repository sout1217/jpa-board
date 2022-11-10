package com.example.demo.service;

import com.example.demo.entity.Board;
import com.example.demo.mapper.BoardMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardMapper boardMapper;

    public void save(Board board) {
        boardMapper.save(board);
    }

    public List<Board> getBoards() {
        return boardMapper.findAll();
    }

    public PageInfo<Board> getBoardsWithPage(Integer boardId, Pageable pageable) {
        PageHelper.startPage(pageable.getPageNumber(), pageable.getPageSize());
        return new PageInfo<>(boardMapper.findAllWithPage(boardId, pageable));
    }

    public Board getBoard(Integer id) {
        return boardMapper.findById(id).orElseThrow(() -> new IllegalArgumentException("비어있음"));
    }

}
