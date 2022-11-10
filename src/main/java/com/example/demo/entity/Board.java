package com.example.demo.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

import javax.persistence.*;

@Entity
@Alias(value = "board")
@Table(name = "BOARD")
@NoArgsConstructor
@ToString
@Getter
public class Board extends AbstractBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    private String content;

    private Integer isTop;

    @Builder
    private Board(String title, String content, Integer isTop) {
        this.title = title;
        this.content = content;
        this.isTop = isTop;
    }
}
