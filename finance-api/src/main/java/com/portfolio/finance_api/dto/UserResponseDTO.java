package com.portfolio.finance_api.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record UserResponseDTO(UUID id, String name, String email, LocalDateTime createdAt){
}
