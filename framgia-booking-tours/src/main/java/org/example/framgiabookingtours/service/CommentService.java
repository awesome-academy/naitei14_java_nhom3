package org.example.framgiabookingtours.service;

import org.example.framgiabookingtours.dto.request.CommentRequestDTO;
import org.example.framgiabookingtours.dto.request.UpdateCommentRequestDTO;
import org.example.framgiabookingtours.dto.response.CommentResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommentService {

    CommentResponseDTO createComment(CommentRequestDTO request, String userEmail);

    CommentResponseDTO updateComment(Long commentId, UpdateCommentRequestDTO request, String userEmail);

    void deleteComment(Long commentId, String userEmail);

    Page<CommentResponseDTO> getCommentsByReviewId(Long reviewId, Pageable pageable);
}
