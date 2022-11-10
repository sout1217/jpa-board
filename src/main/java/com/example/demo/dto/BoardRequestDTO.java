package com.example.demo.dto;

import com.example.demo.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardRequestDTO {

    private String title;

    public Board toEntity() {
        return Board.builder()
                .title(this.title)
                .build();
    }
}
