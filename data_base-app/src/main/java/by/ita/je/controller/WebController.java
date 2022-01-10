package by.ita.je.controller;

import by.ita.je.dto.AnnouncementDto;
import by.ita.je.dto.BestAnnouncementDto;
import by.ita.je.dto.SearchDto;
import by.ita.je.dto.UserDto;
import by.ita.je.entity.Announcement;
import by.ita.je.entity.BestAnnouncement;
import by.ita.je.entity.CreditCart;
import by.ita.je.entity.User;
import by.ita.je.service.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@AllArgsConstructor
@Controller
public class WebController {
    private final ObjectMapper objectMapper;
    private final String baseUrl = "http://localhost:8003/data_base-app/";
    private final InterfaceBuisness interfaceBuisness;
    private final InterfaceAnnouncement interfaceAnnouncement;
    private final InterfaceBestAnnouncementService interfaceBestAnnouncementService;
    private final InterfaseUserService interfaceuserService ;
    private final InterfaceSearchService interfaceSearchService;
    @GetMapping("/")
    public String home() {
        return "home";
    }

   @GetMapping(value = "/searchCriteria")
    public String findByCriteria(@ModelAttribute SearchDto searchDto, Model model) {

        List<Announcement> announcementList = interfaceSearchService.findCriteria(searchDto.getNameCar(),
                searchDto.getModelCar(),searchDto.getPrice(),searchDto.getTypeEngine(),searchDto.getYearOfIssue(),searchDto.getMilage(),
                searchDto.getVolumeEngine(),searchDto.getTransmission(),searchDto.getLocation(),searchDto.getExchange(),searchDto.getCustom());
       List<AnnouncementDto> list= announcementList.stream()
               .map(announcement -> objectMapper.convertValue(announcement, AnnouncementDto.class))
               .collect(Collectors.toList());
        model.addAttribute("announcements", list);
        return "findAll";
    }

    @GetMapping(value = "/findUser")
    public String findById(@RequestParam(value = "login", required = false) String login, @RequestParam(value = "pasword", required = false) int pasword, Model model) {
       // User user = interfaceBuisness.findUserByLoginPasword(login, pasword);
        UserDto  userDto = objectMapper.convertValue(interfaceBuisness.findUserByLoginPasword(login, pasword), UserDto.class);
        model.addAttribute("userdLogin", userDto);
        return "findById";
    }

    @GetMapping(value = "/create")
    public String create(Model model) {
        model.addAttribute("userdLogin", new UserDto());
        return "formUserCreate";
    }

    @PostMapping(value = "/newUser")
    public String created(@ModelAttribute UserDto userDto, Model model) {
        User user = interfaceuserService.create(objectMapper.convertValue(userDto, User.class));
      UserDto response = objectMapper.convertValue(user, UserDto.class);
        model.addAttribute("userdLogin", response);
        return "findById";
    }

    @SneakyThrows
    @GetMapping(value = "/findAllAnnouncement")
    public String findAllAnnouncementByUser(@RequestParam(value = "id") String id, Model model) {

        List<Announcement> listAnnouncement = interfaceBuisness.readAllAnnoucement(Long.valueOf(id));
List<AnnouncementDto> listAnnouncementDto =listAnnouncement.stream()
        .map(announcement -> objectMapper.convertValue(announcement, AnnouncementDto.class))
        .collect(Collectors.toList());
        model.addAttribute("announcements", listAnnouncementDto);
        return "findAll";
    }

    @GetMapping(value = "/{userId}/update")
    public String update(@PathVariable("userId") long userId, Model model) {
        model.addAttribute("announcementd", new Announcement());
        model.addAttribute("userId", userId);
        return "formUpdate";
    }

    @SneakyThrows
    @PostMapping(value = "/{userId}/updatedAnnouncement")
    public String updatedAnnouncement(@PathVariable("userId") long userId, @ModelAttribute Announcement announcement, Model model) {
        Announcement response = interfaceAnnouncement.update(announcement.getId(), announcement);
        model.addAttribute("announcementd", response);
        model.addAttribute("userId", userId);
        return "formAnnouncement";
    }

    @GetMapping(value = "/{userId}/createAnnouncement")
    public String createAnnouncement(@PathVariable("userId") long userId, Model model) {
        model.addAttribute("announcementd", new AnnouncementDto());
        model.addAttribute("userId", userId);
        return "formForCreateAnnouncement";
    }

    @SneakyThrows
    @PostMapping(value = "/{userId}/newAnnouncement")
    public String createdAnnouncement(@PathVariable("userId") long userId, @ModelAttribute AnnouncementDto announcementDto, Model model) {

       Announcement announcement = interfaceBuisness.createAnnouncement(userId,objectMapper.convertValue(announcementDto, Announcement.class));
        AnnouncementDto response = objectMapper.convertValue(announcement, AnnouncementDto.class);
        model.addAttribute("announcementd", response);
        model.addAttribute("userId", userId);
        return "formAnnouncement";
    }

