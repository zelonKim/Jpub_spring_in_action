//package tacos;


//
//import javax.validation.constraints.Digits;
//import javax.validation.constraints.Pattern;
//import javax.validation.constraints.NotBlank;
//import org.hibernate.validator.constraints.CreditCardNumber;
//
//import lombok.Data;
//import java.util.Date;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Data
//public class Order {
//	private Long id;
//	private Date placedAt;
//	
//	@NotBlank(message="Name is required")
//	private String deliveryName;
//	
//	@NotBlank(message="Street is required")
//	private String deliveryStreet;
//	
//	@NotBlank(message="City is required")
//	private String deliveryCity;
//	
//	@NotBlank(message="State is required")
//	private String deliveryState;
//	
//	@NotBlank(message="Zip code is required")
//	private String deliveryZip;
//	
//	@CreditCardNumber(message="Not a valid credit card number")
//	private String ccNumber;
//	
//	@Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$",
//			message="Must be formatted MM/YY")
//	private String ccExpiration;
//	
//	@Digits(integer=3, fraction=0, message="Invalid CVV")
//	private String ccCVV;
//	
//	private List<Taco> tacos = new ArrayList<>();
//
//	public void addDesign(Taco design) {
//		this.tacos.add(design);
//	}
//}


///////////////////////


package tacos;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.CreditCardNumber;

import lombok.Data;


@Data
@Entity
@Table(name="Taco_Order") // Order 엔티티가 데이터베이스의 Taco_Order 테이블에 저장되어야 함을 나타냄.
public class Order implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private Date placedAt;
	
	@ManyToOne // 한 명의 사용자(user)는 여러 주문(order)을 가질 수 있음.  // 한 건의 주문(order)이 한 명의 사용자에(user)게 속함을 나타냄. 
	private User user;
	
	@NotBlank(message="Name is required")
	private String deliveryName;
	
	@NotBlank(message="Street is required")
	private String deliveryStreet;
	
	@NotBlank(message="City is required")
	private String deliveryCity;
	
	@NotBlank(message="State is required")
	private String deliveryState;
	
	@NotBlank(message="Zip code is required")
	private String deliveryZip;
	
	@CreditCardNumber(message="Not a valid credit card number")
	private String ccNumber;
	
	@Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$",
			message="Must be formatted MM/YY")
	private String ccExpiration;
	
	@Digits(integer=3, fraction=0, message="Invalid CVV")
	private String ccCVV;
	
	
	@ManyToMany(targetEntity=Taco.class)
	private List<Taco> tacos = new ArrayList<>();
	
	public void addDesign(Taco design) {
		this.tacos.add(design);
	}
	
	@PrePersist
	void placedAt() {
		this.placedAt = new Date();
	}
}