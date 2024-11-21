package com.spring.mvc.entity;


import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name="Products")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
private long proId;
	@Column(name="proNam")
private String proName;
private Double proPrice;
private String proBrand;
private String proDescription;
private Double proDisPrice;
private String proCategory;
private LocalDate createdAt;
private String createdBy;


}


