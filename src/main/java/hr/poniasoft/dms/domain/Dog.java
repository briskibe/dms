package hr.poniasoft.dms.domain;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "dogs")
public class Dog {

    @Id
    @SequenceGenerator(name="pk_sequence",sequenceName="dms_id_seq", allocationSize=1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE,generator="pk_sequence")
    private long id;

    @Column
    @NotBlank(message = "Ime psa je obavezan podatak")
    private String name;

    @Column
    @NotBlank(message = "Opis je obavezan podatak")
    private String description;

    @Column(unique = true)
    @NotBlank(message = "Broj čipa je obavezan podatak")
    private String chipNumber;

    @Column
    private Boolean isDead;

    @Column
    private Date dateOfDying;

    @OneToMany(
            mappedBy = "dog",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<DogOwner> owners = new ArrayList<>();
}
