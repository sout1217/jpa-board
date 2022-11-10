package com.example.demo.dto;

import com.example.demo.entity.Board;
import lombok.Data;

import java.util.List;

@Data
public class BoardResponseDTO extends AbstractBaseResponseDTO {

    private Integer id;
    private String title;

    public static List<BoardResponseDTO> from(List<Board> boards) {
        // MEMO: 2022/10/23 Stream toList 사용방법
        return boards.stream()
                .map(BoardResponseDTO::from)
                .toList();
    }

    public static BoardResponseDTO from(Board board) {
        BoardResponseDTO dto = new BoardResponseDTO();
        dto.id = board.getId();
        dto.title = board.getTitle();
        dto.createdAt = board.getCreatedAt();
        dto.updatedAt = board.getUpdatedAt();
        dto.createdBy = board.getCreatedBy();
        dto.lastModifiedBy = board.getLastModifiedBy();
        return dto;
    }
}
