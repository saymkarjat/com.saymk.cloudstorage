package com.saymk.cloudstorage.auth.dto.request;

import lombok.Builder;

@Builder
public record UserLoginRequestDTO(String username, String password) {
}
