package com.project.selectioncommittee.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.Set;

@Data
@Entity
public class Faculty {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(unique = true)
    private String name;

    @Column(name = "budget_places")
    private int budgetPlacesAmount;

    @Column(name = "contract_places")
    private int contractPlacesAmount;

    @Column
    private int passingGrade;

    @Column
    private boolean statementIsFinalized;

    @ManyToMany
    @JoinTable(
            name = "faculty_exam_subjects",
            joinColumns = @JoinColumn(name = "faculty_id"),
            inverseJoinColumns = @JoinColumn(name = "exam_subject_id")
    )
    private Set<ExamSubject> examSubjects;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "faculty")
    private Set<ApplicantResult> applicantResults;
}
