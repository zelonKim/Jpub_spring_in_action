//package tacos.data;


//
//import tacos.Order;
//
//public interface OrderRepository {
//	Order save(Order order);
//}


///////////////

package tacos.data;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import tacos.Order;
import tacos.User;

public interface OrderRepository extends CrudRepository<Order, Long>{
	List<Order> findByUserOrderByPlacedAtDesc(User user, Pageable pageable); // 가장 최근 주문부터 오래된 주문의 순서로 내림차순 정렬함.
	
	
//	List<Order> findBydDeliveryZip(String deliberyZip);
//	
//	List<Order> readOrdersByDeliveryZipAndPlacedAtBetween(String deliveryZip, Date startDate, Date endDate);
//	
//	List<Order> findByDeliveryToAndDeliveryCityAllIgnoresCase(String deliveryTo, String deliveryCity);
//	
//	List<Order> findByDeliveryCityOrderByDeliveryTo(String city);
//	
//	@Query("Order o where o.deliveryCity='Seattle'")
//	List<Order> readOrdersDeliveredInSeattle();
}


