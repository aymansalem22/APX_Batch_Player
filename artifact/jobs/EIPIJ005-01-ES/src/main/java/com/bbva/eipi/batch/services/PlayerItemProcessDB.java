package com.bbva.eipi.batch.services;

import com.bbva.eipi.batch.mail.Mail;
import com.bbva.eipi.batch.repository.PlayerDao;
import com.bbva.eipi.dto.player.PlayerDTO;
import com.bbva.eipi.dto.player.PlayerProDTO;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.List;


public class PlayerItemProcessDB implements ItemProcessor<PlayerDTO, PlayerProDTO>, StepExecutionListener {


    private PlayerDao playerDao;
    private JavaMailSender mailSender;


    private List<String> accounts = new ArrayList<>();
    private List<String> stringListPlayer_PRO = new ArrayList<>();
    List<String> recipients = new ArrayList<>();
    List<String> recipients2 = new ArrayList<>();

    //36 34 37
    @Override
    public PlayerProDTO process(PlayerDTO playerDTO) throws Exception {
        if (accounts.contains(playerDTO.getIban())) {
            if (stringListPlayer_PRO.contains(playerDTO.getIban())) {
                System.out.println("Sending email for duplicated iban: " + playerDTO.getIban());
                String message = "Sending email for duplicated iban " + playerDTO.getIban();
                recipients.add("s.aymansalem@gmail.com");
                int i=0;
                recipients2.add(i++,playerDTO.getIban());


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
        if (playerDao != null) {
            accounts = playerDao.getAccounts();
            stringListPlayer_PRO = playerDao.getAccountsInPlayerPRO();
        }
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {

        try {
            sendEmail(new Mail(recipients, "Iban_5", "Sending email for duplicated iban "+recipients2));
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public PlayerDao getPlayerDao() {
        return playerDao;
    }


    public void setPlayerDao(PlayerDao playerDao) {
        this.playerDao = playerDao;
        //initializeLists();
    }

    private void initializeLists() {
        // Initialize accounts and stringListPlayer_PRO after playerDao is injected
        if (playerDao != null) {
            accounts = playerDao.getAccounts();
            stringListPlayer_PRO = playerDao.getAccountsInPlayerPRO();
        }
        System.out.println("list of accounts is : " + accounts);
        System.out.println("list of stringListPlayer_PRO: " + stringListPlayer_PRO);
    }

    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendEmail(Mail mail) throws MessagingException {
        mail.setFrom("elgam3a.portal@gmail.com");

        List<String> recipients = mail.getTo();

        for (String recipient : recipients) {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);

            helper.setSubject(mail.getSubject());
            helper.setFrom(mail.getFrom());
            helper.setTo(recipient);

            boolean html = true;
            helper.setText(mail.getBody(), html);

            mailSender.send(message);
        }
    }
}
