package com.scholarship.restservice.entites;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "SINHVIEN")
@Data
public class Student {
    @Id
    private String IDMSSV;
    private String NAME;
    private String GENDER;
    private String BRITHDATE;
    private String OVERALLSCORE;
    private String FAMILYSITUATION;

}
