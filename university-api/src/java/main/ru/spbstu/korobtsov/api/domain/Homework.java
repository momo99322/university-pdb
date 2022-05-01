package ru.spbstu.korobtsov.api.domain;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@Table(name = "homework")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
public class Homework {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String id;

    private String title;

    private String Content;

    @Min(0)
    @Max(5)
    @Column(columnDefinition = "integer default 0")
    private int mark;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "lecture_id")
    private Lecture lecture;

}
