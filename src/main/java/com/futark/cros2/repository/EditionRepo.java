package com.futark.cros2.repository;

import com.futark.cros2.entity.Edition;
import com.futark.cros2.entity.Saga;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EditionRepo extends JpaRepository<Edition,Long> {


    @Query(value = "SELECT * FROM edition " +
            "WHERE (name ILIKE %:#{#name}%  OR :#{#name} ='')"
            , nativeQuery = true)
    Page<Edition> filtreEdition(
            @Param("name") String name,
            Pageable pageable
    );

}
