package com.project.selectioncommittee.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table
public class Certificate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private int ukrainianLanguageGrade;

    @Column
    private int mathematicsGrade;

    @Column
    private int historyGrade;

    @Column
    private int physicsGrade;

    @Column
    private int geographyGrade;

    @Column
    private int englishLanguageGrade;
}
