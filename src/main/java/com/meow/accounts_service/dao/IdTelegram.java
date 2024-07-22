package com.meow.accounts_service.dao;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "idtg", schema = "public")
public class IdTelegram {

    @Id
    Long idtg;
    @OneToOne(fetch = FetchType.LAZY,optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "idmain", referencedColumnName = "id")
    IdMain idmain;
}
