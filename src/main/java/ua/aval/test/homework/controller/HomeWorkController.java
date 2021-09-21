package ua.aval.test.homework.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.PropertyValueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ua.aval.test.homework.entity.ConferenceEntity;
import ua.aval.test.homework.entity.TalkEntity;
import ua.aval.test.homework.exception.ConferenceAlreadyExistException;
import ua.aval.test.homework.exception.ConferenceNotFoundException;
import ua.aval.test.homework.service.ConferenceService;
import ua.aval.test.homework.service.TalkService;

@RestController
@RequestMapping("/conferences")
public class HomeWorkController {

    @Autowired
    private ConferenceService conferenceService;

    @Autowired
    private TalkService talkService;

//    @GetMapping("/")
//    public ResponseEntity checkServer() {
//        try {
//            return ResponseEntity.ok("Сервер работает!");
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body("Произошла ошибка!");
//        }
//    }

    @JsonView(ConferenceEntity.class)
    @GetMapping
    public ResponseEntity getConferences() {
        try {
            return ResponseEntity.ok(conferenceService.getConferences());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка!");
        }
    }

    @GetMapping("/")
    public ResponseEntity getConferenceById(@RequestParam(name = "id") Long conferenceId) {
        try {
            return ResponseEntity.ok(conferenceService.getConferenceById(conferenceId));
        } catch (ConferenceNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка!");
        }
    }

//    @GetMapping("/")
//    @ResponseBody
//    public ConferenceEntity getConferenceById(@RequestParam(name = "id") Long conferenceId) throws ConferenceNotFoundException {
//            return conferenceService.getConferenceById(conferenceId);
//    }

    @JsonView(ConferenceEntity.class)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addConference(@Validated @RequestBody ConferenceEntity conference) {
        try {
//            conferenceService.addConference(conference);
//            return ResponseEntity.ok("Конференция успешно добавлена");
            return ResponseEntity.ok(conferenceService.addConference(conference));
        } catch (ConferenceAlreadyExistException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{conference_id}")
    public ResponseEntity changeConference(@PathVariable("conference_id") Long conferenceId,
                                           @RequestBody ConferenceEntity conference) {
        try {
            conferenceService.changeConference(conferenceId, conference);
            return ResponseEntity.ok("Конференция успешно обновлена");
        } catch (ConferenceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @PostMapping(value = "/{conference_id}/talks")
    public ResponseEntity addTalk(@PathVariable("conference_id") Long conferenceId,
                                  @RequestBody TalkEntity talk) {
        try {
            talkService.addTalk(conferenceId, talk);
            return ResponseEntity.ok("Доклад успешно добавлен");
        } catch (ConferenceNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{conference_id}/talks")
    public ResponseEntity getTalksByConference(@PathVariable("conference_id") Long conferenceId) {
        try {
            return ResponseEntity.ok(talkService.getTalksByConferenceId(conferenceId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Что-то пошло не так!");
        }
    }

}