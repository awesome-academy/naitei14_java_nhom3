package org.example.framgiabookingtours.service;

import org.example.framgiabookingtours.dto.response.PaymentResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.example.framgiabookingtours.entity.Payment;

import java.util.List;

public interface PaymentService {
    PaymentResponseDTO createPaymentUrl(Long bookingId, String userEmail, HttpServletRequest request);
    String handlePaymentCallback(HttpServletRequest request);
    List<Payment> getAllPaymentsDesc();
    Payment markAsRefunded(Long paymentId);
}