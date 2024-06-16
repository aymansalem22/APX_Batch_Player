package com.bbva.eipi.batch.services;




import com.bbva.eipi.dto.player.PlayerDTO;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlayerItemReader implements ItemReader<PlayerDTO>, StepExecutionListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(PlayerItemReader.class);


    private Resource resource;
    private BufferedReader br;
    private boolean isFileRead;

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    @Override
    public void beforeStep(StepExecution stepExecution) {
        // No need to implement beforeStep here due to lazy initialization
    }

    private void openFile() throws Exception {
        LOGGER.info("File path: {}", resource.getFilename());
        br = new BufferedReader(new InputStreamReader(resource.getInputStream()));
        isFileRead = true;
    }

    @Override
    public PlayerDTO read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if (!isFileRead) {
            openFile();
        }
        String line = br.readLine();
        if (line != null) {
            List<String> values = parseLineEipi(line);
            PlayerDTO player = new PlayerDTO();
            player.setId(values.get(0).trim());
            player.setLastName(values.get(1).trim());
            player.setFirstName(values.get(2).trim());
            player.setPosition(values.get(3).trim());
            player.setBirthYear(Integer.parseInt(values.get(4)));
            player.setDebutYear(Integer.parseInt(values.get(5)));
            player.setIban(values.get(6));
            return player;
        } else {
            // Close the BufferedReader at the end of the file
            br.close();
            isFileRead = false;
            return null;
        }
    }

    private List<String> parseLineEipi(String line) {
        String[] tokens = line.split(",");
        return Arrays.asList(tokens);
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