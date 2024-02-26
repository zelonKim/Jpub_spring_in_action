package tacos.data;

import org.springframework.data.repository.CrudRepository;
import tacos.User;

public interface UserRepository extends CrudRepository<User, Long> { // 커스텀 사용자 명세 서비스를 위한 레포지토리 인터페이스
	User findByUsername(String username);
	
}
