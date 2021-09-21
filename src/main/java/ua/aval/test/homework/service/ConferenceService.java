package ua.aval.test.homework.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.aval.test.homework.entity.ConferenceEntity;
import ua.aval.test.homework.exception.ConferenceAlreadyExistException;
import ua.aval.test.homework.exception.ConferenceNotFoundException;
import ua.aval.test.homework.repository.ConferenceRepo;

import java.util.List;

@Service
public class ConferenceService {

    @Autowired
    private ConferenceRepo conferenceRepo;

    public Iterable<ConferenceEntity> getConferences() {
        return conferenceRepo.findAll();
    }

    public ConferenceEntity getConferenceById(Long conferenceId) throws ConferenceNotFoundException {
        return conferenceRepo.findById(conferenceId)
                .orElseThrow(() -> new ConferenceNotFoundException("Конференция не найдена!"));
    }

    public ConferenceEntity addConference(ConferenceEntity conferenceEntity) throws ConferenceAlreadyExistException {
        // System.out.println(">>>Trololo>>> " + conferenceRepo.findByTitle(conferenceEntity.getTitle()));

        // if(!conferenceRepo.findByTitle(conferenceEntity.getTitle()).isEmpty()) {
        if(conferenceRepo.existsByTitle(conferenceEntity.getTitle())) {
            throw new ConferenceAlreadyExistException("Конференция с таким именем уже существует!");
        }

        return conferenceRepo.save(conferenceEntity);
    }

    public ConferenceEntity changeConference(Long id, ConferenceEntity conferenceEntity) throws ConferenceNotFoundException {
        ConferenceEntity conference = conferenceRepo.findById(id)
                .orElseThrow(() -> new ConferenceNotFoundException("Конференция не найдена!"));

        System.out.println(conferenceEntity);
        System.out.println(conference);
        BeanUtils.copyProperties(conferenceEntity, conference, "id");

        return conferenceRepo.save(conference);
    }

}
