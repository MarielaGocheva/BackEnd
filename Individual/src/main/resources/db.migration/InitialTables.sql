CREATE TABLE if not exists user
(
    id         bigint NOT NULL AUTO_INCREMENT,
    fName      varchar(50),
    lName       varchar(50),
    email       varchar(50),
    role        varchar(50),
    salt        varchar(50),
    encryptedPass   varchar(50),
    PRIMARY KEY (id)
);

CREATE TABLE if not exists playlist
(
    id         bigint NOT NULL AUTO_INCREMENT,
    userId      bigint NOT NULL,
    duration    float(24),
    PRIMARY KEY (id),
    FOREIGN KEY (userId) REFERENCES user (id)
);

CREATE TABLE if not exists song
(
    id         bigint NOT NULL AUTO_INCREMENT,
    songUri     varchar(50) NOT NULL,
    artist      varchar(50),
    duration    float(24),
    imageUrl    varchar(50),
    PRIMARY KEY (id)
);

CREATE TABLE if not exists playlist_songs
(
    id      bigint NOT NULL AUTO_INCREMENT,
    playlistId  bigint NOT NULL,
    songId      bigint NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (playlistId) REFERENCES playlist (id),
    FOREIGN KEY (songId) REFERENCES song (id)
);
