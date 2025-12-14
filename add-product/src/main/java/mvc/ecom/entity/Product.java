package mvc.ecom.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int pId;
	private String pTitle;
	@Column(length = 3000)
	private String pDesc;
	@Column(length = 3000)
	private String pPhoto;
	private int pPrice;
	private int pDiscount;
	private int pQuantity;
	@ManyToOne
	private Category category;

}
