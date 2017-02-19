CONNECT 'jdbc:derby:Movies;create=true'; 

CREATE TABLE MOVIE (MOVIE_ID int not null primary key GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), 
                    MOVIE_NAME varchar(100), RATED varchar(10));

CREATE TABLE MOVIE_REVIEW (REVIEW_ID int not null primary key GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), 
                    MOVIE_ID int, REVIEW varchar(500), STARS int);
INSERT
INTO
MOVIE(MOVIE_NAME,
RATED)
values('Divergent', 'PG-13');

INSERT
INTO
MOVIE(MOVIE_NAME,
RATED)
values('Muppets Most Wanted', 'PG');

INSERT
INTO
MOVIE(MOVIE_NAME,
RATED)
values('The LEGO Movie', 'PG');

INSERT
INTO
MOVIE(MOVIE_NAME,
RATED)
values('Saving Mr. Banks', 'PG-13');

INSERT
INTO
MOVIE(MOVIE_NAME,
RATED)
values('Her', 'R');
