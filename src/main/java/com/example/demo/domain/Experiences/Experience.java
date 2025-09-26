package com.example.demo.domain.Experiences;

import com.example.demo.domain.profiles.Profile;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

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
    private LocalDate fromDate;
    @Column(name = "to_date")
    private LocalDate toDate;
    @Column(name = "is_current")
    private Boolean isCurrent;
    @Column(name = "description")
    private String description;
    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;


    @Builder
    public Experience(Long id, String company, String title, String position, LocalDate fromDate,
                      LocalDate toDate, Boolean isCurrent, String description){
        this.id = id;
        this.company = company;
        this.title = title;
        this.position = position;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.isCurrent = isCurrent;
        this.description = description;
    }
}
