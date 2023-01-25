-- unica tabella di cui abbiamo bisogno
CREATE TABLE player (
    username VARCHAR(50) NOT NULL,
    count VARCHAR(25) NOT NULL,
    PRIMARY KEY(username, count)
);

-- valori inseriti per fare dei test
INSERT INTO player VALUES ("Raffaele", 10000);
INSERT INTO player VALUES ("Giuseppe", 10200);
INSERT INTO player VALUES ("Nicola", 16000);
INSERT INTO player VALUES ("Salvatore", 10010);
INSERT INTO player VALUES ("Giovanni", 10001);