package by.ita.je.controller;

import by.ita.je.dto.*;
import by.ita.je.entity.Announcement;
import by.ita.je.entity.Coment;
import by.ita.je.service.InterfaceAnnouncement;
import by.ita.je.service.InterfaceBestAnnouncementService;
import by.ita.je.service.InterfaceBuisness;
import com.fasterxml.jackson.databind.ObjectMapper;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
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
    private final InterfaceBestAnnouncementService interfaceBestAnnouncementService;

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

    @GetMapping("/readall/{id}")
    public List<AnnouncementDto> readAllAnnoucement(@PathVariable(value = "id") String id) throws NotFoundException {
        return interfaceBuisness.readAllAnnoucement(Long.valueOf(id)).stream()
                .map(announcement -> objectMapper.convertValue(announcement, AnnouncementDto.class))
                .collect(Collectors.toList());

    }
    @GetMapping("/read/one")
    public AnnouncementDto readOne(@RequestParam("id") Long id,@RequestParam("user_id") Long user_id) throws NotFoundException {
        return objectMapper.convertValue(  interfaceAnnouncement.readOneWithUser(id,user_id), AnnouncementDto.class);
    }




    @ResponseBody
    @PostMapping("/add/best")
    public BestAnnouncementDto addZakladka(@RequestParam("id") Long id, @RequestParam("userId") Long userId) throws NotFoundException {

        return objectMapper.convertValue(interfaceBuisness.addAnnouncementInBestAnnouncement(id, userId), BestAnnouncementDto.class);

    }
    @GetMapping("/readallBestAnnoncement")
    public List<BestAnnouncementDto> readAllBestAnnoucement() throws NotFoundException {
        return interfaceBestAnnouncementService.readAll().stream()
                .map(bestAnnouncement -> objectMapper.convertValue(bestAnnouncement, BestAnnouncementDto.class))
                .collect(Collectors.toList());

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


    @SneakyThrows
    @ResponseBody
    @PutMapping("/updateAnnouncement")
    public AnnouncementDto updateAnnouncement(@RequestParam("id") Long id,@RequestBody AnnouncementDto announcementDto) {
       Announcement announcement = objectMapper.convertValue(announcementDto, Announcement.class);
        return objectMapper.convertValue(interfaceBuisness.update(id, announcement), AnnouncementDto.class);

    }
}
