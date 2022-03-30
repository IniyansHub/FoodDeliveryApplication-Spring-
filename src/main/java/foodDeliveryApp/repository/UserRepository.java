package foodDeliveryApp.repository;

//import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import foodDeliveryApp.models.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	User findByEmail(String email);
	
	User findByPassword(String password);
}
