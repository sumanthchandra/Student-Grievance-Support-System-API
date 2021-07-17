package api.studentgrievancesupport.com.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import api.studentgrievancesupport.com.beans.User;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
}