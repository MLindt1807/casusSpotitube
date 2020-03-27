create database Spotitube;
use Spotitube;

create table Playlists(
id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
name varchar(30),
owner boolean
);

create table Tracks(
id int(6) unsigned AUTO_increment Primary key,
title varchar(30) not null,
performer varchar(30) not null,
duration bigint not null,
album varchar(30) not null,
playcount int(6) null,
publicationDate varchar(10) null,
description varchar(60) null,
offlineAvailable boolean null
);

create table TracksInPlaylists(
playlistID int(6) unsigned not null,
trackID int(6) unsigned not null,
primary key(playlistID, trackID),
FOREIGN KEY (playlistID) REFERENCES Playlists(id),
FOREIGN KEY (trackID) REFERENCES Tracks(id)
);
