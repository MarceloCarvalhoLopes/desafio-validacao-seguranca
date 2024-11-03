package com.devsuperior.demo.services;

import com.devsuperior.demo.dto.CityDTO;
import com.devsuperior.demo.entities.City;
import com.devsuperior.demo.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CityService {

    @Autowired
    private CityRepository repository;

    @Transactional(readOnly = true)
    public List<CityDTO> findAll() {
        List<City> list = repository.findAll(Sort.by("name"));
        return list.stream().map(x -> new CityDTO(x)).toList();
    }

    public CityDTO insert(CityDTO dto) {
        City entity =  new City();
        convertDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new CityDTO(entity);
    }

    private void convertDtoToEntity(CityDTO dto, City entity) {
        entity.setName(dto.getName());
    }
}
