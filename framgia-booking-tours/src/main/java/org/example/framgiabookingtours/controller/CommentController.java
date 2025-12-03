package org.example.framgiabookingtours.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.framgiabookingtours.dto.ApiResponse;
import org.example.framgiabookingtours.dto.request.CommentRequestDTO;
import org.example.framgiabookingtours.dto.request.UpdateCommentRequestDTO;
import org.example.framgiabookingtours.dto.response.CommentResponseDTO;
import org.example.framgiabookingtours.service.CommentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ApiResponse<CommentResponseDTO> createComment(
            @Valid @RequestBody CommentRequestDTO request,
            @RequestHeader(value = "X-User-Email", required = false) String headerEmail,
            Authentication authentication) {

        String userEmail = (authentication != null) ? authentication.getName() : headerEmail;
        CommentResponseDTO response = commentService.createComment(request, userEmail);

        return ApiResponse.<CommentResponseDTO>builder()
                .code(1000)
                .message("Comment created successfully")
                .result(response)
                .build();
    }

    @PutMapping("/{commentId}")
    public ApiResponse<CommentResponseDTO> updateComment(
            @PathVariable Long commentId,
            @Valid @RequestBody UpdateCommentRequestDTO request,
            @RequestHeader(value = "X-User-Email", required = false) String headerEmail,
            Authentication authentication) {

        String userEmail = (authentication != null) ? authentication.getName() : headerEmail;
        CommentResponseDTO response = commentService.updateComment(commentId, request, userEmail);

        return ApiResponse.<CommentResponseDTO>builder()
                .code(1000)
                .message("Comment updated successfully")
                .result(response)
                .build();
    }

    @DeleteMapping("/{commentId}")
    public ApiResponse<Void> deleteComment(
            @PathVariable Long commentId,
            @RequestHeader(value = "X-User-Email", required = false) String headerEmail,
            Authentication authentication) {

        String userEmail = (authentication != null) ? authentication.getName() : headerEmail;
        commentService.deleteComment(commentId, userEmail);

        return ApiResponse.<Void>builder()
                .code(1000)
                .message("Comment deleted successfully")
                .build();
    }

    @GetMapping
    public ApiResponse<Page<CommentResponseDTO>> getCommentsByReviewId(
            @RequestParam Long reviewId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<CommentResponseDTO> comments = commentService.getCommentsByReviewId(reviewId, pageable);

        return ApiResponse.<Page<CommentResponseDTO>>builder()
                .code(1000)
                .message("Get comments successfully")
                .result(comments)
                .build();
    }

    @GetMapping("/thread")
    public ApiResponse<List<CommentResponseDTO>> getCommentThreadByReviewId(
            @RequestParam Long reviewId) {

        List<CommentResponseDTO> thread = commentService.getCommentThreadByReviewId(reviewId);

        return ApiResponse.<List<CommentResponseDTO>>builder()
                .code(1000)
                .message("Get comment thread successfully")
                .result(thread)
                .build();
    }
}
