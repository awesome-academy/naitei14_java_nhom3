package org.example.framgiabookingtours.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CommentRequestDTO {

    @NotNull(message = "Review ID không được để trống")
    private Long reviewId;

    @NotBlank(message = "Nội dung không được để trống")
    private String content;

    private Long parentCommentId;
}
