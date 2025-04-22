package com.taskmanager.taskmanagerproject.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.*;

@ToString(exclude = { "parent", "subtasks", "comments" }) // Prevents circular toString calls
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id") // Prevents infinite recursion in JSON
@Entity
@Getter
@Setter
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id") // Maps to `id` column in DB
    private Long id;

    @Column(name = "title") // Task title
    private String title;

    @Column(name = "description") // Detailed task description
    private String description;

    @Column(name = "due_date") // Deadline for the task
    private LocalDate dueDate;

    @Column(name = "responsible") // Person responsible for completing the task
    private String responsible;

    @ManyToOne
    @JoinColumn(name = "parent_id") // Self-referencing parent task (for subtasks)
    private Task parent;

    @Builder.Default
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<Task> subtasks = new ArrayList<>(); // Child tasks linked to this task

    @Builder.Default
    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>(); // Comments associated with this task
}
