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
import api.studentgrievancesupport.com.beans.GrievanceMember;
import api.studentgrievancesupport.com.beans.User;
import api.studentgrievancesupport.com.model.Role;
import api.studentgrievancesupport.com.repository.GrievanceMemberRepository;
import api.studentgrievancesupport.com.repository.UserRepository;
import org.springframework.http.HttpStatus;

//Creating the REST controller
@RestController
public class GrievanceMemberController {

	@Autowired
	private GrievanceMemberRepository grievanceMemberRepository;

	@Autowired
	private UserRepository userRepository;

	@GetMapping(path = "/grievance-members") // READ
	public List<GrievanceMember> getGrievanceMembers(@RequestParam(required = false) String email) {
		List<GrievanceMember> grievanceMembers = new ArrayList<>();
		if (email != null && !email.isBlank()) {
			grievanceMembers = grievanceMemberRepository.findById(email).isPresent()
					? Collections.singletonList(grievanceMemberRepository.findById(email).get())
					: Collections.emptyList();
		} else {
			grievanceMembers = (List<GrievanceMember>) grievanceMemberRepository.findAll();
		}
		return grievanceMembers;
	}

	@PostMapping("/grievance-members") // CREATE
	@ResponseStatus(HttpStatus.CREATED)
	public GrievanceMember createGrievanceMember(@RequestBody GrievanceMember grievanceMember) {
		User user = new User(grievanceMember.getEmail(), grievanceMember.getPassword(), Role.MEMBER);
		userRepository.save(user);
		return grievanceMemberRepository.save(grievanceMember);
	}

	@DeleteMapping("/grievance-members/{email}") // DELETE
	public void deleteGrievanceMember(@PathVariable String email) {
		grievanceMemberRepository.deleteById(email);
		userRepository.deleteById(email);
	}

	@PutMapping("/grievance-members/{email}") // UPDATE
	GrievanceMember updateGrievanceMember(@RequestBody GrievanceMember newGrievanceMember, @PathVariable String email) {
		return grievanceMemberRepository.findById(email).map(grievanceMember -> {
			/* TODO */
			return grievanceMemberRepository.save(grievanceMember);
		}).orElseGet(() -> {
			newGrievanceMember.setEmail(email);
			return grievanceMemberRepository.save(newGrievanceMember);
		});
	}
}
