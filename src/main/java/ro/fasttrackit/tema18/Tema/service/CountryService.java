package ro.fasttrackit.tema18.Tema.service;

import org.springframework.stereotype.Service;
import ro.fasttrackit.tema18.Tema.model.Country;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.*;

@Service
public class CountryService {
    private final CountryReaderFromFile countryReaderFromFile;
    private final List<Country> countries = new ArrayList<>();

    public CountryService(CountryReaderFromFile countryReaderFromFile) throws Exception {
        this.countryReaderFromFile = countryReaderFromFile;
        countries.addAll(countryReaderFromFile.readFromFile());
    }

    public List<Country> getCountries(String includeNeighbour, String excludeNeighbour) {
        return countries.stream()
                .filter(country -> country.getNeighbours().contains(includeNeighbour))
                .filter(country -> !country.getNeighbours().contains(excludeNeighbour))
                .collect(toList());
    }
    public List<String> getCountriesName(){
        return countries.stream()
                .map(country -> country.getName())
                .collect(toList());
    }

    public List<String> getCapital(int id){
        return countries.stream()
                .filter(country -> country.getId() == id)
                .map(country -> country.getCapital())
                .collect(toList());
    }

    public List<Long> getPopulatation(int countryId) {
        return countries.stream()
                .filter(country -> country.getId() == countryId)
                .map(country -> country.getPopulation())
                .collect(toList());
    }

    public List<String> getNeighbours(int countryId) {
        return countries.stream()
                .filter(country -> country.getId() == countryId)
                .findFirst()
                .get().getNeighbours();

    }

    public Map<String, Long> mapCountriesToPopulation() {
        return countries.stream()
                .collect(toMap(country -> country.getName(), country -> country.getPopulation()));
    }

    public Map<String, List<Country>> mapContinentsToListOfCountry() {
        return countries.stream()
                .collect(groupingBy(Country::getContinent));
    }
}
