package org.wecancodeit.gamelibrary.Models;

import java.util.Collection;

import org.springframework.lang.NonNull;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "T_Publisher")
public class PublisherModel {

    @Id
    @GeneratedValue
    private long id;

    // The name of the publisher cannot be null
    @NonNull
    // The size of the name has a max and a min length of 5 characters
    @Size(max = 100, min = 5)
    // Build a unique name for the publisher cannot have duplicates
    @Column(length = 100, unique = true)
    private String name;

    // @OneToMany(mappedBy = "publisher")
    @OneToMany(mappedBy = "publisher", cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<BoardGameModel> games;

    public PublisherModel() {
    }

    public PublisherModel(@Size(max = 100, min = 5) String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    

    public String getName() {
        return name;
    }
    
    public Collection<BoardGameModel> getGames() {
        return games;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((games == null) ? 0 : games.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PublisherModel other = (PublisherModel) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (games == null) {
            if (other.games != null)
                return false;
        } else if (!games.equals(other.games))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "PublisherModel [id=" + id + ", name=" + name + "]";
    }


}
