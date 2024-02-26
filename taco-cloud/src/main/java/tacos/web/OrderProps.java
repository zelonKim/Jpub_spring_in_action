package tacos.web;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import org.springframework.validation.annotation.Validated;

@Component
@ConfigurationProperties(prefix="taco.orders") // taco.orders.pageSize를 통해 구성 속성값을 지정할 수 있음.
@Data
@Validated
public class OrderProps { // 구성 속성 홀더 클래스
	
	@Min(value=5, message="must be between 5 and 25")
	@Max(value=25, message="must be between 5 and 25")
	private int pageSize = 20; // 기본값
}
