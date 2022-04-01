package foodDeliveryApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import foodDeliveryApp.models.DAOUser;


public interface UserRepository extends JpaRepository<DAOUser, Integer> {
	
	DAOUser findByUsername(String username);
	
}
