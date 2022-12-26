package com.plan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.plan.entity.PlanCatogory;

@Repository
public interface PlanCatogoryRepo extends JpaRepository<PlanCatogory, Integer>{

}
