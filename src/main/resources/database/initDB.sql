-- CREATE DATABASE "lordsAndPlanets";
drop table if exists public.planets;
drop table if exists public.lords;


CREATE TABLE IF NOT EXISTS public.lords
(
    lordId SERIAL PRIMARY KEY,
    nameOfLord  text NOT NULL
);
CREATE TABLE IF NOT EXISTS public.planets
(
    planetId SERIAL PRIMARY KEY,
    nameOfPlanet text NOT NULL,
    lordId INT REFERENCES public.lords (lordId)
);