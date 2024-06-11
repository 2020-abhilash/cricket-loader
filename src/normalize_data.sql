-- Exploration Queries

select * from match_info;

select * from match_dates;
select * from match_teams;
select * from match_team_players;
select * from match_referees;
select * from match_player_of_match;
select * from match_registry;
select * from match_reserve_umpires;
select * from match_tv_umpires;
select * from match_umpires;

-- Executable Queries
drop table if exists match_dates;
drop table if exists match_teams;
drop table if exists match_team_players;
drop table if exists match_referees;
drop table if exists match_player_of_match;
drop table if exists match_registry;
drop table if exists match_reserve_umpires;
drop table if exists match_tv_umpires;

-- Create a table for match dates
create table match_dates as
select
    match_id,
    to_date(unnest(string_to_array(dates, '|')), 'YYYY-MM-DD') as date
from match_info;

-- Create a table for match teams
create table match_teams as
select
    match_id,
    unnest(string_to_array(teams, '|')) as team
from match_info;

-- Create a table for match team players
create table match_team_players as
select
    match_id, team, unnest(string_to_array(players, '!')) as player
from
(
    select
        match_id,
        split_part(team_and_players, ':', 1) as team,
        split_part(team_and_players, ':', 2) as players
    from
    (
        select
            match_id,
            unnest(string_to_array(players, '|')) as team_and_players
        from match_info
    )
);

-- Create a table for match referees
create table match_referees as
select
    match_id,
    unnest(string_to_array(match_referees, '|')) as referee
from match_info;

-- Create a table for match player of match
create table match_player_of_match as
select
    match_id,
    unnest(string_to_array(player_of_match, '|')) as player
from match_info;

-- Create a table for match registry
create table match_registry as
select
    match_id,
    split_part(registry, ':', 1) as person_id,
    split_part(registry, ':', 2) as name
from
(
    select
        match_id,
        unnest(string_to_array(registry, '|')) as registry
    from match_info
);

-- Create a table for match reserve umpires
create table match_reserve_umpires as
select
    match_id,
    unnest(string_to_array(reserve_umpire, '|')) as name
from match_info;

-- Create a table for tv umpires
create table match_tv_umpires as
select
    match_id,
    unnest(string_to_array(tv_umpires, '|')) as name
from match_info;

-- Create a table for match umpires
create table match_umpires as
select
    match_id,
    unnest(string_to_array(umpires, '|')) as name
from match_info;

-- Drop the columns from the match_info table
alter table match_info drop column version;
alter table match_info drop column dates;
alter table match_info drop column teams;
alter table match_info drop column players;
alter table match_info drop column match_referees;
alter table match_info drop column player_of_match;
alter table match_info drop column registry;
alter table match_info drop column reserve_umpire;
alter table match_info drop column tv_umpires;
alter table match_info drop column umpires;