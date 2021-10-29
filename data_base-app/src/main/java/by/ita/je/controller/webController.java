package by.ita.je.controller;

import by.ita.je.dto.AnnouncementDto;
import by.ita.je.dto.SearchDto;
import by.ita.je.dto.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

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
        return "findById";
    }
    @GetMapping(value = "/findAllAnnouncement")
    public String findAllAnnouncementByUser(@RequestParam(value = "id") String id, Model model){

        List<AnnouncementDto> list  =  Arrays.asList(restTemplate
                .getForObject(baseUrl + "/buisness"+"/readall/"+id , AnnouncementDto[].class));
       // List<AnnouncementDto> list =  Arrays.asList(Objects.requireNonNull(responseEntity.getBody()));
        model.addAttribute("announcements", list);

        return "findAll";
    }
    @GetMapping(value = "/update")
    public String update(@RequestParam(value = "id", required = false) Long id, Model model){

        AnnouncementDto announcementDto = restTemplate.getForObject(baseUrl + "buisness/"+"read/" +"one?id="+ id, AnnouncementDto.class);
        model.addAttribute("announcementd",  announcementDto);
        return "formUpdate";
    }
    @PostMapping(value = "/updatedAnnouncement")
    public String updated(@ModelAttribute  AnnouncementDto announcementDto, Model model){
        model.addAttribute("announcementd", announcementDto);
          restTemplate.put(baseUrl + "buisness"+"/update/" + announcementDto.getId(), announcementDto,  AnnouncementDto.class);

        return "findById";
    }


    @ModelAttribute("userdLogin")
private UserDto userDto (){
    return new UserDto();
}
}

