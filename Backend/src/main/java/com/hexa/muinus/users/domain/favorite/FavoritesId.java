package com.hexa.muinus.users.domain.favorite;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FavoritesId implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer user;
    private Integer store;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FavoritesId that = (FavoritesId) o;
        return Objects.equals(user, that.user) && Objects.equals(store, that.store);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, store);
    }
}
