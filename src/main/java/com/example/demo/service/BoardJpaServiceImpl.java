package com.example.demo.service;

import com.example.demo.config.Role;
import com.example.demo.dto.BoardRequestDTO;
import com.example.demo.entity.Board;
import com.example.demo.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardJpaServiceImpl implements BoardService{

    private final BoardRepository boardRepository;

    @Override
    @Role(variables = "dto.boardSeq")
    @Transactional
    public Integer save(BoardRequestDTO dto) {
        // MEMO: 2022/10/23 트랜잭션이 활성화 되어 있는지 판단한다
        boolean synchronizationActive = TransactionSynchronizationManager.isActualTransactionActive();
        log.info("트랜잭션 활성화 여부: {}", synchronizationActive);

        return boardRepository.save(dto.toEntity()).getId();
    }

    @Override
    public List<Board> getBoards() {
        // MEMO: 2022/10/23 트랜잭션이 활성화 되어 있는지 판단한다
        boolean synchronizationActive = TransactionSynchronizationManager.isActualTransactionActive();
        log.info("트랜잭션 활성화 여부: {}", synchronizationActive);
        return boardRepository.findAll();
    }

    @Override
    public Page<Board> getBoardsWithPage(Pageable pageable) {
        return boardRepository.findAll(pageable);
    }

    @Override
    public Board getBoard(Integer id) {
        return boardRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    @Role(variables = {"channelId", "title"})
    @Transactional
    public Integer save(int channelId, String title) {
        return null;
    }
}
