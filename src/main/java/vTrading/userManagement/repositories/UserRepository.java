package vTrading.userManagement.repositories;

import org.springframework.data.repository.CrudRepository;
import vTrading.userManagement.models.User;

public interface UserRepository extends CrudRepository<User, Long> {
    @Override
    long count();
}
