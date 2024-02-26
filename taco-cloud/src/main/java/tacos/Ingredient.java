
//package tacos;

//import javax.persistence.Entity;
//import javax.persistence.Id;
//
//import lombok.AccessLevel;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import lombok.RequiredArgsConstructor;
//
//
//@Data 
//@RequiredArgsConstructor 
//public class Ingredient {
//	
//	private final String id;
//	private final String name;
//	private final Type type;
//	
//	public static enum Type {
//		WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
//	}
//}



///////////////////////////////



package tacos;

import javax.persistence.Entity;

import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;


@Data // 인자 있는 생성자를 추가함.
@RequiredArgsConstructor // 인자 없는 생성자와 더불어 인자 있는 생성자를 가질 수 있도록 해줌.
@NoArgsConstructor(access=AccessLevel.PRIVATE, force=true) // 인자 없는 생성자를 추가함. // JPA에서는 엔티티가 인자 없는 생성자를 가져야 함.
@Entity // 해당 클래스를 JPA 엔티티로 선언함.
public class Ingredient {
	
	@Id // 해당 속성이 데이터베이스의 엔티티를 고유하게 식별하도록 해줌.
	private final String id;
	private final String name;
	private final Type type;
	
	public static enum Type {
		WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
	}
}