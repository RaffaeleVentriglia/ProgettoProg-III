-- unica tabella di cui abbiamo bisogno
CREATE TABLE player (
    username VARCHAR(50) NOT NULL,
    count INTEGER NOT NULL,
    PRIMARY KEY(username, count)
);

-- valori inseriti per fare dei test
INSERT INTO player VALUES ("Raffaele", 5938);
INSERT INTO player VALUES ("Giuseppe", 3928);
INSERT INTO player VALUES ("Nicola", 2056);
INSERT INTO player VALUES ("Salvatore", 1027);
INSERT INTO player VALUES ("Giovanni", 250);