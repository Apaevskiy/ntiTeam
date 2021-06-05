-- CREATE DATABASE "lordsAndPlanets";
drop table if exists public.planets;
drop table if exists public.lords;


CREATE TABLE IF NOT EXISTS public.lords
(
    lordId SERIAL PRIMARY KEY,
    fullName  text NOT NULL,
    age INT
);
CREATE TABLE IF NOT EXISTS public.planets
(
    planetId SERIAL PRIMARY KEY,
    nameOfPlanet text NOT NULL,
    lordId INT default 0 REFERENCES public.lords (lordId)
);