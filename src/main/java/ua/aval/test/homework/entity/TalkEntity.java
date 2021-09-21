package ua.aval.test.homework.entity;

import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;

@Entity
@Table(name = "talk")
public class TalkEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @Column(nullable = false)
    @JsonView(TalkEntity.class)
    private String title;

    // @Column(nullable = false)
    @JsonView(TalkEntity.class)
    private String description;

    // @Column(nullable = false)
    @JsonView(TalkEntity.class)
    private String speaker;

    @Enumerated(EnumType.ORDINAL)
    @JsonView(TalkEntity.class)
    private TalkType type;

    @ManyToOne
    @JoinColumn(name = "conference_id")
    private ConferenceEntity conference;

    public TalkEntity() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSpeaker() {
        return speaker;
    }

    public void setSpeaker(String speaker) {
        this.speaker = speaker;
    }

    public TalkType getType() {
        return type;
    }

    public void setType(TalkType type) {
        this.type = type;
    }

    public ConferenceEntity getConference() {
        return conference;
    }

    public void setConference(ConferenceEntity conference) {
        this.conference = conference;
    }

    @Override
    public String toString() {
        return "TalkEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", speaker='" + speaker + '\'' +
                ", type=" + type +
//                ", conference=" + conference +
                '}';
    }

}
