package by.ita.je.controller;

import by.ita.je.service.api.InterfaseUserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import by.ita.je.dto.UserDto;
import javassist.NotFoundException;
import by.ita.je.module.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {
    public UserController(ObjectMapper objectMapper, InterfaseUserService interfaseUserService) {
        this.objectMapper = objectMapper;
        this.interfaseUserService = interfaseUserService;
    }

    private final ObjectMapper objectMapper;
    private final InterfaseUserService interfaseUserService;

    @ResponseBody
    @PostMapping("/create")
    public UserDto createUser(@RequestBody UserDto userDto) {
        User user = objectMapper.convertValue(userDto, User.class);
        return objectMapper.convertValue(interfaseUserService.create(user), UserDto.class);
    }
    @GetMapping("/read/one")
    public UserDto readOne(@RequestParam("id") long id) throws NotFoundException {
        return objectMapper.convertValue(interfaseUserService.readOne(id), UserDto.class);
    }

    @DeleteMapping(value = "/delete")
    public void deleteOne(@RequestParam("id") long id) {
        interfaseUserService.deleteById(id);
    }

    @GetMapping("/read/all")
    public List<UserDto> readAll() {
        return interfaseUserService.readAll().stream()
                .map(user -> objectMapper.convertValue(user, UserDto.class))
                .collect(Collectors.toList());
    }
}
