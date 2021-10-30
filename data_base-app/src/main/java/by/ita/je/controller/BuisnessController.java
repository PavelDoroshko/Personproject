package by.ita.je.controller;

import by.ita.je.dto.*;
import by.ita.je.module.Announcement;
import by.ita.je.module.Coment;
import by.ita.je.module.User;
import by.ita.je.service.api.InterfaceAnnouncement;
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
    private final InterfaceAnnouncement interfaceAnnouncement;
    @ResponseBody
    @PostMapping("/create")
    public AnnouncementDto createAnnouncement(@RequestParam("id") long id,@RequestBody AnnouncementDto announcementDto) throws NotFoundException {
        Announcement announcement = objectMapper.convertValue(announcementDto, Announcement.class);
        return objectMapper.convertValue(interfaceBuisness.createAnnouncement(id,announcement), AnnouncementDto.class);
    }


    @DeleteMapping(value = "/delete")
    public void deleteById(@RequestParam(value = "id", required = false) String id) {
        interfaceBuisness.deleteById(Long.valueOf(id));
    }

    @PostMapping("/update/{id}")
    public AnnouncementDto update(@PathVariable("id") String id,
                                  @RequestBody AnnouncementDto announcementDto) throws NotFoundException {
        Announcement announcement = objectMapper.convertValue(announcementDto, Announcement.class);
        return objectMapper.convertValue(interfaceBuisness.update(Long.valueOf(id), announcement), AnnouncementDto.class);
    }

    @GetMapping("/readall/{id}")
    public List<AnnouncementDto> readAllAnnoucement(@PathVariable(value = "id") String id) throws NotFoundException {
        return interfaceBuisness.readAllAnnoucement(Long.valueOf(id)).stream()
                .map(announcement -> objectMapper.convertValue(announcement, AnnouncementDto.class))
                .collect(Collectors.toList());

    }
    @GetMapping("/read/one")
    public AnnouncementDto readOne(@RequestParam("id") Long id) throws NotFoundException {
        return objectMapper.convertValue(  interfaceAnnouncement.readOne(id), AnnouncementDto.class);
    }

    @ResponseBody
    @PostMapping("/update/coment")
    public ComentDto coment(@RequestParam("id") Long id, @RequestBody ComentDto comentDto) {
        Coment coment = objectMapper.convertValue(comentDto, Coment.class);

       return objectMapper.convertValue(interfaceBuisness.createComent(id, coment), ComentDto.class);

    }

    @ResponseBody
    @PostMapping("/add/best")
    public BestAnnouncementDto addZakladka(@RequestParam("id") Long id, @RequestParam("userId") Long userId) throws NotFoundException {

        return objectMapper.convertValue(interfaceBuisness.addAnnouncementInBestAnnouncement(id, userId), BestAnnouncementDto.class);

    }

    @ResponseBody
    @PostMapping("/creditcart")
    public CreditCartDto createCreditcart(@RequestParam("id") Long id) throws NotFoundException {

        return objectMapper.convertValue(interfaceBuisness.createCreditCart(id), CreditCartDto.class);

    }

    @ResponseBody
    @PutMapping("/add/balance")
    public UserDto addBalance(@RequestParam("userId") Long userId) throws NotFoundException {

        return objectMapper.convertValue(interfaceBuisness.addBalance(userId), UserDto.class);

    }

    @ResponseBody
    @PutMapping("/getup")
    public AnnouncementDto getup(@RequestParam("id") Long id,@RequestParam("money") int money,@RequestParam("id") Long userId) throws NotFoundException {

        return objectMapper.convertValue(interfaceBuisness.getUpAnnoncementMoney(id,money,userId), AnnouncementDto.class);

    }
}
