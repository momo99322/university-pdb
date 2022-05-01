package ru.spbstu.korobtsov.api.domain;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name ="lectures")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Lecture {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String id;

    private String name;

    private Instant time;

    @ManyToOne
    @JoinColumn(name = "lecturer_id")
    private Lecturer lecturer;

}
