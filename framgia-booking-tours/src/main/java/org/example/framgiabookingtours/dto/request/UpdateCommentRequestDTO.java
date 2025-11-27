package org.example.framgiabookingtours.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateCommentRequestDTO {

    @NotBlank(message = "Nội dung không được để trống")
    private String content;
}
