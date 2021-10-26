package by.ita.je.controller;

import by.ita.je.dto.AnnouncementDto;
import by.ita.je.dto.SearchDto;
import by.ita.je.service.SearchService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/search")
public class SearchController {
    private final ObjectMapper objectMapper;
    private final SearchService searchService;


    @PostMapping(value = "/criteria")
    public List<AnnouncementDto> readByCriteria(@RequestBody SearchDto searchDto){
        return searchService.findCriteria(searchDto.getNameCar(),
                searchDto.getModelCar(),searchDto.getPrice(),searchDto.getTypeEngine(),
                searchDto.getYearOfIssue(),searchDto.getMilage(),searchDto.getVolumeEngine(),searchDto.getTransmission(),
                searchDto.getLocation(),searchDto.getCustom(),searchDto.getExchange())
                .stream()
                .map(announcement -> objectMapper.convertValue(announcement, AnnouncementDto.class))
                .collect(Collectors.toList());
    }

}
