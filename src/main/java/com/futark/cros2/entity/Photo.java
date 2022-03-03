package com.futark.cros2.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Lob
//    @Column(length=100000)
//    private byte[] photo;

    @Lob
    @Type(type = "org.hibernate.type.BinaryType")
    private byte[] photo;

//    @Lob
//    @Column(name = "photo", columnDefinition="BLOB")
//    private byte[] photo;
}
