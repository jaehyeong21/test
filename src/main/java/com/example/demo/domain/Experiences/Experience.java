package com.example.demo.domain.Experiences;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "experiences")
@NoArgsConstructor
@Getter
public class Experience {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;
    @Column(name = "company")
    private String company;
    @Column(name = "title")
    private String title;
    @Column(name = "position")
    private String position;
    @Column(name = "from_date")
    private LocalDateTime from_date;
    @Column(name = "to_date")
    private LocalDateTime to_date;
    @Column(name = "is_current")
    private Boolean is_current;
    @Column(name = "description")
    private String description;


    @Builder
    public Experience(Long id, String company, String title, String position, LocalDateTime from_date,
                      LocalDateTime to_date, Boolean is_current, String description){
        this.id = id;
        this.company = company;
        this.title = title;
        this.position = position;
        this.from_date = from_date;
        this.to_date = to_date;
        this.is_current = is_current;
        this.description = description;
    }
}
