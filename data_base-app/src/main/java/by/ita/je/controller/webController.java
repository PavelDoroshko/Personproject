package by.ita.je.controller;

import by.ita.je.dto.AnnouncementDto;
import by.ita.je.dto.SearchDto;
import by.ita.je.dto.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@AllArgsConstructor
@Controller
public class webController {
    private final RestTemplate restTemplate;
    private final String baseUrl = "http://localhost:8003/data_base-app/";


    @GetMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping(value = "/searchCriteria")
    public String findByCriteria(@ModelAttribute SearchDto searchDto, Model model){
        ResponseEntity<AnnouncementDto[]> responseEntity =
                restTemplate.postForEntity(baseUrl + "search/"+"criteria", searchDto, AnnouncementDto[].class);
        List<AnnouncementDto> list = Arrays.asList(Objects.requireNonNull(responseEntity.getBody()));
        model.addAttribute("announcements", list);
        return "findAll";
    }
    @GetMapping(value = "/findUser")
    public String findById(@RequestParam(value = "login", required = false) String login, Model model){
        ResponseEntity responseEntity = restTemplate.getForEntity(baseUrl + "user/"+"read?login="+ login, UserDto.class);
        model.addAttribute("userdLogin", responseEntity.getBody());
        return "findById";
    }

    @GetMapping(value = "/create")
    public String create(Model model) {
        model.addAttribute("userdLogin", new UserDto());
        return "formUserCreate";
    }
    @PostMapping(value = "/newUser")
    public String created(@ModelAttribute UserDto userDto, Model model) {
        UserDto response =
                restTemplate.postForObject(baseUrl + "user/"+"create", userDto,UserDto.class);
        model.addAttribute("userdLogin", response);
        return "FindById";
    }
    @GetMapping(value = "/findAllAnnouncement")
    public String findAllAnnouncementByUser(@PathVariable(value = "id") String id, Model model){
        ResponseEntity<AnnouncementDto[]> responseEntity = restTemplate
                .getForEntity(baseUrl + "/buisness"+"/readall/"+"id ", AnnouncementDto[].class);
        List<AnnouncementDto> list =  Arrays.asList(Objects.requireNonNull(responseEntity.getBody()));
        model.addAttribute("announcements", list);

        return "findAll";
    }

@ModelAttribute("userdLogin")
private UserDto userDto (){
    return new UserDto();
}
}

