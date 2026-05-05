package com.jee.bdccensetspringmvc.sec.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class AppRole {
    @Id
    private String roleId;
    @Column(unique = true)
    private String role;
}
