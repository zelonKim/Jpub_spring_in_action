//package tacos.data;
//
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
//import org.springframework.stereotype.Repository;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import tacos.Order;
//import tacos.Taco;
//
//
//@Repository
//public class JdbcOrderRepository implements OrderRepository {
//	
//	private SimpleJdbcInsert orderInserter;
//	private SimpleJdbcInsert orderTacoInserter;
//	
//	private ObjectMapper objectMapper;
//	
//	
//	@Autowired
//	public JdbcOrderRepository(JdbcTemplate jdbc) { // JdbcTemplate을 사용해서 SimpleJdbcInsert 인스턴스를 생성함.
//		this.orderInserter = new SimpleJdbcInsert(jdbc).withTableName("Taco_Order").usingGeneratedKeyColumns("id"); // Taco_Order 테이블을 지정함.  // ID 속성값은 데이터베이스가 생성해주는 것을 사용함.
//		this.orderTacoInserter = new SimpleJdbcInsert(jdbc).withTableName("Taco_Order_Tacos"); // Taco_Order_Tacos 테이블을 지정함.
//		
//		this.objectMapper = new ObjectMapper(); // Jackson ObjectMapper 인스턴스를 생성함.
//	}
//	
//	
//	
//	@Override
//	public Order save(Order order) {
//		order.setPlacedAt(new Date());
//		
//		long orderId = saveOrderDetails(order);
//		
//		order.setId(orderId);
//		
//		
//		List<Taco> tacos = order.getTacos();
//		
//		for(Taco taco : tacos) {
//			saveTacoToOrder(taco, orderId);
//		}
//		
//		return order;
//	}
//	
//	
//	
//	private long saveOrderDetails(Order order) {
//		@SuppressWarnings("unchecked")
//		Map<String, Object> values = objectMapper.convertValue(order, Map.class); // ObjectMapper.convertValue(변환 전 타입, 변환 후 타입)  // order 객체의 각 속성(컬럼)을 쉽게 Map으로 생성해줌.
//		
//		values.put("placedAt", order.getPlacedAt()); // Map.put("테이블의 컬럼명", 해당 컬럼에 추가될 값)
//		
//		
//		long orderId = orderInserter.executeAndReturnKey(values).longValue(); // 해당 Map 데이터를 지정된 테이블에 저장한 후, 생성한 ID를 반환함.
//		
//		return orderId;
//	}
//	
//	
//	
//	
//	private void saveTacoToOrder(Taco taco, long orderId) {
//		Map<String, Object> values = new HashMap<>();
//		
//		values.put("tacoOrder", orderId);
//		values.put("taco", taco.getId());
//		
//		orderTacoInserter.execute(values); // 해당 Map 데이터를 지정된 테이블에 저장함.
//	}
//	
//	
//}