package com.scholarship.restservice.entites;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity(name ="tochuc")
@Data
public class Organizetion {
    @Id
    private String ID;
    private String TENTOCHUC;
}
