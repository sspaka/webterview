package com.ssafy.webterview.repository;

import com.ssafy.webterview.entity.Rater;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RaterRepository extends JpaRepository<Rater, Integer> {
}