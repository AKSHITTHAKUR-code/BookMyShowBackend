package com.codeworld.BookMyShowBackend.repository;


import com.codeworld.BookMyShowBackend.entity.ShowSeat;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ShowSeatRepository extends JpaRepository<ShowSeat,Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select s.seatLabel from ShowSeat s where s.show.id=:showId and s.reserved=false order by s.id")
    List<String> findAvailableLabels(@Param("showId")Long showId);

    @Query("select s from ShowSeat s " +
            "where s.show.id = :showId and s.seatLabel in :labels")
    List<ShowSeat>findForUpdate(@Param("showId")Long showid,
                                @Param("labels")List<String> labels);

}






