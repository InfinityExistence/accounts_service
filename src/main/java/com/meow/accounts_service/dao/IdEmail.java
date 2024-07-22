package com.meow.accounts_service.dao;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "idcr", schema = "public")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IdEmail {

    @Id
    @Column(name = "email")
    String email;
    String password;
    @OneToOne(fetch = FetchType.LAZY,optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "idmain", referencedColumnName = "id")
    IdMain idmain;
}
