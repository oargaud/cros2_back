package com.futark.cros2.repository;

import com.futark.cros2.entity.Bd;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BdRepo extends JpaRepository<Bd,Long> {



    @Query(value = "SELECT * FROM bd " +
//            "JOIN saga ON bd.saga_id = saga.id " +
//            "JOIN FETCH  bd.saga_id" +
            "WHERE (titre ILIKE %:#{#titre}%  OR :#{#titre} ='')" +
            "AND (tome ILIKE %:#{#tome}% OR :#{#tome} ='')" +
            "AND (statut ILIKE %:#{#statut}% OR :#{#statut} ='')"
//            +
//            "AND (saga ILIKE %:#{#saga}% OR :#{#saga} ='')"
//            +
//            "AND (artiste ILIKE %:#{#artiste}% OR :#{#artiste} ='')"
            , nativeQuery = true)
    Page<Bd> filtreBd(
            @Param("titre") String titre,
            @Param("tome") String tome,
            @Param("statut") String statut,
//            @Param("saga") String saga,
//            @Param("artiste") String artiste,
            Pageable pageable
    );



    @Query(value = "SELECT * FROM bd WHERE id = :id"

            , nativeQuery = true)
    Bd trouveCtePutainDeBD(Long id);

    @Query(value = "SELECT COUNT(*) FROM bd WHERE statut = 'Possédé'", nativeQuery = true)
    Long totalPossede();


    
}
