package com.scholarship.restservice.entites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
public class User {
    float overallScore;
    private String mssv, name, gender, brithDate, familySituation, idHocBong, ngayNhan, soTien, tenToChuc;

}

