package by.ita.je.controller;

import by.ita.je.service.InterfaseCreditCarService;
import by.ita.je.service.InterfaseUserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import by.ita.je.dto.UserDto;
import javassist.NotFoundException;
import by.ita.je.entity.User;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {
    public UserController(ObjectMapper objectMapper, InterfaseUserService interfaseUserService, InterfaseCreditCarService interfaseCreditCarService) {
        this.objectMapper = objectMapper;
        this.interfaseUserService = interfaseUserService;
        this.interfaseCreditCarService = interfaseCreditCarService;
    }

    private final ObjectMapper objectMapper;
    private final InterfaseUserService interfaseUserService;
    private final InterfaseCreditCarService interfaseCreditCarService;

    @ResponseBody
    @PostMapping("/create")
    public UserDto createUser(@RequestBody UserDto userDto) {
        User user = objectMapper.convertValue(userDto, User.class);
        return objectMapper.convertValue(interfaseUserService.create(user), UserDto.class);
    }
    @GetMapping("/read/all")
    public List<UserDto> readAll() {
        return interfaseUserService.readAll().stream()
                .map(user -> objectMapper.convertValue(user, UserDto.class))
                .collect(Collectors.toList());
    }
    @GetMapping("/read/one")
    public UserDto readOne(@RequestParam("id") long id) throws NotFoundException {
        return objectMapper.convertValue(interfaseUserService.readOne(id), UserDto.class);
    }



    @GetMapping("/read")
    public UserDto readOneLogin(@RequestParam("login") String login) throws NotFoundException {
        return objectMapper.convertValue(interfaseUserService.readOneByLogin(login), UserDto.class);
    }

}
