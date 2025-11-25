package org.example.framgiabookingtours.service;

import org.example.framgiabookingtours.dto.request.BookingRequestDTO;
import org.example.framgiabookingtours.entity.Booking;

import java.util.List;

public interface BookingService {

    Booking createBooking(BookingRequestDTO request, String userEmail);
    Booking cancelBooking(Long bookingId, String userEmail);
    List<Booking> getMyBookings(String userEmail);
}

