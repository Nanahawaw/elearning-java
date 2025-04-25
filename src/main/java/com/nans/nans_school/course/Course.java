package com.nans.nans_school.course;

import com.nans.nans_school.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    @NotNull
    private String title;

    @NotNull
    private String description;


    private boolean published;

    @NotNull
    private int price;

    private String duration;

    private String thumbnailUrl;

    @ManyToOne
    @JoinColumn(name="tutor_id")
    private User tutor;


}
