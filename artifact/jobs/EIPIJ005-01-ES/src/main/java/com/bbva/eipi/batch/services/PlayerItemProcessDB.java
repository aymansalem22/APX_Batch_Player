package com.bbva.eipi.batch.services;

import com.bbva.eipi.batch.repository.PlayerDao;
import com.bbva.eipi.dto.player.PlayerDTO;
import com.bbva.eipi.dto.player.PlayerProDTO;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.web.client.RestTemplate;


import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Qualifier;
import java.util.List;


public class PlayerItemProcessDB implements ItemProcessor<PlayerDTO, PlayerProDTO>, StepExecutionListener {

    public PlayerItemProcessDB() {
    }

    public PlayerItemProcessDB(PlayerDao playerDao) {
        this.playerDao = playerDao;
    }

    private PlayerDao playerDao;

    RestTemplate restTemplate = new RestTemplate();

    private final List<String> accounts= playerDao.getAccounts();;
    private final List<String> stringListPlayer_PRO=playerDao.getAccountsInPlayerPRO();;

   // public PlayerItemProcessDB(PlayerDao playerDao) {

     //   this.playerDao = playerDao;
        // Initialize lists in the constructor
      //  this.accounts = playerDao.getAccounts();
       // this.stringListPlayer_PRO = playerDao.getAccountsInPlayerPRO();
  //  }

    //36 34 37
    @Override
    public PlayerProDTO process(PlayerDTO playerDTO) throws Exception {
        if (accounts.contains(playerDTO.getIban())) {
            if (stringListPlayer_PRO.contains(playerDTO.getIban())) {
                System.out.println("Sending email for duplicated iban: " + playerDTO.getIban());
                return null;
            } else {
                PlayerProDTO playerProDTO = getPlayerProDTO(playerDTO);
                playerDao.deletePlayerProStep(playerDTO.getId());
                return playerProDTO;
            }
        }
        return null;
    }

    private static PlayerProDTO getPlayerProDTO(PlayerDTO playerDTO) {
        PlayerProDTO playerProDTO = new PlayerProDTO();
        playerProDTO.setId(playerDTO.getId());
        playerProDTO.setIban(playerDTO.getIban());
        playerProDTO.setBirthYear(playerDTO.getBirthYear());
        playerProDTO.setPosition(playerDTO.getPosition());
        playerProDTO.setDebutYear(playerDTO.getDebutYear());
        playerProDTO.setFirstName(playerDTO.getFirstName());
        playerProDTO.setLastName(playerDTO.getLastName());
        return playerProDTO;
    }

    @Override
    public void beforeStep(StepExecution stepExecution) {
        // Initialize accounts and stringListPlayer_PRO before step starts
//        if (playerDao != null) {
//            accounts.clear();
//            accounts.addAll(playerDao.getAccounts());
//            stringListPlayer_PRO.clear();
//            stringListPlayer_PRO.addAll(playerDao.getAccountsInPlayerPRO());
//        }
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        // Clean up resources if needed
        return null;
    }

    public PlayerDao getPlayerDao() {
        return playerDao;
    }

    public void setPlayerDao(PlayerDao playerDao) {
        this.playerDao = playerDao;
    }
}
