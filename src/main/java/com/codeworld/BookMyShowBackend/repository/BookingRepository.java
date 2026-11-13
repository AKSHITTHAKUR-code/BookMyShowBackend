package com.codeworld.BookMyShowBackend.repository;

import com.codeworld.BookMyShowBackend.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookingRepository  extends JpaRepository<Booking,Long> {

List<Booking> findByCustomerPhoneOrderByBookAtDesc(String customerphone);

Optional<Booking> findByIdAndCustomerPhone(Long id, String customerphone);

List<Booking> findByCustomerIdOrderByBookAtDesc(Long customerId);

Optional<Booking> findByIdAndCustomerId(Long id, Long customerId);


}
