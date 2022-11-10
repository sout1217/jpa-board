package com.example.demo.mapper;

import com.example.demo.entity.Board;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardDao {

    List<Board> findAll();

    


}
