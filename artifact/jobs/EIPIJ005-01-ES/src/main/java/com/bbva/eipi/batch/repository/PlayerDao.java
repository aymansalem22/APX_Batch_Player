package com.bbva.eipi.batch.repository;


import com.bbva.eipi.dto.player.PlayerDTO;
import com.bbva.eipi.dto.player.PlayerProDTO;


import java.util.List;


public interface PlayerDao  {


    void savePlayer(PlayerDTO player);
    void deletePlayer();
    List<String> getAccounts();
    List<String> getAccountsInPlayerPRO();
    void savePlayerPro(PlayerProDTO player);
    void deletePlayerProStep(String iban);
}
