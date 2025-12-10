package org.example.framgiabookingtours.controller.admin;

import org.example.framgiabookingtours.entity.Payment;
import org.example.framgiabookingtours.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin/payments")
@RequiredArgsConstructor
public class AdminPaymentController {

    private final PaymentService paymentService;

    // 1. Hiển thị danh sách
    @GetMapping
    public String showPaymentsList(Model model) {
        List<Payment> paymentsList = paymentService.getAllPaymentsDesc();

        // Đưa dữ liệu vào model để Thymeleaf hiển thị
        model.addAttribute("paymentsList", paymentsList);

        // Trả về file HTML (src/main/resources/templates/admin/payments-list.html)
        return "admin/payments-list";
    }

    // 2. Xử lý nút "Đánh dấu Hoàn tiền"
    @PostMapping("/{id}/mark-refunded")
    public String markRefunded(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            paymentService.markAsRefunded(id);
            redirectAttributes.addFlashAttribute("successMessage", "Đã cập nhật trạng thái hoàn tiền thành công!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Lỗi: " + e.getMessage());
        }
        // Load lại trang danh sách
        return "redirect:/admin/payments";
    }
}
