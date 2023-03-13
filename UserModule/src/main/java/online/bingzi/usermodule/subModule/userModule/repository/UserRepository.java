package online.bingzi.usermodule.subModule.userModule.repository;

import online.bingzi.usermodule.subModule.userModule.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}