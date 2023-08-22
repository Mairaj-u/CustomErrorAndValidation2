package com.msq.CustomErrorAndValidation.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="USER_TBL")
@NoArgsConstructor
@AllArgsConstructor(staticName = "build")
@Setter
@Getter
@Data
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId;
    private  String name;
    private String email;
    private  String mobile;
    private String gender;
    private int age;
    private String nationality;


}
