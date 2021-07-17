package api.studentgrievancesupport.com.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import api.studentgrievancesupport.com.beans.Ticket;
import api.studentgrievancesupport.com.repository.TicketRepository;
import org.springframework.http.HttpStatus;

//Creating the REST controller
@RestController
public class TicketController {

	@Autowired
	private TicketRepository ticketRepository;

	@GetMapping(path = "/tickets")
	public List<Ticket> getTickets(@RequestParam(required = false) String id) {
		List<Ticket> tickets = new ArrayList<>();
		if (id != null && !id.isBlank()) {
			tickets = ticketRepository.findById(Long.parseLong(id)).isPresent()
					? Collections.singletonList(ticketRepository.findById(Long.parseLong(id)).get())
					: Collections.emptyList();
		} else {
			tickets = (List<Ticket>) ticketRepository.findAll();
		}
		return tickets;
	}

	@PostMapping("/ticket")
	@ResponseStatus(HttpStatus.CREATED)
	public Ticket addStudent(@RequestBody Ticket ticket) {
		return ticketRepository.save(ticket);
	}

	@DeleteMapping("/ticket/{ticketId}")
	public void deleteTicket(@PathVariable Long ticketId) {
		ticketRepository.deleteById(ticketId);
	}

	@PutMapping("/ticket/{ticketId}")
	Ticket updateTicket(@RequestBody Ticket newTicket, @PathVariable Long ticketId) {

		return ticketRepository.findById(ticketId).map(ticket -> {
			ticket.setLevel(newTicket.getLevel());
			ticket.setKeyword(newTicket.getKeyword());
			ticket.setSubject(newTicket.getSubject());
			ticket.setUrgency(newTicket.getUrgency());
			ticket.setDescription(newTicket.getDescription());
			ticket.setAnonymity(newTicket.getAnonymity());
			return ticketRepository.save(ticket);
		}).orElseGet(() -> {
			newTicket.setTicketId(ticketId);
			return ticketRepository.save(newTicket);
		});
	}
}
