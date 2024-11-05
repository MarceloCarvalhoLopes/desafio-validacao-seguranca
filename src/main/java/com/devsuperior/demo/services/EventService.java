package com.devsuperior.demo.services;

import com.devsuperior.demo.dto.EventDTO;
import com.devsuperior.demo.entities.City;
import com.devsuperior.demo.entities.Event;
import com.devsuperior.demo.repositories.CityRepository;
import com.devsuperior.demo.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private CityRepository cityRepository;

    @Transactional(readOnly = true)
    public Page<EventDTO> findAllPaged(Pageable pageable) {
        Page<Event> list = eventRepository.findAll(pageable);
        return list.map(x -> new EventDTO(x));
    }

    public EventDTO insert(EventDTO dto) {

        Event entity = new Event();
        copyDtoToEntity(entity,dto);
        entity = eventRepository.save(entity);
        return new EventDTO(entity);

    }

    private void copyDtoToEntity(Event entity, EventDTO dto) {
        entity.setName(dto.getName());
        entity.setDate(dto.getDate());
        entity.setUrl(dto.getUrl());
        City city = new City();
        city = cityRepository.getReferenceById(dto.getCityId());
        entity.setCity(city);
    }
}
