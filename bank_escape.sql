--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: people; Type: TABLE; Schema: public; Owner: Guest; Tablespace: 
--

CREATE TABLE people (
    id integer NOT NULL,
    name character varying,
    x_coordinate integer,
    y_coordinate integer,
    health integer
);


ALTER TABLE people OWNER TO "Guest";

--
-- Name: people_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE people_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE people_id_seq OWNER TO "Guest";

--
-- Name: people_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE people_id_seq OWNED BY people.id;


--
-- Name: weapon_locations; Type: TABLE; Schema: public; Owner: Guest; Tablespace: 
--

CREATE TABLE weapon_locations (
    id integer NOT NULL,
    weapon_id integer,
    person_id integer
);


ALTER TABLE weapon_locations OWNER TO "Guest";

--
-- Name: weapon_locations_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE weapon_locations_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE weapon_locations_id_seq OWNER TO "Guest";

--
-- Name: weapon_locations_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE weapon_locations_id_seq OWNED BY weapon_locations.id;


--
-- Name: weapons; Type: TABLE; Schema: public; Owner: Guest; Tablespace: 
--

CREATE TABLE weapons (
    id integer NOT NULL,
    nameofweapon character varying,
    damage integer,
    person_id integer
);


ALTER TABLE weapons OWNER TO "Guest";

--
-- Name: weapons_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE weapons_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE weapons_id_seq OWNER TO "Guest";

--
-- Name: weapons_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE weapons_id_seq OWNED BY weapons.id;


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY people ALTER COLUMN id SET DEFAULT nextval('people_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY weapon_locations ALTER COLUMN id SET DEFAULT nextval('weapon_locations_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY weapons ALTER COLUMN id SET DEFAULT nextval('weapons_id_seq'::regclass);


--
-- Data for Name: people; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY people (id, name, x_coordinate, y_coordinate, health) FROM stdin;
1	Bad Guy Mike	133	124	95
2	Bad Guy Jake	224	58	32
3	John	141	64	92
\.


--
-- Name: people_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('people_id_seq', 3, true);


--
-- Data for Name: weapon_locations; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY weapon_locations (id, weapon_id, person_id) FROM stdin;
\.


--
-- Name: weapon_locations_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('weapon_locations_id_seq', 1, false);


--
-- Data for Name: weapons; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY weapons (id, nameofweapon, damage, person_id) FROM stdin;
\.


--
-- Name: weapons_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('weapons_id_seq', 1, false);


--
-- Name: people_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest; Tablespace: 
--

ALTER TABLE ONLY people
    ADD CONSTRAINT people_pkey PRIMARY KEY (id);


--
-- Name: weapon_locations_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest; Tablespace: 
--

ALTER TABLE ONLY weapon_locations
    ADD CONSTRAINT weapon_locations_pkey PRIMARY KEY (id);


--
-- Name: weapons_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest; Tablespace: 
--

ALTER TABLE ONLY weapons
    ADD CONSTRAINT weapons_pkey PRIMARY KEY (id);


--
-- Name: public; Type: ACL; Schema: -; Owner: epicodus
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM epicodus;
GRANT ALL ON SCHEMA public TO epicodus;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

