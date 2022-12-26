package com.plan.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plan.entity.Plan;
import com.plan.entity.PlanCatogory;
import com.plan.repository.PlanCatogoryRepo;
import com.plan.repository.PlanRepository;

@Service
public class PlanServiceImpl implements PlanService {

	@Autowired
	private PlanRepository planRepository;

	@Autowired
	private PlanCatogoryRepo catogoryRepo;

	@Override
	public Map<Integer, String> getPlanCategories() {
		// TODO Auto-generated method stub
		List<PlanCatogory> catogories = catogoryRepo.findAll();
		Map<Integer, String> categoryMap = new HashMap<>();

		catogories.forEach(category -> {
			categoryMap.put(category.getCatogoryId(), category.getCatogoryName());
		});

		return categoryMap;
	}

	@Override
	public boolean savePlan(Plan plan) {
		Plan saved = planRepository.save(plan);
//		if(saved.getPlanId()!= null) {
//			return true;
//		}else {
//			
//			return false;
//		}
		return saved.getPlanId()!=null ? true :false;

//		return saved.getPlanId() != null;
	}

	@Override
	public List<Plan> getAllPlans() {
		// TODO Auto-generated method stub
		return planRepository.findAll();
	}

	@Override
	public Plan getPlanById(Integer planId) {
		// TODO Auto-generated method stub
		Optional<Plan> findById = planRepository.findById(planId);

		if (findById.isPresent()) {
			return findById.get();
		} else {
			return null;
		}
	}

	@Override
	public boolean updatePlan(Plan plan) {
		Plan updateplan = planRepository.save(plan);
		return plan.getPlanId() != null;
	}

	@Override
	public boolean deletePlanById(Integer planId) {
		boolean status = false;
		try {

			planRepository.findById(planId);
			status = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean planStatusChange(Integer planId, String status) {
		Optional<Plan> findById = planRepository.findById(planId);
		if (findById.isPresent()) {
			Plan plan = findById.get();
			plan.setActiveSwitch(status);
			planRepository.save(plan);
			return true;
		}
		return false;
	}

}
