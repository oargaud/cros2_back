package com.futark.cros2.repository;

import com.futark.cros2.entity.Bd;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BdRepo extends JpaRepository<Bd,Long> {


//    @Query(value = "SELECT * FROM bd AS bd "
//            + "JOIN saga AS saga ON bd.saga_id = saga.id "
//            + "JOIN edition AS edition ON bd.edition_id = edition.id "
//            + "JOIN photo AS photo ON bd.photo_id = photo.id "
//            + "JOIN bd_artiste_illustrateurs AS bd_art_ill ON bd.id = bd_art_ill.bd_id "
//            + "JOIN artiste AS illustrateur ON bd_art_ill.artiste_id = illustrateur.id "
//            + "JOIN bd_artiste_scenaristes AS bd_art_sce ON bd.id = bd_art_sce.bd_id "
//            + "JOIN artiste AS scenariste ON bd_art_sce.artiste_id = scenariste.id "
//
//            + "WHERE (titre ILIKE %:#{#titre}%  OR :#{#titre} ='') "
//            + "AND (tome ILIKE %:#{#tome}% OR :#{#tome} ='') "
//            + "AND (statut ILIKE %:#{#statut}% OR :#{#statut} ='') "
//            + "AND (saga.name ILIKE %:#{#saga}% OR :#{#saga} ='') "
//            + "AND (edition.name ILIKE %:#{#edition}% OR :#{#edition} ='') "
//            + "AND (illustrateur.lastname ILIKE %:#{#illustrateur}% OR :#{#illustrateur} ='')"
//            + "AND (scenariste.lastname ILIKE %:#{#scenariste}% OR :#{#scenariste} ='')"
//            , nativeQuery = true)
//    Page<Bd> filtreBd(
//            @Param("titre") String titre,
//            @Param("tome") String tome,
//            @Param("statut") String statut,
//            @Param("saga") String saga,
//            @Param("edition") String edition,
//            @Param("illustrateur") String illustrateur,
//            @Param("scenariste") String scenariste,
//            Pageable pageable
//    );


    @Query(value = "SELECT * FROM bd WHERE id = :id" , nativeQuery = true)
    Bd trouveCtePutainDeBD(Long id);

    @Query(value = "SELECT COUNT(*) FROM bd WHERE statut = 'Possédé'", nativeQuery = true)
    Long totalPossede();



//
//    @Query(value = "SELECT * FROM bd AS bd "
//            + "LEFT JOIN saga AS saga ON bd.saga_id = saga.id "
//            + "LEFT JOIN edition AS edition ON bd.edition_id = edition.id "
//            + "LEFT JOIN photo AS photo ON bd.photo_id = photo.id "
//            + "LEFT JOIN bd_artiste_illustrateurs AS bd_art_ill ON bd.id = bd_art_ill.bd_id "
//            + "LEFT JOIN artiste AS illustrateur ON bd_art_ill.artiste_id = illustrateur.id "
//            + "LEFT JOIN bd_artiste_scenaristes AS bd_art_sce ON bd.id = bd_art_sce.bd_id "
//            + "LEFT JOIN artiste AS scenariste ON bd_art_sce.artiste_id = scenariste.id "
//
//            + "WHERE (titre ILIKE %:#{#titre}%  OR :#{#titre} ='') "
//            + "AND (tome ILIKE %:#{#tome}% OR :#{#tome} ='') "
//            + "AND (statut ILIKE %:#{#statut}% OR :#{#statut} ='') "
//            + "AND (saga.name ILIKE %:#{#saga}% OR :#{#saga} ='') "
//            + "AND (edition.name ILIKE %:#{#edition}% OR :#{#edition} ='') "
//            + "AND (illustrateur.lastname ILIKE %:#{#illustrateur}% OR :#{#illustrateur} ='')"
//            + "AND (scenariste.lastname ILIKE %:#{#scenariste}% OR :#{#scenariste} ='')"
//
//            + " ORDER BY saga.name , tome collate numeric LIMIT :limit OFFSET :offset "
//
//            , nativeQuery = true)
//    List<Bd> test(
//            @Param("titre") String titre,
//            @Param("tome") String tome,
//            @Param("statut") String statut,
//            @Param("saga") String saga,
//            @Param("edition") String edition,
//            @Param("illustrateur") String illustrateur,
//            @Param("scenariste") String scenariste,
//            @Param("limit") Integer limit,
//            @Param("offset") Integer offset
//    );


    @Query(value = "SELECT distinct bd.*,saga.name,bd.tome collate numeric "
            + "FROM bd AS bd "
            + "LEFT JOIN saga AS saga ON bd.saga_id = saga.id "
            + "LEFT JOIN edition AS edition ON bd.edition_id = edition.id "
            + "LEFT JOIN photo AS photo ON bd.photo_id = photo.id "
            + "LEFT JOIN bd_artiste_illustrateurs AS bd_art_ill ON bd.id = bd_art_ill.bd_id "
            + "LEFT JOIN artiste AS illustrateur ON bd_art_ill.artiste_id = illustrateur.id "
            + "LEFT JOIN bd_artiste_scenaristes AS bd_art_sce ON bd.id = bd_art_sce.bd_id "
            + "LEFT JOIN artiste AS scenariste ON bd_art_sce.artiste_id = scenariste.id "

            + "WHERE (titre ILIKE %:#{#titre}%  ) "
            + "AND (tome ILIKE %:#{#tome}%) "
            + "AND (statut ILIKE %:#{#statut}% ) "
            + "AND (saga.name ILIKE %:#{#saga}% ) "
            + "AND (edition.name ILIKE %:#{#edition}% ) "
            + "AND (illustrateur.lastname ILIKE %:#{#illustrateur}% )"
            + "AND (scenariste.lastname ILIKE %:#{#scenariste}% )"

            + " ORDER BY saga.name , tome collate numeric"
            + " LIMIT :limit OFFSET :offset "

            , nativeQuery = true)
    List<Bd> test(
            @Param("titre") String titre,
            @Param("tome") String tome,
            @Param("statut") String statut,
            @Param("saga") String saga,
            @Param("edition") String edition,
            @Param("illustrateur") String illustrateur,
            @Param("scenariste") String scenariste,
            @Param("limit") Integer limit,
            @Param("offset") Integer offset
    );



//    @Query(value = "SELECT bd.id, bd.titre, bd.tome, bd.statut, "
//            + "saga.name, edition.name, photo.id, photo.photo"
//            + " FROM bd AS bd "
//            + "LEFT JOIN saga AS saga ON bd.saga_id = saga.id "
//            + "LEFT JOIN edition AS edition ON bd.edition_id = edition.id "
//            + "LEFT JOIN photo AS photo ON bd.photo_id = photo.id "
//            + "LEFT JOIN bd_artiste_illustrateurs AS bd_art_ill ON bd.id = bd_art_ill.bd_id "
//            + "LEFT JOIN artiste AS illustrateur ON bd_art_ill.artiste_id = illustrateur.id "
//            + "LEFT JOIN bd_artiste_scenaristes AS bd_art_sce ON bd.id = bd_art_sce.bd_id "
//            + "LEFT JOIN artiste AS scenariste ON bd_art_sce.artiste_id = scenariste.id "
//
//            + "WHERE (titre ILIKE %:#{#titre}%  ) "
//            + "AND (tome ILIKE %:#{#tome}% ) "
//            + "AND (statut ILIKE %:#{#statut}% ) "
//            + "AND (saga.name ILIKE %:#{#saga}% ) "
//            + "AND (edition.name ILIKE %:#{#edition}% ) "
//            + "AND (illustrateur.lastname ILIKE %:#{#illustrateur}% )"
//            + "AND (scenariste.lastname ILIKE %:#{#scenariste}% )"
//
//            + "GROUP BY bd.id, bd.titre, bd.tome, bd.statut, saga.name, edition.name, photo.id, photo.photo "
//
//            + " ORDER BY saga.name , tome collate numeric "
//            + " LIMIT :limit OFFSET :offset "
//
//            , nativeQuery = true)
//    List<Bd> test(
//            @Param("titre") String titre,
//            @Param("tome") String tome,
//            @Param("statut") String statut,
//            @Param("saga") String saga,
//            @Param("edition") String edition,
//            @Param("illustrateur") String illustrateur,
//            @Param("scenariste") String scenariste,
//            @Param("limit") Integer limit,
//            @Param("offset") Integer offset
//    );

//    @Query(value =
//            "SELECT COUNT(*) FROM bd AS bd "
//            + "LEFT JOIN saga AS saga ON bd.saga_id = saga.id "
//            + "LEFT JOIN edition AS edition ON bd.edition_id = edition.id "
//            + "LEFT JOIN photo AS photo ON bd.photo_id = photo.id "
//            + "LEFT JOIN bd_artiste_illustrateurs AS bd_art_ill ON bd.id = bd_art_ill.bd_id "
//            + "LEFT JOIN artiste AS illustrateur ON bd_art_ill.artiste_id = illustrateur.id "
//            + "LEFT JOIN bd_artiste_scenaristes AS bd_art_sce ON bd.id = bd_art_sce.bd_id "
//            + "LEFT JOIN artiste AS scenariste ON bd_art_sce.artiste_id = scenariste.id "
//
//            + "WHERE (titre ILIKE %:#{#titre}%  OR :#{#titre} ='') "
//            + "AND (tome ILIKE %:#{#tome}% OR :#{#tome} ='') "
//            + "AND (statut ILIKE %:#{#statut}% OR :#{#statut} ='') "
//            + "AND (saga.name ILIKE %:#{#saga}% OR :#{#saga} ='') "
//            + "AND (edition.name ILIKE %:#{#edition}% OR :#{#edition} ='') "
//            + "AND (illustrateur.lastname ILIKE %:#{#illustrateur}% OR :#{#illustrateur} ='')"
//            + "AND (scenariste.lastname ILIKE %:#{#scenariste}% OR :#{#scenariste} ='')"
//
//            , nativeQuery = true)
//    Long getTotal(
//            @Param("titre") String titre,
//           @Param("tome") String tome,
//           @Param("statut") String statut,
//           @Param("saga") String saga,
//           @Param("edition") String edition,
//           @Param("illustrateur") String illustrateur,
//           @Param("scenariste") String scenariste
//    );


    @Query(value =
            "SELECT COUNT(DISTINCT (bd.titre,bd.tome)) FROM bd AS bd "

            + "LEFT JOIN saga AS saga ON bd.saga_id = saga.id "
            + "LEFT JOIN edition AS edition ON bd.edition_id = edition.id "
            + "LEFT JOIN photo AS photo ON bd.photo_id = photo.id "
            + "LEFT JOIN bd_artiste_illustrateurs AS bd_art_ill ON bd.id = bd_art_ill.bd_id "
            + "LEFT JOIN artiste AS illustrateur ON bd_art_ill.artiste_id = illustrateur.id "
            + "LEFT JOIN bd_artiste_scenaristes AS bd_art_sce ON bd.id = bd_art_sce.bd_id "
            + "LEFT JOIN artiste AS scenariste ON bd_art_sce.artiste_id = scenariste.id "

            + "WHERE (titre ILIKE %:#{#titre}%  ) "
            + "AND (tome ILIKE %:#{#tome}% ) "
            + "AND (statut ILIKE %:#{#statut}% ) "
            + "AND (saga.name ILIKE %:#{#saga}% ) "
            + "AND (edition.name ILIKE %:#{#edition}% ) "
            + "AND (illustrateur.lastname ILIKE %:#{#illustrateur}% )"
            + "AND (scenariste.lastname ILIKE %:#{#scenariste}% )"

            , nativeQuery = true)
    Long getTotal(
            @Param("titre") String titre,
            @Param("tome") String tome,
            @Param("statut") String statut,
            @Param("saga") String saga,
            @Param("edition") String edition,
            @Param("illustrateur") String illustrateur,
            @Param("scenariste") String scenariste
    );






























}
























