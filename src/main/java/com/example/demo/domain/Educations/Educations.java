package com.example.demo.domain.Educations;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "Educations")
@NoArgsConstructor
@Getter
public class Educations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;
    @Column(name = "school")
    private String school;
    @Column(name = "degree")
    private String degree;
    @Column(name = "field")
    private String field;
    @Column(name = "from_date")
    private LocalDateTime from_date;
    @Column(name = "to_date")
    private LocalDateTime to_date;
    @Column(name = "is_current")
    private Boolean is_current;

    @Builder
    public Educations(Long id, String school, String degree, String field, LocalDateTime from_date,
                      LocalDateTime to_date, Boolean is_current){
        this.id = id;
        this.school = school;
        this.degree = degree;
        this.field = field;
        this.from_date = from_date;
        this.to_date = to_date;
        this.is_current = is_current;
    }
}
