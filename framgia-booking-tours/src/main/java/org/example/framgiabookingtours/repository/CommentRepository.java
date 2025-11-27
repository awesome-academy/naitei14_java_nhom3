package org.example.framgiabookingtours.repository;

import org.example.framgiabookingtours.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    long countByReviewIdAndIsDeletedFalse(Long reviewId);

    @Query("SELECT c FROM Comment c WHERE c.review.id = :reviewId AND c.isDeleted = false ORDER BY c.createdAt DESC")
    Page<Comment> findByReviewId(@Param("reviewId") Long reviewId, Pageable pageable);

    @Query("SELECT c FROM Comment c WHERE c.id = :id AND c.isDeleted = false")
    Optional<Comment> findByIdAndIsDeletedFalse(@Param("id") Long id);
}
