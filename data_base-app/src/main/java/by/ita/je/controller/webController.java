package by.ita.je.controller;

import by.ita.je.dto.AnnouncementDto;
import by.ita.je.dto.CreditCartDto;
import by.ita.je.dto.SearchDto;
import by.ita.je.dto.UserDto;
import by.ita.je.module.Announcement;
import by.ita.je.module.CreditCart;
import by.ita.je.module.User;
import by.ita.je.service.api.InterfaceAnnouncement;
import by.ita.je.service.api.InterfaceBuisness;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
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
    private final InterfaceBuisness interfaceBuisness;
    private final InterfaceAnnouncement interfaceAnnouncement;
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
        AnnouncementDto response =restTemplate.postForObject(baseUrl + "buisness"+"/update/" + announcementDto.getId(), announcementDto,  AnnouncementDto.class);
        //model.addAttribute("userdLogin", response.getUser());
        return "findAll";
    }
     @GetMapping(value = "/{userId}/createAnnouncement")
   public String createAnnouncement(@PathVariable("userId") long userId, Model model) {
        model.addAttribute("announcementd", new AnnouncementDto());
                model.addAttribute("userId", userId);
        return "formForCreateAnnouncement";
    }
    @PostMapping(value = "/{userId}/newAnnouncement")
    public String createdAnnouncement(@PathVariable("userId") long userId, @ModelAttribute AnnouncementDto announcementDto, Model model) {
        AnnouncementDto response =
                restTemplate.postForObject(baseUrl + "buisness/"+"/create?id=" + userId, announcementDto,AnnouncementDto.class);
        model.addAttribute("announcementd", response);
        model.addAttribute("userId", userId);
        return "formAnnouncement";
    }
    @GetMapping(value = "/findUserById")
    public String findUserById(@RequestParam(value = "id", required = false) String id, Model model){
        ResponseEntity responseEntity = restTemplate.getForEntity(baseUrl + "user/"+"read/"+"one?id="+id, UserDto.class);
        model.addAttribute("userdLogin", responseEntity.getBody());
        return "findById";
    }
    @SneakyThrows
    @PostMapping(value = "{userId}/createCreditCart")
    public String createCreditCart(@PathVariable(value = "userId", required = false) long userId, Model model){
        CreditCart response =interfaceBuisness.createCreditCart(userId);
        model.addAttribute("creditCart", response);
        model.addAttribute("userId", userId);
        return "creditCart";
    }
    @SneakyThrows
    @GetMapping(value = "{userId}/addBalance")
    public String addBalance(@PathVariable(value = "userId", required = false) long userId, Model model){
       // ResponseEntity responseEntity = restTemplate.getForEntity(baseUrl + "user/"+"read/"+"one?id="+id, UserDto.class);
       User user = interfaceBuisness.addBalance(userId);
        model.addAttribute("userdLogin", user);
        return "findById";
    }
    @SneakyThrows
    @GetMapping(value = "{userId}/findAnnouncementById")
    public String findAnnouncement(@PathVariable("userId") long userId, @ModelAttribute AnnouncementDto announcementDto, Model model) {
        Announcement response = interfaceAnnouncement.readOne(announcementDto.getId());
        model.addAttribute("announcementd", response);
        model.addAttribute("userId", userId);
        return "formAnnouncement";
    }
    @ModelAttribute("userdLogin")
private UserDto userDto (){
    return new UserDto();
}
}

