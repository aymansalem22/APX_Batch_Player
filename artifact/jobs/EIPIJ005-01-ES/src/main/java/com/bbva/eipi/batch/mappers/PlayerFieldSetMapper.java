package com.bbva.eipi.batch.mappers;


import com.bbva.eipi.dto.player.PlayerDTO;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;


public class PlayerFieldSetMapper implements FieldSetMapper<PlayerDTO> {

    @Override
    public PlayerDTO mapFieldSet(FieldSet fs) {

        if(fs == null){
            return null;
        }

        PlayerDTO player = new PlayerDTO();
        player.setId(fs.readString("ID"));
        player.setLastName(fs.readString("lastName"));
        player.setFirstName(fs.readString("firstName"));
        player.setPosition(fs.readString("position"));
        player.setDebutYear(fs.readInt("debutYear"));
        player.setBirthYear(fs.readInt("birthYear"));
        player.setIban(fs.readString("iban"));

        return player;
    }
}
