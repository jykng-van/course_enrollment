package com.jykng.enrollment.model;

import lombok.Data;

@Data
public class CourseInput {
    long teacherId;
    Long subjectId;
    String startDate;
    String endDate;
}