    @SneakyThrows
    @GetMapping(value = "/findUserById")
    public String findUserById(@RequestParam(value = "id", required = false) long id, Model model) {

        User user = interfaceuserService.readOne(id);
        UserDto response = objectMapper.convertValue(user,UserDto.class);
        model.addAttribute("userdLogin", response);
        return "findById";
    }

    @SneakyThrows
    @PostMapping(value = "{userId}/createCreditCart")
    public String createCreditCart(@PathVariable(value = "userId") long userId, Model model) {
        CreditCart response = interfaceBuisness.createCreditCart(userId);
        model.addAttribute("creditCart", response);
        model.addAttribute("userId", userId);
        return "creditCart";
    }

    @SneakyThrows
    @GetMapping(value = "{userId}/addBalance")
    public String addBalance(@PathVariable(value = "userId", required = false) long userId, Model model) {
        User user = interfaceBuisness.addBalance(userId);
        model.addAttribute("userdLogin", user);
        return "findById";
    }

    @SneakyThrows
    @GetMapping(value = "/{userId}/findAnnouncementById")
    public String findAnnouncement(@PathVariable("userId") String userId, @ModelAttribute AnnouncementDto announcementDto, Model model) {
        model.addAttribute("announcementd", new AnnouncementDto());
        model.addAttribute("userId", userId);
        return "formForFindAnnouncement";
    }
    @SneakyThrows
    @GetMapping(value = "/{userId}/newFindAnnouncement")
    public String findedAnnouncement(@PathVariable("userId") long userId, @ModelAttribute AnnouncementDto announcementDto, Model model) {
        Announcement announcement = interfaceAnnouncement.readOneWithUser(announcementDto.getId(),userId);
        AnnouncementDto response =objectMapper.convertValue(announcement , AnnouncementDto.class);
        model.addAttribute("announcementd", response);
        model.addAttribute("userId", userId);
        return "formAnnouncement";
    }

    @SneakyThrows
    @GetMapping(value = "/{userId}/getUpAnnouncement")
    public String getUpAnnouncement(@PathVariable("userId") long userId, Model model) {
        model.addAttribute("announcementd", new AnnouncementDto());
        model.addAttribute("userId", userId);
        return "formForGetUpAnnouncement";
    }

    @PostMapping(value = "/{userId}/newGetUpAnnouncement")
    public String getUpedAnnouncement(@PathVariable("userId") long userId, @ModelAttribute AnnouncementDto announcementDto, Model model) {
        Announcement response = interfaceBuisness.getUpAnnoncement(userId, announcementDto);
        model.addAttribute("announcementd", response);
        model.addAttribute("userId", userId);
        return "formAnnouncement";
    }

    @GetMapping(value = "/{userId}/createBestAnnouncement")
    public String createBestAnnouncement(@PathVariable("userId") long userId, Model model) {
        model.addAttribute("bestannouncementd", new BestAnnouncementDto());
        model.addAttribute("userId", userId);
        return "formForCreateBestAnnouncement";
    }

    @SneakyThrows
    @PostMapping(value = "/{userId}/newBestAnnouncement")
    public String createdBestAnnouncement(@PathVariable("userId") long userId, @ModelAttribute BestAnnouncementDto bestannouncementDto, Model model) {
        BestAnnouncement response = interfaceBuisness.addAnnouncementInBestAnnouncement(bestannouncementDto.getId(), userId);
        model.addAttribute("bestannouncementd", response);
        model.addAttribute("userId", userId);
        return "formBestAnnouncement";
    }

    @GetMapping(value = "/{userId}/deleteAnnouncement")
    public String deleteAnnouncement(@PathVariable("userId") long userId, Model model) {
        model.addAttribute("announcementd", new AnnouncementDto());
        model.addAttribute("userId", userId);
        return "formForDeleteAnnouncement";
    }

    @GetMapping(value = "/{userId}/deleteAllAnnouncement")
    public String deletedBestAnnouncement(@PathVariable("userId") long userId, @ModelAttribute AnnouncementDto announcementDto, Model model) {
        interfaceAnnouncement.deleteById(announcementDto.getId());
        model.addAttribute("userId", userId);
        return "formAfterDeleteAnnouncementAll";
    }

    @GetMapping(value = "/findAllBestAnnouncement")
    public String findAllBestAnnouncementByUser(Model model) {
        List<BestAnnouncement> list = interfaceBestAnnouncementService.readAll();
        model.addAttribute("bestannouncements", list);
        return "findAllBestAnnouncement";
    }

    @ModelAttribute("userdLogin")
    private UserDto userDto() {
        return new UserDto();
    }
}

