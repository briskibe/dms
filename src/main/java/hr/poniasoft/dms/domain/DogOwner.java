package hr.poniasoft.dms.domain;

import hr.poniasoft.dms.domain.compositeKeys.DogsOwnersId;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import java.util.Date;

@Entity(name = "DogOwner")
@Table(
        name = "dog_owners",
        indexes = {
                @Index(name = "idx_dog", columnList = "dog_id"),
                @Index(name = "idx_owner", columnList = "owner_id")
        }
)
@Data
public class DogOwner {

    @EmbeddedId
    private DogsOwnersId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("dogId")
    private Dog dog;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("ownerId")
    private Owner owner;

    @Column
    private Date dateOfAdoption;

    // prebacivanje vlasnistva
    @Column
    private Date dateOfTransfer;

    @Column
    private Boolean returned;

    @Column
    private String areaOfAdoption;

    public DogOwner(Dog dog, Owner owner) {
        this.dog = dog;
        this.owner = owner;
        this.id = new DogsOwnersId(dog.getId(), owner.getId());
    }
}
