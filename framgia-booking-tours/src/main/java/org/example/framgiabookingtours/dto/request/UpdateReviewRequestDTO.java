package org.example.framgiabookingtours.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class UpdateReviewRequestDTO {

    @NotBlank(message = "Nội dung không được để trống")
    private String content;

    @NotNull(message = "Rating không được để trống")
    @Min(value = 1, message = "Rating phải từ 1 đến 5")
    @Max(value = 5, message = "Rating phải từ 1 đến 5")
    private Integer rating;
}
