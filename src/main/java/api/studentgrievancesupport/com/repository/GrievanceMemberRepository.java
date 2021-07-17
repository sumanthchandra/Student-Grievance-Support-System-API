package api.studentgrievancesupport.com.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import api.studentgrievancesupport.com.beans.GrievanceMember;

@Repository
public interface GrievanceMemberRepository extends CrudRepository<GrievanceMember, String> {
}
