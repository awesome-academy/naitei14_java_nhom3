package org.example.framgiabookingtours.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RefreshTokenRequestDTO {
    @NotBlank(message = "REFRESH_TOKEN_IS_REQUIRED")
    String refreshToken;
}