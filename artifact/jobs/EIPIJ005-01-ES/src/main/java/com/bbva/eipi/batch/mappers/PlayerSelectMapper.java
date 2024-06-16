package com.bbva.eipi.batch.mappers;

import com.bbva.eipi.dto.player.PlayerDTO;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PlayerSelectMapper implements RowMapper<PlayerDTO> {

    @Override
    public PlayerDTO mapRow(ResultSet rs, int i) throws SQLException {
        PlayerDTO player = new PlayerDTO();
        player.setId(rs.getString("player_id"));
        player.setLastName(rs.getString("last_name"));
        player.setFirstName(rs.getString("first_name"));
        player.setPosition(rs.getString("pos"));
        player.setDebutYear(rs.getInt("year_drafted"));
        player.setBirthYear(rs.getInt("year_of_birth"));
        player.setIban(rs.getString("iban"));

        return player;
    }
}
