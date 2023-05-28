package com.scholarship.restservice.controller;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.scholarship.restservice.entites.*;
import com.scholarship.restservice.jpadatabase.LoginUserRepository;
import com.scholarship.restservice.jpadatabase.OrganizetionRepository;
import com.scholarship.restservice.jpadatabase.ScholarshipRepository;
import com.scholarship.restservice.jpadatabase.StudentRepsitory;
import com.scholarship.restservice.utils.HashPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceController {
    private static Gson GSON = new Gson();
    @Autowired
    private LoginUserRepository loginUserRepository;
    @Autowired
    private ScholarshipRepository scholarshipRepository;
    @Autowired
    private OrganizetionRepository organizetionRepository;
    @Autowired
    private StudentRepsitory studentRepsitory;


    //    Login API
    //usename=lap;
    //password=123;
    @GetMapping("/login")
    public LoginResponse login(@RequestParam(value = "username") String userName,
                               @RequestParam(value = "password") String passWord) {
        String hashPassword = HashPassword.hash(passWord);
        LoginUser byUsename = loginUserRepository.findByUsename(userName);
        boolean isLoginSuccess = false;
//        kiểm tra password đã được mã hóa
        if (byUsename.getPassword().equals(hashPassword)) {
            isLoginSuccess = true;
        }
        System.out.printf("Call API /login :  username: %s, password: %s, isLoginSuccess: %s\n", userName, "***", isLoginSuccess);
        return new LoginResponse(isLoginSuccess, userName);
    }

    //    Set data add signup API
    @GetMapping("/setdataaddsignup")
    public String saveDataAddSignUp(@RequestParam(value = "username") String userName,
                                    @RequestParam(value = "password") String passWord) {
        LoginUser loginUser = new LoginUser();
        loginUser.setUsename(userName);
//        Mã hóa passwoerd(encrypt pass)
        String hashPassword = HashPassword.hash(passWord);
        loginUser.setPassword(hashPassword);
        loginUserRepository.save(loginUser);
        System.out.printf("Call API /setdataaddsignup to list result: %s \n", GSON.toJson(loginUser));
        return "";
    }

    //    Get data all scholarship users
    @GetMapping("/getdataallscholarshipusers")
    public List<User> getDataAllScholarshipUsers() {
        Iterable<Scholarship> all = scholarshipRepository.findAll();
        List<User> result = new ArrayList<>();
        for (Scholarship s : all) {
            User user = new User(Float.parseFloat(s.getStudent().getOVERALLSCORE()), s.getStudent().getIDMSSV(),
                    s.getStudent().getNAME(), s.getStudent().getGENDER(), s.getStudent().getBRITHDATE(),
                    s.getStudent().getFAMILYSITUATION(),
                    s.getId(), s.getNGAYNHAN().toString(), s.getSOTIEN() + "", s.getOrganizetion().getTENTOCHUC());
            result.add(user);

        }
        System.out.printf("Call API /getdataallscholarshipusers to list the resulting length: %s,%s \n", result.size(), GSON.toJson(result));
        return result;
    }


    //    Get data all users
    @GetMapping("/getdataallusers")
    public List<User> getDataAllUsers() {
        Iterable<Scholarship> all = scholarshipRepository.findAll();
        List<User> result = new ArrayList<>();
        Iterable<Student> students = studentRepsitory.findAll();
        for (Student student : students) {
            boolean isFound = false;
            for (Scholarship scholarship : all) {
//                kiểm tra điều kiện có học sinh nào không
                if (scholarship.getStudent().getIDMSSV().equals(student.getIDMSSV())) {
                    User user = new User(Float.parseFloat(student.getOVERALLSCORE()), student.getIDMSSV(), student.getNAME(),
                            student.getGENDER(), student.getBRITHDATE(), student.getFAMILYSITUATION(), scholarship.getId(),
                            scholarship.getNGAYNHAN().toString(), scholarship.getSOTIEN() + "",
                            scholarship.getOrganizetion().getTENTOCHUC());
                    result.add(user);
                    isFound = true;
                    break;
                }
            }

            if (!isFound) {
                User user = new User(Float.parseFloat(student.getOVERALLSCORE()), student.getIDMSSV(),
                        student.getNAME(), student.getGENDER(), student.getBRITHDATE(),
                        student.getFAMILYSITUATION(),
                        "", "", "", "");
                result.add(user);
            }
        }
//        System.out.printf("Call API /getdataallusers to list the resulting length: %s,%s \n", result.size(), result.toString());
        System.out.printf("Call API /getdataallusers to list the resulting length: %s,%s \n", result.size(), GSON.toJson(result));
        return result;
    }

    //    Set data add student
    @GetMapping("/setdataaddstudent")
    public String setDataAddStudents(@RequestParam(value = "mssv") String mssv,
                                     @RequestParam(value = "name") String name,
                                     @RequestParam(value = "gender") String gender,
                                     @RequestParam(value = "brithDate") String brithDate,
                                     @RequestParam(value = "overallScore") float overallScore,
                                     @RequestParam(value = "familySituation") String familySituation) {
        Student student = new Student();
        student.setIDMSSV(mssv);
        student.setNAME(name);
        student.setGENDER(gender);
        student.setBRITHDATE(brithDate);
        student.setOVERALLSCORE(String.valueOf(overallScore));
        student.setFAMILYSITUATION(familySituation);
        studentRepsitory.save(student);

        System.out.printf("Call API /setdataaddstudent to list result: %s \n", GSON.toJson(student));
        return "";
    }


    //    Set data add organization
    @GetMapping("/setdataaddorganization")
    public String saveDataOrganization(@RequestParam(value = "id") String id,
                                       @RequestParam(value = "tenToChuc") String tenToChuc) {
        Organizetion organizetion = new Organizetion();
        organizetion.setID(id);
        organizetion.setTENTOCHUC(tenToChuc);
        organizetionRepository.save(organizetion);

        System.out.printf("Call API /setdataaddorganization to list the resulting: %s \n", GSON.toJson(organizetion));
        return "";
    }

    //    updata data add users (student form)
    @GetMapping("/updatedataaaddusers")
    public String updateDataAddUsers(@RequestParam(value = "IDHocBong") String idHocBong,
                                     @RequestParam(value = "IDToChuc") String idToChuc,
                                     @RequestParam(value = "NGAYNHAN") String NGAYNHAN,
                                     @RequestParam(value = "SOTIEN") Integer SOTIEN) throws ParseException {

        Optional<Scholarship> opScholarship = scholarshipRepository.findById(idHocBong);
        Scholarship scholarship = null;
        if (opScholarship.isPresent()) {
            scholarship = opScholarship.get();
            scholarship.setNGAYNHAN(Date.valueOf(NGAYNHAN));
            scholarship.setSOTIEN(SOTIEN);
            Organizetion organizetion = scholarship.getOrganizetion();
            if (!organizetion.getID().equals(idToChuc)) {
                Optional<Organizetion> optionalOrganizetion = organizetionRepository.findById(idToChuc);
                if (optionalOrganizetion.isPresent()) {
                    scholarship.setOrganizetion(optionalOrganizetion.get());
                }
            }
            scholarshipRepository.save(scholarship);
        }

        System.out.printf("Call API /updatedataaaddusers list result: %s \n", GSON.toJson(scholarship));
        return "";
    }

    //    create data add users (student form)
    @GetMapping("/createdataaaddusers")
    public String createDataAddUsers(@RequestParam(value = "IDSinhVien") String idSinhVien,
                                     @RequestParam(value = "IDToChuc") String idToChuc,
                                     @RequestParam(value = "NGAYNHAN") String NGAYNHAN,
                                     @RequestParam(value = "SOTIEN") Integer SOTIEN) {

        Optional<Student> optionalStudent = studentRepsitory.findById(idSinhVien);
        Optional<Organizetion> optionalOrganizetion = organizetionRepository.findById(idToChuc);
        Scholarship scholarship = new Scholarship();
        if (optionalStudent.isPresent() && optionalOrganizetion.isPresent()) {
            scholarship.setStudent(optionalStudent.get());
            scholarship.setOrganizetion(optionalOrganizetion.get());
            scholarship.setNGAYNHAN(Date.valueOf(NGAYNHAN));
            scholarship.setSOTIEN(SOTIEN);
            scholarshipRepository.save(scholarship);
        }
        System.out.printf("Call API /createdataaaddusers list result: %s \n", GSON.toJson(scholarship));

        return "";
    }

}
