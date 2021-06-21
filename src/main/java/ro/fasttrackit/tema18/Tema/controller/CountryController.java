package ro.fasttrackit.tema18.Tema.controller;

import org.springframework.web.bind.annotation.*;
import ro.fasttrackit.tema18.Tema.model.Country;
import ro.fasttrackit.tema18.Tema.service.CountryService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/countries")
public class CountryController {

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    public List<Country> getCountries(@RequestParam(required = false) String includeNeighbour,
                                      @RequestParam(required = false) String excludeNeighbour) {
        return countryService.getCountries(includeNeighbour, excludeNeighbour);
    }

    @GetMapping("/names")
    public List<String> getCountriesName(){
        return countryService.getCountriesName();

    }

    @GetMapping("/{countryId}/capital")
    public List<String> getCapital(@PathVariable int countryId){
        return countryService.getCapital(countryId);
    }

    @GetMapping("/{countryId}/population")
    public List<Long> getPopulation(@PathVariable int countryId){
        return countryService.getPopulatation(countryId);

    }

    @GetMapping("/{countryId}/neighbours")
    public List<String> getCountryNeighbours(@PathVariable int countryId){
        return countryService.getNeighbours(countryId);
    }
    @GetMapping("/population")
    public Map<String, Long> mapCountriesToPopulation(){
        return countryService.mapCountriesToPopulation();
    }
    @GetMapping("/continent/countries")
    public Map<String, List<Country>> mapContinentsToListOfCountry(){
        return countryService.mapContinentsToListOfCountry();
    }


}
