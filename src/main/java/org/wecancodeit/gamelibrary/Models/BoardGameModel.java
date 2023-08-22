package org.wecancodeit.gamelibrary.Models;

import org.springframework.lang.NonNull;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "T_boardgame")
public class BoardGameModel {

    @Id
    @GeneratedValue
    private long id;

    @NonNull
    @Size(max = 100, min = 5)
    // Makes all the names unique and sorted
    @Column(name = "gameName", length = 100, unique = true)
    private String name;

    @NonNull
    @Size(max = 500)
    @Column(length = 500)
    private String description;

    @NonNull
    @Size(max = 500)
    @Column(length = 500)
    private String imageUrl;

    @ManyToOne
    private PublisherModel publisher;

    public BoardGameModel() {

    }

    public BoardGameModel(@Size(max = 100, min = 5) String name, @Size(max = 500) String description,
            @Size(max = 500) String imageUrl, PublisherModel publisher) {
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.publisher = publisher;
    }



    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public PublisherModel getPublisher() {
        return publisher;
    }



    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((imageUrl == null) ? 0 : imageUrl.hashCode());
        result = prime * result + ((publisher == null) ? 0 : publisher.hashCode());
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
        BoardGameModel other = (BoardGameModel) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        if (imageUrl == null) {
            if (other.imageUrl != null)
                return false;
        } else if (!imageUrl.equals(other.imageUrl))
            return false;
        if (publisher == null) {
            if (other.publisher != null)
                return false;
        } else if (!publisher.equals(other.publisher))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "BoardGameModel [id=" + id + ", name=" + name + "]";
    }

}
