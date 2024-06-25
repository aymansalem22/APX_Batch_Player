package com.bbva.eipi.batch.repository.imp;

import com.bbva.eipi.batch.repository.PlayerDao;
import com.bbva.eipi.dto.player.PlayerDTO;

import com.bbva.eipi.dto.player.PlayerProDTO;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;


import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class JdbcPlayerDao implements PlayerDao {



    public static final String INSERT_PLAYER =
            "INSERT INTO PLAYERS (player_id, last_name, first_name, iban, pos, year_of_birth, year_drafted) " +
                    "VALUES (:id, :lastName, :firstName, :iban, :position, :birthYear, :debutYear);";


    public static final String INSERT_PLAYER_PRO =
            "INSERT INTO PLAYER_PRO (PLAYER_PRO_id, last_name, first_name, iban, pos, year_of_birth, year_drafted, text) " +
                    "VALUES (:id, :lastName, :firstName, :iban, :position, :birthYear, :debutYear, :text);";

    public static final String DELETE_PLAYER =
            "DELETE FROM PLAYERS WHERE player_id = :id";


    public static final String INSERT_EMPLOYEE =
            "INSERT INTO Employees (employee_id, last_name, first_name, iban, pos, year_of_birth, year_drafted) " +
                    "VALUES (:id, :lastName, :firstName, :iban, :position, :birthYear, :debutYear);";

    public static final String INSERT_MANAGERS =
            "INSERT INTO Managers (manager_id, last_name, first_name, iban, pos, year_of_birth, year_drafted) " +
                    "VALUES (:id, :lastName, :firstName, :iban, :position, :birthYear, :debutYear);";

    public static final String DELETE_PLAYER_TABLE = "DELETE FROM PLAYERS";
    public static final String DELETE_EMPLOYEE = "DELETE FROM Employees";
    public static final String DELETE_Manager = "DELETE FROM Managers";
    public static final String DELETE_PLAYER_PRO_TABLE = "DELETE FROM PLAYER_PRO";
    private NamedParameterJdbcOperations namedParameterJdbcTemplate;

    @Override
    public void savePlayer(PlayerDTO player) {
        if (namedParameterJdbcTemplate == null) {
            System.out.println("namedParameterJdbcTemplate is null in savePlayer");
        }
       // getApplicationConfigurationServiceFactory();
       int a= namedParameterJdbcTemplate.update(INSERT_PLAYER, new BeanPropertySqlParameterSource(player));
        System.out.println("savePlayer "+a);
    }

    @Override
    public void savePlayerPro(PlayerProDTO player) {
        if (namedParameterJdbcTemplate == null) {
            System.out.println("namedParameterJdbcTemplate is null in savePlayerPRO");
        }

        int a= namedParameterJdbcTemplate.update(INSERT_PLAYER_PRO, new BeanPropertySqlParameterSource(player));
        System.out.println("savePlayerPro "+a);
    }

    @Override
    public void deletePlayerProStep(String id) {
        Map<String, String> ids = new HashMap<>();
        ids.put("id",id);
       int a= namedParameterJdbcTemplate.update(DELETE_PLAYER, ids);
        System.out.println("deletePlayerProStep "+a);
    }


    @Override
    public void deletePlayer() {
        if (namedParameterJdbcTemplate == null) {
            System.out.println("namedParameterJdbcTemplate is null in deletePlayer");
        }
        MapSqlParameterSource parameters = new MapSqlParameterSource();
       // namedParameterJdbcTemplate.update(DELETE_PLAYER_TABLE, parameters);
       // namedParameterJdbcTemplate.update(DELETE_Manager, parameters);
     //   namedParameterJdbcTemplate.update(DELETE_EMPLOYEE, parameters);
    //    namedParameterJdbcTemplate.update(DELETE_PLAYER_PRO_TABLE, parameters);
      // batchInsertPlayers();
       // batchInsertEmployees();
       // batchInsertPlayersPro();
    }




    public List<String> getAccounts() {
        String sql = "SELECT iban AS accounts FROM Employees " +
                "UNION " +
                "SELECT iban AS accounts FROM Managers";

        List<String> accounts = namedParameterJdbcTemplate.query(
                sql,
                (rs, rowNum) -> rs.getString("accounts")
        );

        // Log the retrieved accounts
        System.out.println("Retrieved accounts from database: " + accounts);

        return accounts;
    }

    public List<String> getAccountsInPlayerPRO() {
        String sql = "SELECT IBAN AS accounts FROM PLAYER_PRO";

        List<String> accounts = namedParameterJdbcTemplate.query(
                sql,
                (rs, rowNum) -> rs.getString("accounts")
        );

        // Log the retrieved accounts
        System.out.println("Retrieved accounts from database: " + accounts);

        return accounts;
    }


    public void batchInsertPlayers() {
        MapSqlParameterSource[] batch = new MapSqlParameterSource[4];

        batch[0] = new MapSqlParameterSource()
                .addValue("id", "M001")
                .addValue("lastName", "Smith")
                .addValue("firstName", "John")
                .addValue("iban", "GB82WEST12345698765432")
                .addValue("position", "Guard")
                .addValue("birthYear", 1990)
                .addValue("debutYear", 2012);

        batch[1] = new MapSqlParameterSource()
                .addValue("id", "M002")
                .addValue("lastName", "Johnson")
                .addValue("firstName", "Mike")
                .addValue("iban", "GB82WEST12345698765433")
                .addValue("position", "Forward")
                .addValue("birthYear", 1988)
                .addValue("debutYear", 2010);

        batch[2] = new MapSqlParameterSource()
                .addValue("id", "M003")
                .addValue("lastName", "Williams")
                .addValue("firstName", "Chris")
                .addValue("iban", "GB82WEST12345698765434")
                .addValue("position", "Center")
                .addValue("birthYear", 1991)
                .addValue("debutYear", 2011);

        batch[3] = new MapSqlParameterSource()
                .addValue("id", "M004")
                .addValue("lastName", "Brown")
                .addValue("firstName", "David")
                .addValue("iban", "GB82WEST12345698765435")
                .addValue("position", "Guard")
                .addValue("birthYear", 1993)
                .addValue("debutYear", 2013);

        namedParameterJdbcTemplate.batchUpdate(INSERT_PLAYER, batch);
    }

    public void batchInsertEmployees() {
        MapSqlParameterSource[] batch = new MapSqlParameterSource[4];

        batch[0] = new MapSqlParameterSource()
                .addValue("id", "E001")
                .addValue("lastName", "Brown")
                .addValue("firstName", "James")
                .addValue("iban", "GB82WEST12345698765448")
                .addValue("position", "Manager")
                .addValue("birthYear", 1985)
                .addValue("debutYear", 2007);

        batch[1] = new MapSqlParameterSource()
                .addValue("id", "E002")
                .addValue("lastName", "Williams")
                .addValue("firstName", "Chris")
                .addValue("iban", "GB82WEST12345698765487")
                .addValue("position", "Developer")
                .addValue("birthYear", 1992)
                .addValue("debutYear", 2014);

        batch[2] = new MapSqlParameterSource()
                .addValue("id", "E003")
                .addValue("lastName", "Johnson")
                .addValue("firstName", "Michael")
                .addValue("iban", "GB82WEST12345698765434")
                .addValue("position", "Analyst")
                .addValue("birthYear", 1990)
                .addValue("debutYear", 2012);

        batch[3] = new MapSqlParameterSource()
                .addValue("id", "E004")
                .addValue("lastName", "Davis")
                .addValue("firstName", "Steven")
                .addValue("iban", "GB82WEST12345698765436")
                .addValue("position", "Consultant")
                .addValue("birthYear", 1988)
                .addValue("debutYear", 2010);

        namedParameterJdbcTemplate.batchUpdate(INSERT_EMPLOYEE, batch);
    }


    public void batchInsertPlayersPro() {
        MapSqlParameterSource[] batch = new MapSqlParameterSource[4];

        batch[0] = new MapSqlParameterSource()
                .addValue("id", "P001")
                .addValue("lastName", "Smith")
                .addValue("firstName", "John")
                .addValue("iban", "GB82WEST12345698765436")
                .addValue("position", "Guard")
                .addValue("birthYear", 1990)
                .addValue("debutYear", 2012);

        batch[1] = new MapSqlParameterSource()
                .addValue("id", "P002")
                .addValue("lastName", "Johnson")
                .addValue("firstName", "Mike")
                .addValue("iban", "GB82WEST12345698765457")
                .addValue("position", "Forward")
                .addValue("birthYear", 1988)
                .addValue("debutYear", 2010);

        batch[2] = new MapSqlParameterSource()
                .addValue("id", "P003")
                .addValue("lastName", "Williams")
                .addValue("firstName", "Chris")
                .addValue("iban", "GB82WEST12345698765458")
                .addValue("position", "Center")
                .addValue("birthYear", 1991)
                .addValue("debutYear", 2011);

        batch[3] = new MapSqlParameterSource()
                .addValue("id", "P004")
                .addValue("lastName", "Brown")
                .addValue("firstName", "David")
                .addValue("iban", "GB82WEST12345698765459")
                .addValue("position", "Guard")
                .addValue("birthYear", 1993)
                .addValue("debutYear", 2013);

        namedParameterJdbcTemplate.batchUpdate(INSERT_PLAYER_PRO, batch);

    }



    public void setDataSource(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }
}


