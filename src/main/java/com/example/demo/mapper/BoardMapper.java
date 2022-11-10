package com.example.demo.mapper;

import com.example.demo.entity.Board;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@Mapper
public interface BoardMapper {

    List<Board> findAll();

    Integer save(Board board);

    Optional<Board> findById(Integer id);

    List<Board> findAllWithPage(@Param("pageable") Pageable pageable);

    List<Board> findAllWithPageAndSort(@Param("pageable") Pageable pageable);
}
