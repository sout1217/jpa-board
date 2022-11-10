package com.example.demo.dto;

import java.time.LocalDateTime;

public interface BaseResponseDTO {
    LocalDateTime getCreatedAt();
    LocalDateTime getUpdatedAt();
    String getLastModifiedBy();
    String getCreatedBy();
}
