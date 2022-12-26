package com.plan.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.plan.constants.AppConstant;
import com.plan.entity.Plan;
import com.plan.props.AppProperties;
import com.plan.service.PlanService;

@RestController
public class PlanRestController {

//	@Autowired
//	private PlanService planService;
//	
//	@Autowired
//	private AppProperties appProperties;
	
	private Map<String, String> messages;
	private PlanService planService;
	
	
	public PlanRestController( PlanService planService, AppProperties appProperties) {
		this.planService = planService;
		this.messages = appProperties.getMesseges();
		System.out.println(this.messages);
	}

	@PostMapping("/categories")
	public ResponseEntity<Map<Integer, String>> planCategories() {

		Map<Integer, String> categories = planService.getPlanCategories();

		return new ResponseEntity<>(categories, HttpStatus.OK);
	}

	@PostMapping("/plans")
	public ResponseEntity<String> savePlan(@RequestBody Plan plan) {
		String responseMsg = AppConstant.EMPTY_STR;

		boolean isSaved = planService.savePlan(plan);
		//Map<String, String> messages= appProperties.getMesseges();

		if (isSaved) {
			responseMsg = messages.get(AppConstant.PLAN_SAVE_SUCC);
		} else {
			responseMsg = messages.get(AppConstant.PLAN_SAVE_FAIL);
		}
		return new ResponseEntity<String>(HttpStatus.CREATED);
	}

	@GetMapping("/plans")
	public ResponseEntity<List<Plan>> plans() {
		List<Plan> planList = planService.getAllPlans();
		return new ResponseEntity<List<Plan>>(planList, HttpStatus.OK);
	}

	@PutMapping("/plan")
	public ResponseEntity<String> updatePlan(@RequestBody Plan plan) {
		boolean isUpdated = planService.updatePlan(plan);
		String msg = AppConstant.EMPTY_STR;
		//Map<String, String> messages = appProperties.getMesseges();
		if (isUpdated) {
			msg = messages.get(AppConstant.PLAN_UPDATE_SUCC);
		} else {
			msg = messages.get(AppConstant.PLAN_UPDATE_FAIL);
		}
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}

	@GetMapping("/plan/{planId}")
	public ResponseEntity<Plan> editPlan(@PathVariable Integer planId) {
		Plan plan = planService.getPlanById(planId);

		return new ResponseEntity<Plan>(plan, HttpStatus.OK);
	}

	@DeleteMapping("/plan/{planId}")
	public ResponseEntity<String> deletePlan(@PathVariable Integer planId) {
		boolean isDeleted = planService.deletePlanById(planId);
		String msg = AppConstant.EMPTY_STR;
		//Map<String, String> messages = appProperties.getMesseges();
		if (isDeleted) {
			msg = messages.get(AppConstant.PLAN_DELETE_SUCC);
		} else {
			msg = messages.get(AppConstant.PLAN_DELETE_FAIL);
		}
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}

	@PutMapping("/status-change/{planId}/{status}")
	public ResponseEntity<String> statusChange(@PathVariable Integer planId, String status) {
		String msg = AppConstant.EMPTY_STR;
		//Map<String, String> messages = appProperties.getMesseges();
		boolean isStatusChanged = planService.planStatusChange(planId, status);
		if (isStatusChanged) {
			msg = messages.get(AppConstant.PLAN_STATUS_CHANGE);
		} else {
			msg = messages.get(AppConstant.PLAN_STATUS_CHANGE_FAIL);
		}
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}
}
