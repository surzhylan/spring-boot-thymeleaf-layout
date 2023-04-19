package kz.bitlab.springThymeleafLD.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "tasks")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "deadline_date")
    private String deadlineDate;

    @Column(name = "isCompleted")
    private boolean isCompleted;

    @Column(name = "description")
    private String description;
}
