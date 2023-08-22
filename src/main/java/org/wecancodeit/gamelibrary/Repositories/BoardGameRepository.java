package org.wecancodeit.gamelibrary.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.wecancodeit.gamelibrary.Models.BoardGameModel;

public interface BoardGameRepository extends CrudRepository<BoardGameModel, Long>{

    //Search for board game name
    BoardGameModel findByName(String name);
    
}