/*
INSERT INTO players (id, lastName, firstName, iban, position, birthYear, debutYear) VALUES
('M001', 'Smith', 'John', 'GB82WEST12345698765432', 'Guard', 1990, 2012),
('M002', 'Johnson', 'Mike', 'GB82WEST12345698765433', 'Forward', 1988, 2010),
('M003', 'Williams', 'Chris', 'GB82WEST12345698765434', 'Center', 1991, 2011),
('M004', 'Brown', 'David', 'GB82WEST12345698765435', 'Guard', 1993, 2013);


INSERT INTO employees (EMPLOYEE_ID , last_Name, first_Name, iban, pos, YEAR_OF_BIRTH , YEAR_DRAFTED) VALUES
('E001', 'Brown', 'James', 'GB82WEST12345698765436', 'Manager', 1985, 2007),
('E002', 'Williams', 'Chris', 'GB82WEST12345698765448', 'Developer', 1992, 2014),
('E003', 'Johnson', 'Michael', 'GB82WEST12345698765434', 'Analyst', 1990, 2012),
('E004', 'Davis', 'Steven', 'GB82WEST12345698765487', 'Consultant', 1988, 2010);

INSERT INTO PLAYER_PRO (PLAYER_PRO_ID , LAST_NAME , FIRST_NAME , iban, pos, YEAR_OF_BIRTH , YEAR_DRAFTED ) VALUES
('P001', 'Smith', 'John', 'GB82WEST12345698765436', 'Guard', 1990, 2012),
('P002', 'Johnson', 'Mike', 'GB82WEST12345698765457', 'Forward', 1988, 2010),
('P003', 'Williams', 'Chris', 'GB82WEST12345698765458', 'Center', 1991, 2011),
('P004', 'Brown', 'David', 'GB82WEST12345698765459', 'Guard', 1993, 2013);

 */