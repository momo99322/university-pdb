package ru.spbstu.korobtsov.api.domain;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "attendance")
@Getter
@Setter
@ToString
public class Attendance {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String id;
    @ManyToOne
    @JoinColumn(name = "lecture_id")
    private Lecture lecture;
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;
    @Column(columnDefinition = "boolean default false")
    private boolean attendance;
}
