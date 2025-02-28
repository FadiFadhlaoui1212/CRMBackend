package com.example.jwtAuth.repository;

import com.example.jwtAuth.model.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepository extends JpaRepository<Activity, Long> {

}
