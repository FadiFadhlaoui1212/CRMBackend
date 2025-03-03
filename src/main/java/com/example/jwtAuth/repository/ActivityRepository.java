package com.example.jwtAuth.repository;

import com.example.jwtAuth.dto.ActivityReducedDetails;
import com.example.jwtAuth.model.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface ActivityRepository extends JpaRepository<Activity, Long> {

    @Query("SELECT a FROM Activity a WHERE a.id = (SELECT MAX(a2.id) FROM Activity a2)")
    Activity findActivityWithMaxId();

    @Modifying
    @Transactional
    @Query("DELETE FROM Activity a WHERE a.id IN :ids")
    void deleteActivitiesByIds(@Param("ids") List<Long> ids);

    @Query("SELECT DISTINCT new com.example.jwtAuth.dto.ActivityReducedDetails(" +
            "a.id, a.date, a.type, a.subject, a.note) " +  // Remove participants from constructor
            "FROM Activity a")
    List<ActivityReducedDetails> findAllActivities();



}
