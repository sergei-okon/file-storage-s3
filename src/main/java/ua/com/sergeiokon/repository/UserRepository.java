package ua.com.sergeiokon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.sergeiokon.repository.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
