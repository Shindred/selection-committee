package com.project.selectioncommittee.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.io.Serializable;

@Data
@Embeddable
public class ExamResultId implements Serializable {

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "exam_subject_id")
    private ExamSubject examSubject;
}
