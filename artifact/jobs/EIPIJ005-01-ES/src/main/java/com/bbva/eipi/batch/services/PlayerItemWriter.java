package com.bbva.eipi.batch.services;


import com.bbva.eipi.batch.repository.PlayerDao;
import com.bbva.eipi.dto.player.PlayerDTO;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

public class PlayerItemWriter implements ItemWriter<PlayerDTO>, StepExecutionListener {

    private PlayerDao playerDao;

    @Override
    public void write(List<? extends PlayerDTO> players) throws Exception {

        for(PlayerDTO player: players){
            playerDao.savePlayer(player);
            System.out.println("first step Database...."+player);
        }
    }

    public void setPlayerDao(PlayerDao playerDao) {
        this.playerDao = playerDao;
    }

    @Override
    public void beforeStep(StepExecution stepExecution) {

    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {

        ExitStatus exitStatus = stepExecution.getExitStatus();

        // You can now check the exitStatus based on your needs
        if (exitStatus.equals(ExitStatus.COMPLETED)) {
            System.out.println("Step completed successfully!");
        } else {
            System.out.println("Step failed with exit code: " + exitStatus.getExitCode());
        }

        return ExitStatus.COMPLETED; // You can return a different status based on your logic
    }
}
