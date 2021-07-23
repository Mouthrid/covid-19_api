package com.mouthird.devcovid19api.dao.repositories;

import com.mouthird.devcovid19api.dao.entity.Channel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Channel Repository, use QueryDSL
 */
@Repository
public interface ChannelRepository extends
        JpaRepository<Channel, Long>, QuerydslPredicateExecutor<Channel> {

    boolean existsById(String id);

    @Transactional
    Long deleteById(String id);

}
