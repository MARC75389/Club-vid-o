package com.ESGIS.IRT2.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Table
@Entity
@Data
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_Location;

    private Date dateDeLaLocation;
}
