package ua.aval.test.homework.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.aval.test.homework.entity.ConferenceEntity;
import ua.aval.test.homework.entity.TalkEntity;
import ua.aval.test.homework.exception.ConferenceNotFoundException;
import ua.aval.test.homework.repository.ConferenceRepo;
import ua.aval.test.homework.repository.TalkRepo;

import java.util.List;

@Service
public class TalkService {

    @Autowired
    private TalkRepo talkRepo;

    @Autowired
    private ConferenceRepo conferenceRepo;

    public TalkEntity addTalk(Long conferenceId, TalkEntity talkEntity) throws ConferenceNotFoundException {
        ConferenceEntity conference = conferenceRepo.findById(conferenceId)
                .orElseThrow(() -> new ConferenceNotFoundException("Конференция не найдена!"));

        talkEntity.setConference(conference);

        return talkRepo.save(talkEntity);
    }

    public Iterable<TalkEntity> getTalksByConferenceId(Long conferenceId) {
        return talkRepo.findByConferenceId(conferenceId);
    }

}
