package org.example.framgiabookingtours.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponseDTO {

    private Long id;
    private Long reviewId;
    private String content;
    private LocalDateTime createdAt;
    private Long parentCommentId;

    // User info
    private UserInfoDTO user;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserInfoDTO {
        private Long id;
        private String email;
        private String fullName;
        private String avatarUrl;
    }
}
