package org.example.framgiabookingtours.service;

import org.example.framgiabookingtours.dto.request.CommentRequestDTO;
import org.example.framgiabookingtours.dto.request.UpdateCommentRequestDTO;
import org.example.framgiabookingtours.dto.response.CommentResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CommentService {

    CommentResponseDTO createComment(CommentRequestDTO request, String userEmail);

    CommentResponseDTO updateComment(Long commentId, UpdateCommentRequestDTO request, String userEmail);

    void deleteComment(Long commentId, String userEmail);

    Page<CommentResponseDTO> getCommentsByReviewId(Long reviewId, Pageable pageable);

    /**
     * Lấy toàn bộ comments theo review và build dạng thread (cây).
     */
    List<CommentResponseDTO> getCommentThreadByReviewId(Long reviewId);
}
