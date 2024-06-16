package com.bbva.eipi.batch.services;


import com.bbva.eipi.batch.repository.PlayerDao;
import com.bbva.eipi.dto.player.PlayerDTO;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.InitializingBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class PlayerItemProcess implements ItemProcessor<PlayerDTO,PlayerDTO>, StepExecutionListener {

    public PlayerItemProcess() {
    }

    PlayerDao playerDao;
    List<String> data = Arrays.asList("GB82WEST12345698765469", "GB82WEST12345698765448");
    List<String> accounts;
    List<String> arrayAccounts;

    public PlayerItemProcess(PlayerDao playerDao) {
        this.playerDao = playerDao;
        this.accounts = playerDao.getAccounts();
        this.arrayAccounts = new ArrayList<>();
        this.arrayAccounts.addAll(accounts);
        this.arrayAccounts.addAll(data);
    }

    @Override
    public PlayerDTO process(PlayerDTO player) throws Exception {
        boolean exists = arrayAccounts.stream().anyMatch(p -> p.equals(player.getIban()));
        return exists ? player : null;
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

        return ExitStatus.COMPLETED;
    }
}