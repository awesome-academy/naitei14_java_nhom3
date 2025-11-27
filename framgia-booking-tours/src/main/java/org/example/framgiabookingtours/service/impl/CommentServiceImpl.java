package org.example.framgiabookingtours.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.framgiabookingtours.dto.request.CommentRequestDTO;
import org.example.framgiabookingtours.dto.request.UpdateCommentRequestDTO;
import org.example.framgiabookingtours.dto.response.CommentResponseDTO;
import org.example.framgiabookingtours.entity.Comment;
import org.example.framgiabookingtours.entity.Profile;
import org.example.framgiabookingtours.entity.Review;
import org.example.framgiabookingtours.entity.User;
import org.example.framgiabookingtours.exception.AppException;
import org.example.framgiabookingtours.exception.ErrorCode;
import org.example.framgiabookingtours.repository.CommentRepository;
import org.example.framgiabookingtours.repository.ReviewRepository;
import org.example.framgiabookingtours.repository.UserRepository;
import org.example.framgiabookingtours.service.CommentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public CommentResponseDTO createComment(CommentRequestDTO request, String userEmail) {
        // Tìm user
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        // Tìm review
        Review review = reviewRepository.findById(request.getReviewId())
                .orElseThrow(() -> new AppException(ErrorCode.REVIEW_NOT_FOUND));

        // Kiểm tra review có bị xóa không
        if (review.getIsDeleted()) {
            throw new AppException(ErrorCode.REVIEW_NOT_FOUND);
        }

        // Xử lý parent comment nếu có
        Comment parentComment = null;
        if (request.getParentCommentId() != null) {
            parentComment = commentRepository.findByIdAndIsDeletedFalse(request.getParentCommentId())
                    .orElseThrow(() -> new AppException(ErrorCode.COMMENT_NOT_FOUND));

            // Kiểm tra parent comment phải thuộc về cùng review
            if (!parentComment.getReview().getId().equals(request.getReviewId())) {
                throw new AppException(ErrorCode.COMMENT_NOT_BELONG_TO_REVIEW);
            }
        }

        // Tạo comment
        Comment comment = Comment.builder()
                .review(review)
                .user(user)
                .content(request.getContent())
                .parentComment(parentComment)
                .isDeleted(false)
                .build();

        Comment savedComment = commentRepository.save(comment);

        // Map sang DTO
        return mapToDTO(savedComment);
    }

    @Override
    @Transactional
    public CommentResponseDTO updateComment(Long commentId, UpdateCommentRequestDTO request, String userEmail) {
        // Tìm user
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        // Tìm comment
        Comment comment = commentRepository.findByIdAndIsDeletedFalse(commentId)
                .orElseThrow(() -> new AppException(ErrorCode.COMMENT_NOT_FOUND));

        // Kiểm tra comment thuộc về user
        if (!comment.getUser().getId().equals(user.getId())) {
            throw new AppException(ErrorCode.COMMENT_NOT_BELONG_TO_USER);
        }

        // Cập nhật content
        comment.setContent(request.getContent());

        Comment updatedComment = commentRepository.save(comment);

        // Map sang DTO
        return mapToDTO(updatedComment);
    }

    @Override
    @Transactional
    public void deleteComment(Long commentId, String userEmail) {
        // Tìm user
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        // Tìm comment
        Comment comment = commentRepository.findByIdAndIsDeletedFalse(commentId)
                .orElseThrow(() -> new AppException(ErrorCode.COMMENT_NOT_FOUND));

        // Kiểm tra comment thuộc về user
        if (!comment.getUser().getId().equals(user.getId())) {
            throw new AppException(ErrorCode.COMMENT_NOT_BELONG_TO_USER);
        }

        // Soft delete
        comment.setIsDeleted(true);
        comment.setDeletedAt(LocalDateTime.now());
        commentRepository.save(comment);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CommentResponseDTO> getCommentsByReviewId(Long reviewId, Pageable pageable) {
        // Kiểm tra review tồn tại
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new AppException(ErrorCode.REVIEW_NOT_FOUND));

        // Kiểm tra review có bị xóa không
        if (review.getIsDeleted()) {
            throw new AppException(ErrorCode.REVIEW_NOT_FOUND);
        }

        // Lấy danh sách comments theo review
        Page<Comment> comments = commentRepository.findByReviewId(reviewId, pageable);

        // Map sang DTO
        return comments.map(this::mapToDTO);
    }

    private CommentResponseDTO mapToDTO(Comment comment) {
        User user = comment.getUser();
        Profile profile = user.getProfile();

        // Tạo user info DTO
        CommentResponseDTO.UserInfoDTO userInfo = CommentResponseDTO.UserInfoDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .fullName(profile != null ? profile.getFullName() : null)
                .avatarUrl(profile != null ? profile.getAvatarUrl() : null)
                .build();

        // Tạo comment DTO
        return CommentResponseDTO.builder()
                .id(comment.getId())
                .reviewId(comment.getReview().getId())
                .content(comment.getContent())
                .createdAt(comment.getCreatedAt())
                .parentCommentId(comment.getParentComment() != null ? comment.getParentComment().getId() : null)
                .user(userInfo)
                .build();
    }
}
