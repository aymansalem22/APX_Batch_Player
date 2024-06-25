CREATE TABLE IF NOT EXISTS PLAYERS (
    player_id VARCHAR2(50),
    last_name VARCHAR2(100),
    first_name VARCHAR2(100),
    iban VARCHAR2(26),
    pos VARCHAR2(50),
    year_of_birth NUMBER,
    year_drafted NUMBER
);

CREATE TABLE IF NOT EXISTS EMPLOYEES (
    employee_id VARCHAR2(50),
    last_name VARCHAR2(100),
    iban VARCHAR2(26),
    first_name VARCHAR2(100),
    pos VARCHAR2(50),
    year_of_birth NUMBER,
    year_drafted NUMBER
);

CREATE TABLE IF NOT EXISTS MANAGERS (
    manager_id  VARCHAR2(50),
    last_name VARCHAR2(100),
    iban VARCHAR2(26),
    first_name VARCHAR2(100),
    pos VARCHAR2(50),
    year_of_birth NUMBER,
    year_drafted NUMBER
);

CREATE TABLE IF NOT EXISTS PLAYER_PRO (
    PLAYER_PRO_id  VARCHAR2(50),
    last_name VARCHAR2(100),
    iban VARCHAR2(26),
    first_name VARCHAR2(100),
    pos VARCHAR2(50),
    year_of_birth NUMBER,
    year_drafted NUMBER
);
