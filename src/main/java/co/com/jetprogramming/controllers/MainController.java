package co.com.jetprogramming.controllers;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import co.com.jetprogramming.mappers.UserLogMapper;
import co.com.jetprogramming.model.User;
import co.com.jetprogramming.model.UserLog;
import co.com.jetprogramming.model.pojos.UserLogDto;
import co.com.jetprogramming.repositories.UserLogRepository;
import co.com.jetprogramming.repositories.UserRepository;
import co.com.jetprogramming.utils.StreamUtils;

@Controller
@RequestMapping(path = "/demo")
public class MainController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserLogRepository userLogRepository;

	@Autowired
	private UserLogMapper logMapper;

	@GetMapping(path = "/add")
	public @ResponseBody String addNewUser(@RequestParam String name, @RequestParam String email) {
		User n = new User();
		n.setName(name);
		n.setEmail(email);
		userRepository.save(n);
		return "Saved";
	}

	@PostMapping(path = "/log/add")
	public @ResponseBody String addNewUserLog(@RequestBody UserLogDto dto) {
		UserLog e = logMapper.mapEntity(dto);
		userLogRepository.save(e);
		return "Saved";
	}

	@GetMapping(path = "/all")
	public @ResponseBody Iterable<User> getAllUsers() {
		return userRepository.findAll();
	}

	@GetMapping(path = "/log/all")
	public @ResponseBody List<UserLogDto> getAllUserLogs() {
		Iterable<UserLog> all = userLogRepository.findAll();
		Stream<UserLog> stream = StreamUtils.stream(all);
		return stream.map(e -> logMapper.mapDto(e)).collect(Collectors.toList());
	}
}