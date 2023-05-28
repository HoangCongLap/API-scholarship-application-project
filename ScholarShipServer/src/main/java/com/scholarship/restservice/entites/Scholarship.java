package com.scholarship.restservice.entites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.sql.Date;

@Entity(name = "THONGTINHOCBONG")
@Data
public class Scholarship {
    @Id
    @Column(name = "IDHocBong")
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id = "0";

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "IDSinhVien")
    private Student student;

    private Date NGAYNHAN;
    private int SOTIEN;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "IDToChuc")
    private Organizetion organizetion;
}