package com.tarashluhsko.dyplom.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tarashluhsko.dyplom.model.security.DataEncoderTool;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Data
@MappedSuperclass
public class User  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native", strategy = "native")
    protected Long id;
    @Column(name = "email")
    protected String email;
    @Column(name = "password")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    protected String password;
    @Column(name = "role")
    protected String role;

    @Transient
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role));
    }

    public void encryptPassword() {
        this.password = DataEncoderTool.encodeData(this.password);
    }
}
