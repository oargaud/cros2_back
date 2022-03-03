package com.futark.cros2.repository;

import com.futark.cros2.entity.Artiste;
import com.futark.cros2.entity.Saga;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ArtisteRepo extends JpaRepository<Artiste,Long> {


    @Query(value = "SELECT * FROM artiste " +
            "WHERE (lastname ILIKE %:#{#lastname}%  OR :#{#lastname} ='')" +
            "AND (firstname ILIKE %:#{#firstname}%  OR :#{#firstname} ='')"
            , nativeQuery = true)
    Page<Artiste> filtreArtiste(
            @Param("lastname") String lastname,
            @Param("firstname") String firstname,
            Pageable pageable
    );

}
