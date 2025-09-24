package com.example.demo.domain.Educations;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "Educations")
@NoArgsConstructor
@Getter
public class Education {
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
    private LocalDate fromDate;
    @Column(name = "to_date")
    private LocalDate toDate;
    @Column(name = "is_current")
    private Boolean isCurrent;

    @Builder
    public Education(Long id, String school, String degree, String field, LocalDate fromDate,
                     LocalDate toDate, Boolean isCurrent){
        this.id = id;
        this.school = school;
        this.degree = degree;
        this.field = field;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.isCurrent = isCurrent;
    }
}
