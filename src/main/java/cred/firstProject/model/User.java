package cred.firstProject.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer id;

    public String name;

    @JsonFormat(pattern = "dd/MM/yyyy")
    public Date dob;

    @Column(unique = true)
    public Integer mobile;

    @Column(unique = true)
    public String emailId;

    public String address;
}
