package hr.poniasoft.dms.domain.compositeKeys;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
public class DogsOwnersId implements Serializable {

    @Column(name = "dog_id")
    private Long dogId;

    @Column(name = "owner_id")
    private Long ownerId;

    private DogsOwnersId() {}

    public DogsOwnersId(Long dogId, Long ownerId) {
        this.dogId = dogId;
        this.ownerId = ownerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        DogsOwnersId that = (DogsOwnersId) o;
        return Objects.equals(dogId, that.dogId) &&
                Objects.equals(ownerId, that.ownerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dogId, ownerId);
    }
}
