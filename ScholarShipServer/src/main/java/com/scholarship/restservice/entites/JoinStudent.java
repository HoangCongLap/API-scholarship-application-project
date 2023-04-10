package com.scholarship.restservice.entites;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;


public class JoinStudent {

    private String IDMSSV;
    private String NAME;
    private String GENDER;
    private Date BRITHDATE;
    private String OVERALLSCORE;
    private String FAMILYSITUATION;


    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "IDSinhVien")
    private Student student;

    private Date NGAYNHAN;
    private int SOTIEN;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "IDToChuc")
    private Organizetion organizetion;
}
