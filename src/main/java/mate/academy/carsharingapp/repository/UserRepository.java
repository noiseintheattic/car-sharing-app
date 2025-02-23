package mate.academy.carsharingapp.repository;

import java.util.Optional;
import mate.academy.carsharingapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u LEFT JOIN FETCH u.userRoles WHERE u.email =:email")
    Optional<User> findUserByEmail(@Param("email") String email);
}
