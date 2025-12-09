/*
  Ticket: Fix Data truncated for column 'status'
  Description: Cập nhật cột status của bảng bookings để hỗ trợ thêm các trạng thái mới (CONFIRMED, REFUNDED, COMPLETED).
  Quan trọng: Khi MODIFY ENUM trong MySQL, ta phải liệt kê lại TOÀN BỘ các giá trị cũ + giá trị mới.
*/

ALTER TABLE bookings
    MODIFY COLUMN status ENUM('PENDING', 'PAID', 'CANCELLED', 'CONFIRMED', 'REFUNDED', 'COMPLETED') NOT NULL DEFAULT 'PENDING';