package org.example.framgiabookingtours.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.example.framgiabookingtours.dto.response.BookingResponseDTO;
import org.example.framgiabookingtours.dto.response.PaymentResponseDTO;
import org.example.framgiabookingtours.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.example.framgiabookingtours.service.PaymentService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/my-bookings")
@RequiredArgsConstructor
public class UserBookingController {

    private final BookingService bookingService;
    private final PaymentService paymentService;

    @GetMapping
    public String showMyBookings(Model model) {
        // 1. Lấy email của user đang đăng nhập
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName(); // Email

        // 2. Lấy danh sách booking của user đó
        // (Lưu ý: Hàm getMyBookings trong Service nên trả về List<BookingResponseDTO> như đã sửa ở các bước trước để an toàn)
        List<BookingResponseDTO> bookings = bookingService.getMyBookings(currentUsername);

        // 3. Đưa dữ liệu vào Model để Thymeleaf sử dụng
        model.addAttribute("bookings", bookings);

        // 4. Trả về tên file view (resources/templates/my-bookings.html)
        return "my-bookings";
    }
    @PostMapping("/{id}/pay")
    public String initiatePayment(@PathVariable Long id, HttpServletRequest request) {
        // Gọi PaymentService để lấy URL
        PaymentResponseDTO response = paymentService.createPaymentUrl(id, getCurrentUserEmail(), request);
        // Redirect trình duyệt sang URL đó
        return "redirect:" + response.getPaymentUrl();
    }
    private String getCurrentUserEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new IllegalStateException("User chưa được xác thực");
        }
        return authentication.getName();
    }
}