//    SELECT bd.id,
//        bd.titre,
//        bd.tome,
//        bd.statut,
//        saga.name,
//        edition.name,
//        photo.id,
//        photo.photo
//
//        FROM bd AS bd
//        LEFT JOIN saga AS saga ON bd.saga_id = saga.id
//        LEFT JOIN edition AS edition ON bd.edition_id = edition.id
//        LEFT JOIN photo AS photo ON bd.photo_id = photo.id
//        LEFT JOIN bd_artiste_illustrateurs AS bd_art_ill ON bd.id = bd_art_ill.bd_id
//        LEFT JOIN artiste AS illustrateur ON bd_art_ill.artiste_id = illustrateur.id
//        LEFT JOIN bd_artiste_scenaristes AS bd_art_sce ON bd.id = bd_art_sce.bd_id
//        LEFT JOIN artiste AS scenariste ON bd_art_sce.artiste_id = scenariste.id
//
//        WHERE (titre ILIKE '%%' )
//        AND (tome ILIKE '%%')
//        AND (statut ILIKE '%%' )
//        AND (saga.name ILIKE '%%' )
//        AND (edition.name ILIKE '%%' )
//        AND (illustrateur.lastname ILIKE '%%' )
//        AND (scenariste.lastname ILIKE '%%' )
//
//        GROUP BY bd.id, bd.titre, bd.tome, bd.statut, saga.name, edition.name, photo.id, photo.photo
//
//        ORDER BY saga.name , tome collate numeric
//        LIMIT 100 OFFSET 0












