package com.meow.accounts_service.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "idmain", schema = "public")
public class IdMain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY,mappedBy = "idmain", cascade = CascadeType.ALL)
    IdTelegram idTelegram;
    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY,mappedBy = "idmain", cascade = CascadeType.ALL)
    IdEmail idEmail;
}
