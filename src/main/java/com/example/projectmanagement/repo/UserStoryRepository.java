package com.example.projectmanagement.repo;

import com.example.projectmanagement.entity.UserStoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserStoryRepository extends JpaRepository<UserStoryEntity, Integer> {}
