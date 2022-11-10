package com.example.demo.service;

import com.example.demo.dto.BoardRequestDTO;
import com.example.demo.entity.Board;
import com.example.demo.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardMapperServiceImpl implements BoardService {

    private final BoardMapper boardMapper;

    @Override
    public Integer save(BoardRequestDTO dto) {
        return boardMapper.save(dto.toEntity());
    }

    @Override
    public List<Board> getBoards() {
        return boardMapper.findAll();
    }

    @Override
    public Page<Board> getBoardsWithPage(Pageable pageable) {
        List<Board> contents = boardMapper.findAllWithPage(pageable);
        return new PageImpl<>(contents, pageable, contents.size());
    }

    @Override
    public Board getBoard(Integer id) {
        return boardMapper.findById(id).orElseThrow(() -> new IllegalArgumentException("비어있음"));
    }

    @Override
    public Integer save(int channelId, String title) {
        return null;
    }

}
