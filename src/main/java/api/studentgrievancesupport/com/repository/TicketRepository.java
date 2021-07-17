package api.studentgrievancesupport.com.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import api.studentgrievancesupport.com.beans.Ticket;

@Repository
public interface TicketRepository extends CrudRepository<Ticket, Long> {
}
