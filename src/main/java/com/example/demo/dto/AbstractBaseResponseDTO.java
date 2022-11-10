package com.example.demo.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public abstract class AbstractBaseResponseDTO {
    protected LocalDateTime createdAt;
    protected LocalDateTime updatedAt;
    protected String lastModifiedBy;
    protected String createdBy;
}
