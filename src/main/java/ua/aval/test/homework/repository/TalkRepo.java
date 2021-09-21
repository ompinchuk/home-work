package ua.aval.test.homework.repository;

import org.springframework.data.repository.CrudRepository;
import ua.aval.test.homework.entity.TalkEntity;

public interface TalkRepo extends CrudRepository<TalkEntity, Long> {

    Iterable<TalkEntity> findByConferenceId(Long conferenceId);

}
