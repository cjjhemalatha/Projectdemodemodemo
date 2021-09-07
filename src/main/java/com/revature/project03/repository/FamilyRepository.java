package com.revature.project03.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.project03.entities.Family;

@Repository
public interface FamilyRepository extends JpaRepository<Family, Integer>{

}
