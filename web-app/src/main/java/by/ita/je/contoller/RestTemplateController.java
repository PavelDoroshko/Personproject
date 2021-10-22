package by.ita.je.contoller;

import by.ita.je.dto.AnnouncementDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@Controller
public class RestTemplateController {
    private final RestTemplate restTemplate;
    private final String baseUrl = "http://localhost:8003/data_base-app/";

    @GetMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping("/findAll")
    public String findAll(Model model) {
        ResponseEntity<AnnouncementDto[]> responseEntity = restTemplate
                .getForEntity(baseUrl + "buisness/"+"read/"+"all", AnnouncementDto[].class);
        List<AnnouncementDto> list = Arrays.asList(responseEntity.getBody());
        model.addAttribute("announcementlist", list);
        return "findAll";
    }
}
