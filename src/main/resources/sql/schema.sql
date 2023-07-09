create table player(id identity primary key, playerfname varchar(50), playerlname varchar(50), dob date, age int, position varchar(30));

create table team(id identity primary key, teamname varchar(50), playerid bigint, foreign key(playerid) references player(id));