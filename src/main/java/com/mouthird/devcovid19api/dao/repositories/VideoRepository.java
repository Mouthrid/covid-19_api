package com.mouthird.devcovid19api.dao.repositories;

import com.mouthird.devcovid19api.dao.entity.Video;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

/**
 * Video Repository, use QueryDSL
 */
@Repository
public interface VideoRepository extends
        JpaRepository<Video, Long>, QuerydslPredicateExecutor<Video> {

    boolean existsById(String id);

    @Query("SELECT v FROM Video v WHERE v.viewState != ?1")
    List<Video> findByNotState(String viewState);

    @Query("SELECT v FROM Video v WHERE v.viewState = ?1")
    List<Video> findByState(String viewState);

    @Query(value = "SELECT v FROM Video v WHERE v.viewState = ?1",
        countQuery = "SELECT count(v) FROM Video v WHERE v.viewState = ?1",
        nativeQuery = true)
    List<Video> findByState(String viewState, PageRequest pageable);

    @Transactional
    Long deleteById(String id);

}
