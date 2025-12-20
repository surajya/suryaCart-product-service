package com.suryacart.product.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int categoryId;

	private String categoryTitle;
	@Column(length = 1500)
	private String categoryDiscription;
	@OneToMany(mappedBy = "category")
	private List<Product> product = new ArrayList<>();

}
