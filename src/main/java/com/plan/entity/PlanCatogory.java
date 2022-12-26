package com.plan.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data

public class PlanCatogory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer catogoryId;
	private String catogoryName;

	private String activeSw;

	private String createdBy;
	private String updatedBy;
	@Column(name = "CREATED_DATE", updatable = false)
	@CreationTimestamp
	private LocalDate createDate;
	@UpdateTimestamp
	@Column(name = "UPDATE_DATE", insertable = false)
	private LocalDate updateDate;
}
