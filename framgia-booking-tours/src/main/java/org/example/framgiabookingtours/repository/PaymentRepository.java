package org.example.framgiabookingtours.repository;

import org.example.framgiabookingtours.entity.Payment;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    @EntityGraph(attributePaths = {"booking", "booking.user"})
    @Query("SELECT p FROM Payment p ORDER BY p.paymentDate DESC")
    List<Payment> findAllWithBookingAndUser();
}
