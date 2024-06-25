package com.bbva.eipi.batch.repositoryentity;

import com.bbva.eipi.dto.player.PlayerDTO;

import javax.persistence.EntityManager;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class PlayerDAO extends JpaDAO<PlayerDTO> implements GenericDAO<PlayerDTO> {

    public PlayerDAO() {

        // TODO Auto-generated constructor stub
    }

    public PlayerDAO(EntityManager entityManager) {
        // TODO Auto-generated constructor stub
    }

//    @Override
//    public PlayerDTO create(Book book) {
//        book.setLastUpdateTime(new Date());
//        return super.create(book);
//    }
//
//    @Override
//    public PlayerDTO update(PlayerDTO playerDTO) {
//        playerDTO.setLastUpdateTime(new Date());
//        return super.update(book);
//    }


    @Override
    public PlayerDTO get(Object id) {
        return null;
    }

    @Override
    public void delete(Object id) {

    }

    @Override
    public List<PlayerDTO> listAll() {
        return Collections.emptyList();
    }

    @Override
    public long count() {
        return 0;
    }
}
