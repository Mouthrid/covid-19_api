package com.mouthird.devcovid19api.dao.repositories;

import com.mouthird.devcovid19api.dao.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
 * News Repository, use QueryDSL
 */
@Repository
public interface NewsRepository extends
        JpaRepository<News, Long>, QuerydslPredicateExecutor<News> {
}
