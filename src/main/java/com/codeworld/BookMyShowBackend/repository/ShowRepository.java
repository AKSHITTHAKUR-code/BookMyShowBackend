package com.codeworld.BookMyShowBackend.repository;


import com.codeworld.BookMyShowBackend.entity.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;


public interface ShowRepository extends JpaRepository<Show,Long> {

 //   boolean existsByMovieIdTheaderIdAndStartTime(LocalDateTime startTime);
    boolean existsByMovieIdAndTheaterIdAndStartTime(Long movieId, Long theaterId, LocalDateTime startTime);

    @Query("select s from Show s join fetch s.movie m join fetch s.theater t " +
            "where s.active = true and m.active = true and t.city = :city " +
            "and s.startTime >= :from and s.startTime < :to order by s.startTime")
    List<Show>findActiveShow(String city,LocalDateTime from,LocalDateTime to);

}
