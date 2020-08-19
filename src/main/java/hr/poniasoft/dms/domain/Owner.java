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
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Entity
@Table(name="owners")
@Data
public class Owner {

    @Id
    @SequenceGenerator(name="pk_sequence",sequenceName="dms_id_seq", allocationSize=1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE,generator="pk_sequence")
    private long id;

    @Column
    @NotBlank(message = "Ime je obavezan podatak")
    private String firstName;

    @Column
    @NotBlank(message = "Prezime je obavezan podatak")
    private String lastName;

    @Column
    @NotBlank(message = "Adresa je obavezan podatak")
    private String address;

    @Column
    @NotBlank(message = "OIB je obavezan podatak")
    @Pattern(message = "Neispravan format OIB-a", regexp = "^[0-9]{11}$")
    private String oib;

    @Column
    private String telephoneNumber;

    @Column
    private String dogAddress;

    @OneToMany(
            mappedBy = "owner",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<DogOwner> dogs = new ArrayList<>();

    public void addDog(Dog dog) {
        DogOwner dogOwner = new DogOwner(dog, this);
        dogs.add(dogOwner);
        dog.getOwners().add(dogOwner);
    }

    public void removeDog(Dog dog) {
        for (Iterator<DogOwner> iterator = dogs.iterator();
             iterator.hasNext(); ) {
            DogOwner dogOwner = iterator.next();

            if (dogOwner.getOwner().equals(this) &&
                    dogOwner.getDog().equals(dog)) {
                iterator.remove();
                dogOwner.getDog().getOwners().remove(dogOwner);
                dogOwner.setDog(null);
                dogOwner.setOwner(null);
            }
        }
    }
}