//    SELECT distinct bd.*,saga.name,bd.tome collate numeric
//        FROM bd AS bd
//        LEFT JOIN saga AS saga ON bd.saga_id = saga.id
//        LEFT JOIN edition AS edition ON bd.edition_id = edition.id
//        LEFT JOIN photo AS photo ON bd.photo_id = photo.id
//        LEFT JOIN bd_artiste_illustrateurs AS bd_art_ill ON bd.id = bd_art_ill.bd_id
//        LEFT JOIN artiste AS illustrateur ON bd_art_ill.artiste_id = illustrateur.id
//        LEFT JOIN bd_artiste_scenaristes AS bd_art_sce ON bd.id = bd_art_sce.bd_id
//        LEFT JOIN artiste AS scenariste ON bd_art_sce.artiste_id = scenariste.id
//
//        WHERE (titre ILIKE '%%'  )
//        AND (tome ILIKE  '%%' )
//        AND (statut ILIKE   '%%'  )
//        AND (saga.name ILIKE  '%ogre%'  )
//        AND (edition.name ILIKE  '%%'  )
//        AND (illustrateur.lastname ILIKE  '%%'  )
//        AND (scenariste.lastname ILIKE  '%%'  )
//
//        ORDER BY saga.name , bd.tome collate numeric
//        LIMIT 100 OFFSET 0

