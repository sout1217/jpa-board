package com.example.demo.dto;

import com.example.demo.entity.Board;
import lombok.Data;

import java.util.List;

@Data
public class BoardResponseDTOAbstract extends AbstractBaseResponseDTO {

    private Integer id;
    private String title;

    public static List<BoardResponseDTOAbstract> from(List<Board> boards) {
        // MEMO: 2022/10/23 Stream toList 사용방법
        return boards.stream()
                .map(BoardResponseDTOAbstract::from)
                .toList();
    }

    public static BoardResponseDTOAbstract from(Board board) {
        BoardResponseDTOAbstract dto = new BoardResponseDTOAbstract();
        dto.id = board.getId();
        dto.title = board.getTitle();
        dto.createdAt = board.getCreatedAt();
        dto.updatedAt = board.getUpdatedAt();
        dto.createdBy = board.getCreatedBy();
        dto.lastModifiedBy = board.getLastModifiedBy();
        return dto;
    }
}
