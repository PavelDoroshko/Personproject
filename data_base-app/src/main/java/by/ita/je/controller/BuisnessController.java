package by.ita.je.controller;

import by.ita.je.dto.*;
import by.ita.je.module.Announcement;
import by.ita.je.module.Coment;
import by.ita.je.module.User;
import by.ita.je.service.api.InterfaceBuisness;
import com.fasterxml.jackson.databind.ObjectMapper;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/buisness")
public class BuisnessController {
    private final ObjectMapper objectMapper;
    private final InterfaceBuisness interfaceBuisness;

    @ResponseBody
    @PostMapping("/create")
    public AnnouncementDto createAnnouncement(@RequestBody AnnouncementDto announcementDto) throws NotFoundException {
        //User user = objectMapper.convertValue(userDto, User.class);
        Announcement announcement = objectMapper.convertValue(announcementDto, Announcement.class);
        return objectMapper.convertValue(interfaceBuisness.createAnnouncement(announcement), AnnouncementDto.class);
    }

    @GetMapping("/read/one")
    public AnnouncementDto readOne(@RequestParam("id") long id) throws NotFoundException {

        return null;
    }

    @DeleteMapping(value = "/delete")
    public void deleteById(@RequestParam(value = "id", required = false) String id) {
        interfaceBuisness.deleteById(Long.valueOf(id));
    }

    @PutMapping("/update")
    public AnnouncementDto update(@RequestParam("id") Long id,
                                  @RequestBody AnnouncementDto announcementDto) throws NotFoundException {
        Announcement announcement = objectMapper.convertValue(announcementDto, Announcement.class);
        return objectMapper.convertValue(interfaceBuisness.update(id, announcement), AnnouncementDto.class);
    }

    @GetMapping("/read/all")
    public List<AnnouncementDto> readAll(@RequestBody UserDto userDto) throws NotFoundException {
        User user = objectMapper.convertValue(userDto, User.class);
        return interfaceBuisness.readAll(user).stream()
                .map(announcement -> objectMapper.convertValue(announcement, AnnouncementDto.class))
                .collect(Collectors.toList());

    }

    @ResponseBody
    @PostMapping("/update/coment")
    public ComentDto coment(@RequestParam("id") Long id,@RequestBody ComentDto comentDto) {
        Coment coment = objectMapper.convertValue(comentDto, Coment.class);
        return objectMapper.convertValue(interfaceBuisness.createComent(id,coment), ComentDto.class);

    }
    @ResponseBody
    @PostMapping("/add/best")
    public BestAnnouncementDto addZakladka(@RequestParam("id") Long id,@RequestBody UserDto userDto) throws NotFoundException {
      //  Announcement announcement = objectMapper.convertValue(announcementDto, Announcement.class);
        User user = objectMapper.convertValue(userDto, User.class);
        return objectMapper.convertValue(interfaceBuisness.addAnnouncementInBestAnnouncement(id,user),BestAnnouncementDto.class);
       // @RequestBody UserDto userDto
    }
    @ResponseBody
    @PostMapping("/creditcart")
    public CreditCartDto createCreditcart(@RequestBody UserDto userDto) throws NotFoundException {
        User user = objectMapper.convertValue(userDto, User.class);
        return objectMapper.convertValue(interfaceBuisness.createCreditCart(user),CreditCartDto.class);

    }
    @ResponseBody
    @PutMapping("/add/balance")
    public UserDto addBalance(@RequestBody UserDto userDto) throws NotFoundException {
        User user = objectMapper.convertValue(userDto, User.class);
        return objectMapper.convertValue(interfaceBuisness.addBalance(user), UserDto.class);

    }
    @ResponseBody
    @PutMapping("/getup")
    public AnnouncementDto getup(@RequestBody AnnouncementDto announcementDto) throws NotFoundException {
       Announcement announcement= objectMapper.convertValue(announcementDto,Announcement.class);
        return objectMapper.convertValue(interfaceBuisness.getUpAnnoncement(announcement), AnnouncementDto.class);

    }
}