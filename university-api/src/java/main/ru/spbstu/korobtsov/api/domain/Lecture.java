package ru.spbstu.korobtsov.api.domain;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

import static javax.persistence.CascadeType.REMOVE;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "lectures")
@JsonFilter("lectureFilter")
public class Lecture {

    @Id
    @JsonIgnore
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @Length(min = 1)
    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime time;

    @ManyToOne
    @JoinColumn(name = "lecturer_id")
    private Lecturer lecturer;

    @OneToMany(cascade = REMOVE, mappedBy = "lecture", fetch = FetchType.EAGER)
    private Set<Attendance> attendance;

    @JsonIgnore
    @OneToMany(cascade = REMOVE, mappedBy = "lecture", fetch = FetchType.EAGER)
    private Set<Homework> homework;

    @Override
    public String toString() {
        return "Lecture{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", time=" + time +
                ", lecturer=" + lecturer +
                '}';
    }
}