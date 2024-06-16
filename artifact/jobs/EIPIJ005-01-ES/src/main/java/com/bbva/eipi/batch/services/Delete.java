package com.bbva.eipi.batch.services;

import com.bbva.eipi.batch.repository.PlayerDao;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class Delete implements Tasklet {

    private PlayerDao playerDao;

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
         playerDao.deletePlayer();
       // chunkContext.getStepContext().getStepExecution().getExecutionContext().putInt("deletedCount", deletedCount);
        return RepeatStatus.FINISHED;
    }

    public void setPlayerDao(PlayerDao playerDao) {
        this.playerDao = playerDao;
    }
}
