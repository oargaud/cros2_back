package com.futark.cros2.repository;

import com.futark.cros2.entity.Control;
import com.futark.cros2.entity.Edition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ControlRepo extends JpaRepository<Control,Long> {


//    @Query(value = "SELECT * FROM edition " +
//            "WHERE (name ILIKE %:#{#name}%  OR :#{#name} ='')"
//            , nativeQuery = true)
//    Page<Edition> filtreEdition(
//            @Param("name") String name,
//            Pageable pageable
//    );

    List<Control> findAllByOrderByTypeAscStageAscTypeAscNameAsc();


}
