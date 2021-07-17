package api.studentgrievancesupport.com.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import api.studentgrievancesupport.com.beans.Student;

@Repository
public interface StudentRepository extends CrudRepository<Student, String> {
}
