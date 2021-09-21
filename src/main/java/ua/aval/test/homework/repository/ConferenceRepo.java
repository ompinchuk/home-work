package ua.aval.test.homework.repository;

import org.springframework.data.repository.CrudRepository;
import ua.aval.test.homework.entity.ConferenceEntity;

import java.util.List;

public interface ConferenceRepo extends CrudRepository<ConferenceEntity, Long> {

    // List<ConferenceEntity> findByTitle(String title);
    boolean existsByTitle(String title);

}
