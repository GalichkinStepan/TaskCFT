package com.cft.FirstTask.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cft.FirstTask.model.Tutorial;

public interface TutorialRepository extends JpaRepository<Tutorial, Long> {

}
