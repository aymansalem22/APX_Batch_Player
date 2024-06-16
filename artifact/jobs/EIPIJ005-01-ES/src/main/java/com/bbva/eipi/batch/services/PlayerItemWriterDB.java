package com.bbva.eipi.batch.services;

import com.bbva.eipi.batch.repository.PlayerDao;
import com.bbva.eipi.dto.player.PlayerDTO;
import com.bbva.eipi.dto.player.PlayerProDTO;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

public class PlayerItemWriterDB implements ItemWriter<PlayerProDTO> {

    private PlayerDao playerDao;



    @Override
    public void write(List<? extends PlayerProDTO> playerProDTO) throws Exception {
        for(PlayerProDTO player: playerProDTO){
            playerDao.savePlayerPro(player);

        }
    }
}
