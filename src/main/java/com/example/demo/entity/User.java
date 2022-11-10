package com.example.demo.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

import javax.persistence.*;

@Entity
@Alias(value = "user")
@Table(name = "USER")
@NoArgsConstructor
@ToString
@Getter
public class User extends AbstractBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

}
