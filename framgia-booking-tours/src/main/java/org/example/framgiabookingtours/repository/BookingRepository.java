package org.example.framgiabookingtours.repository;

import org.example.framgiabookingtours.entity.Booking;
import org.example.framgiabookingtours.enums.BookingStatus;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    // Spring Data JPA sẽ tự động tạo các hàm CRUD (save, findById, findAll, delete...)

    // Chúng ta sẽ thêm các hàm truy vấn tùy chỉnh ở đây sau nếu cần
//     Ví dụ: List<Booking> findByUserId(Long userId);
    @EntityGraph(attributePaths = {"user", "tour"})
    List<Booking> findByUserId(Long userId);

    @EntityGraph(attributePaths = {"user", "tour", "user.profile"})
    @Query("SELECT b FROM Booking b ORDER BY b.bookingDate DESC") // Sắp xếp mới nhất lên đầu
    List<Booking> findAllWithUserAndTour();

    long countByStatus(BookingStatus status);
}
