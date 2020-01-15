package com.masschip.jh.enties;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;

import javax.jws.soap.SOAPBinding;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.security.Timestamp;

@Setter
@Getter
@Entity
@Table(name = "persistent_logins")
public class Persistentlogins {


    @Length(max = 64)
    @NotNull
    private String   username;

    @Length(max = 64)
    @Id
    @NotNull
    private String series;

    @Length(max = 64)
    @NotNull
    private String token;

    @NotNull
    private Timestamp last_used;


}
