package org.example.framgiabookingtours.repository;

import jakarta.persistence.LockModeType;
import org.example.framgiabookingtours.entity.Tour;
import org.example.framgiabookingtours.enums.TourStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface TourRepository extends JpaRepository<Tour, Long>, JpaSpecificationExecutor<Tour> {
    
	// Page<Tour> findByStatus(TourStatus status, Pageable pageable);
	
	long countByStatus(TourStatus status);
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Query("SELECT t FROM Tour t WHERE t.id = :id")
	Optional<Tour> findByIdWithLock(@Param("id") Long id);
} //Kế thừa JpaSpecificationExecutor để hỗ trợ lọc phức tạp (sẽ dùng trong F2)