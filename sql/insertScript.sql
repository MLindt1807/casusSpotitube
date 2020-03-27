SET SQL_SAFE_UPDATES = 0;
delete from playlists;

insert into playlists(name,owner)
values ("Death metal", true),
("Pop",false);

delete from tracks;
insert into tracks(title,performer,duration,album,playcount,publicationDate,description,offlineAvailable)
values ("Ocean and a rock","Lisa Hannigan", 337,"Sea sew", null,null,null,false),
("So Long, Marianne","Leonard Cohen",546,"Songs of Leonard Cohen", null,null,null,false),
("One","Metallica",423, null, 37,"18-03-2001","Long version",true);



delete from tracksInPlaylists;
insert into tracksinplaylists(playlistID, trackID)
values ((select id from playlists where name like "Death metal") , (select id from tracks where title like "One")),
((select id from playlists where name like "Pop"),(select id from tracks where title like "So Long, Marianne"));
    
