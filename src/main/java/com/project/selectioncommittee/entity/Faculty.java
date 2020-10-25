package com.project.selectioncommittee.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "faculty")
public class Faculty {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private Integer id;
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    @Column(name = "budget_places")
    private int budgetPlacesAmount;
    @Getter
    @Setter
    @Column(name = "contract_places")
    private int contractPlacesAmount;
    @Getter
    @Setter
    @Column(name = "passing_grade")
    private int passingGrade;
    @Getter
    @Setter
    @Column(name = "statement_is_finalized")
    private boolean statementIsFinalized;
}
