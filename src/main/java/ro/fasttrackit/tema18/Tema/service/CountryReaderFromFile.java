package ro.fasttrackit.tema18.Tema.service;

import org.springframework.stereotype.Component;
import ro.fasttrackit.tema18.Tema.model.Country;

import java.io.BufferedReader;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Component
public class CountryReaderFromFile {

    public List<Country> readFromFile() throws Exception {
        List<Country> result = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new FileReader("file/countries.txt"));
        String line;
        int id = 1;
        while ((line = bufferedReader.readLine()) != null) {
            result.add(getCountry(id++, line));
        }
        return result;
    }

    private Country getCountry(int id, String line) {
        String[] country = line.split("\\|");
        List<String> neighbours = new ArrayList<>();
        if (country.length > 5) {
            neighbours = newNeighbours(country[5]);
        }
        return new Country(id,
                country[0],
                country[1],
                Long.parseLong(country[2]),
                Long.parseLong(country[3]),
                country[4],
                neighbours);
    }


    private List<String> newNeighbours(String s) {
        String[] neighbours = s.split("~");
        List<String> list = new ArrayList<>();
        for (String st : neighbours) {
            list.add(st);
        }
        return list;

    }

}


