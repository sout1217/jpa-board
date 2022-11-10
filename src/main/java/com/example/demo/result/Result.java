package com.example.demo.result;

import com.example.demo.dto.BoardResponseDTO;
import com.example.demo.entity.Board;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Getter
@Builder
public class Result<T> {
    private T data;

    public static ResponseEntity<String> created(Integer entityId) {
        return ResponseEntity.created(getUri(entityId)).build();
    }

    private static URI getUri(Integer entityId) {
        return UriComponentsBuilder
                .fromPath(String.format("/boards/%s", entityId))
                .encode()
                .build()
                .toUri();
    }

    public static ResponseEntity<Result<List<BoardResponseDTO>>> ok(List<Board> entity) {
        return ResponseEntity.ok(
                Result
                        .<List<BoardResponseDTO>>builder()
                        .data(BoardResponseDTO.from(entity))
                        .build()
        );
    }

    public static ResponseEntity<Result<Page<BoardResponseDTO>>> ok(Page<Board> boardsWithPage) {
        return ResponseEntity.ok(
                Result
                        .<Page<BoardResponseDTO>>builder()
                        .data(boardsWithPage.map(BoardResponseDTO::from))
                        .build()
        );
    }
}
