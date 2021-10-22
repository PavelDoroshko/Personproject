package by.ita.je.controller;

import by.ita.je.dto.CarDto;
import by.ita.je.service.CarService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/car")
public class CarController {
    private final ObjectMapper objectMapper;
    private final CarService carService;

    @GetMapping("/read/all")
    public List<CarDto> readAll() {
        return carService.readAll().stream()
                .map(car -> objectMapper.convertValue(car, CarDto.class))
                .collect(Collectors.toList());
    }

}
