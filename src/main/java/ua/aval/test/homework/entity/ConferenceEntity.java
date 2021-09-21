package ua.aval.test.homework.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "conference")
public class ConferenceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @NotNull
    @JsonView(ConferenceEntity.class)
    private String title;

    @Column(nullable = false)
    @NotNull
    @JsonView(ConferenceEntity.class)
    private String topic;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "conference")
    @JsonView(ConferenceEntity.class)
//    @JsonView({ConferenceEntity.class, TalkEntity.class})
    private List<TalkEntity> talks;

//    @Column(nullable = false)
//    private String date;
//
//    @Basic()
//    private Date aa;

//    @Column(nullable = false)
//    private Long numberOfParticipants;

    public ConferenceEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public List<TalkEntity> getTalks() {
        return talks;
    }

    public void setTalks(List<TalkEntity> talks) {
        this.talks = talks;
    }

    //    public String getDate() {
//        return date;
//    }
//
//    public void setDate(String date) {
//        this.date = date;
//    }

//    public Long getNumberOfParticipants() {
//        return numberOfParticipants;
//    }
//
//    public void setNumberOfParticipants(Long numberOfParticipants) {
//        this.numberOfParticipants = numberOfParticipants;
//    }

    @Override
    public String toString() {
        return "ConferenceEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", topic='" + topic + '\'' +
//                ", talks=" + talks +
                '}';
    }

}
