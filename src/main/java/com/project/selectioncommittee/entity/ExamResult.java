package com.project.selectioncommittee.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table
@ToString
public class ExamResult implements Serializable {

    @EmbeddedId
    private ExamResultId examResultId;

    @Column
    private int grade;
}
