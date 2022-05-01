package ru.spbstu.korobtsov.api.domain;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "lecturers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Lecturer {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String id;

    private String name;

    private String email;
}
