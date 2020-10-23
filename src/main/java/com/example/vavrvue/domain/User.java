package com.example.vavrvue.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;


@Data
@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "users")
public class User implements CommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Column(name = "name",unique = true)
    private String name;

    public User(String name) {
        this.name=name;
    }
}
