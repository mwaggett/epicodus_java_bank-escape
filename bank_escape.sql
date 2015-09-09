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
-- Name: person; Type: TABLE; Schema: public; Owner: sandimckendrick; Tablespace: 
--

CREATE TABLE person (
    id integer NOT NULL,
    weapon_id integer
);


ALTER TABLE person OWNER TO sandimckendrick;

--
-- Name: person_id_seq; Type: SEQUENCE; Schema: public; Owner: sandimckendrick
--

CREATE SEQUENCE person_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE person_id_seq OWNER TO sandimckendrick;

--
-- Name: person_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: sandimckendrick
--

ALTER SEQUENCE person_id_seq OWNED BY person.id;


--
-- Name: weapons; Type: TABLE; Schema: public; Owner: sandimckendrick; Tablespace: 
--

CREATE TABLE weapons (
    id integer NOT NULL,
    damage integer,
    name_of_weapon character varying,
    number_of_uses integer,
    broken boolean
);


ALTER TABLE weapons OWNER TO sandimckendrick;

--
-- Name: weapons_id_seq; Type: SEQUENCE; Schema: public; Owner: sandimckendrick
--

CREATE SEQUENCE weapons_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE weapons_id_seq OWNER TO sandimckendrick;

--
-- Name: weapons_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: sandimckendrick
--

ALTER SEQUENCE weapons_id_seq OWNED BY weapons.id;


--
-- Name: weapons_persons; Type: TABLE; Schema: public; Owner: sandimckendrick; Tablespace: 
--

CREATE TABLE weapons_persons (
    id integer NOT NULL,
    weapon_id integer,
    person_id integer
);


ALTER TABLE weapons_persons OWNER TO sandimckendrick;

--
-- Name: weapons_persons_id_seq; Type: SEQUENCE; Schema: public; Owner: sandimckendrick
--

CREATE SEQUENCE weapons_persons_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE weapons_persons_id_seq OWNER TO sandimckendrick;

--
-- Name: weapons_persons_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: sandimckendrick
--

ALTER SEQUENCE weapons_persons_id_seq OWNED BY weapons_persons.id;


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: sandimckendrick
--

ALTER TABLE ONLY person ALTER COLUMN id SET DEFAULT nextval('person_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: sandimckendrick
--

ALTER TABLE ONLY weapons ALTER COLUMN id SET DEFAULT nextval('weapons_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: sandimckendrick
--

ALTER TABLE ONLY weapons_persons ALTER COLUMN id SET DEFAULT nextval('weapons_persons_id_seq'::regclass);


--
-- Data for Name: person; Type: TABLE DATA; Schema: public; Owner: sandimckendrick
--

COPY person (id, weapon_id) FROM stdin;
\.


--
-- Name: person_id_seq; Type: SEQUENCE SET; Schema: public; Owner: sandimckendrick
--

SELECT pg_catalog.setval('person_id_seq', 1, false);


--
-- Data for Name: weapons; Type: TABLE DATA; Schema: public; Owner: sandimckendrick
--

COPY weapons (id, damage, name_of_weapon, number_of_uses, broken) FROM stdin;
\.


--
-- Name: weapons_id_seq; Type: SEQUENCE SET; Schema: public; Owner: sandimckendrick
--

SELECT pg_catalog.setval('weapons_id_seq', 1, false);


--
-- Data for Name: weapons_persons; Type: TABLE DATA; Schema: public; Owner: sandimckendrick
--

COPY weapons_persons (id, weapon_id, person_id) FROM stdin;
\.


--
-- Name: weapons_persons_id_seq; Type: SEQUENCE SET; Schema: public; Owner: sandimckendrick
--

SELECT pg_catalog.setval('weapons_persons_id_seq', 1, false);


--
-- Name: person_pkey; Type: CONSTRAINT; Schema: public; Owner: sandimckendrick; Tablespace: 
--

ALTER TABLE ONLY person
    ADD CONSTRAINT person_pkey PRIMARY KEY (id);


--
-- Name: weapons_persons_pkey; Type: CONSTRAINT; Schema: public; Owner: sandimckendrick; Tablespace: 
--

ALTER TABLE ONLY weapons_persons
    ADD CONSTRAINT weapons_persons_pkey PRIMARY KEY (id);


--
-- Name: weapons_pkey; Type: CONSTRAINT; Schema: public; Owner: sandimckendrick; Tablespace: 
--

ALTER TABLE ONLY weapons
    ADD CONSTRAINT weapons_pkey PRIMARY KEY (id);


--
-- Name: public; Type: ACL; Schema: -; Owner: sandimckendrick
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM sandimckendrick;
GRANT ALL ON SCHEMA public TO sandimckendrick;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

