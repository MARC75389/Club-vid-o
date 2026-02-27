package com.ESGIS.IRT2.Model;

import jakarta.persistence.*;
import lombok.Data;

@Table
@Entity
@Data
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_Location;
}
