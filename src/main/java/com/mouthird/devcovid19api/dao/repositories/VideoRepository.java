package com.mouthird.devcovid19api.dao.repositories;

import com.mouthird.devcovid19api.dao.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Video Repository, use QueryDSL
 */
@Repository
public interface VideoRepository extends
        JpaRepository<Video, Long>, QuerydslPredicateExecutor<Video> {

    boolean existsById(String id);

    @Transactional
    Long deleteById(String id);

}
