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
-- Name: people; Type: TABLE; Schema: public; Owner: Molly; Tablespace: 
--

CREATE TABLE people (
    id integer NOT NULL,
    name character varying,
    x_coordinate integer,
    y_coordinate integer,
    health integer
);


ALTER TABLE people OWNER TO "Molly";

--
-- Name: people_id_seq; Type: SEQUENCE; Schema: public; Owner: Molly
--

CREATE SEQUENCE people_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE people_id_seq OWNER TO "Molly";

--
-- Name: people_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Molly
--

ALTER SEQUENCE people_id_seq OWNED BY people.id;


--
-- Name: weapons; Type: TABLE; Schema: public; Owner: Molly; Tablespace: 
--

CREATE TABLE weapons (
    id integer NOT NULL,
    nameofweapon character varying,
    damage integer,
    numberofuses integer,
    broken boolean,
    person_id integer
);


ALTER TABLE weapons OWNER TO "Molly";

--
-- Name: weapons_id_seq; Type: SEQUENCE; Schema: public; Owner: Molly
--

CREATE SEQUENCE weapons_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE weapons_id_seq OWNER TO "Molly";

--
-- Name: weapons_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Molly
--

ALTER SEQUENCE weapons_id_seq OWNED BY weapons.id;


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Molly
--

ALTER TABLE ONLY people ALTER COLUMN id SET DEFAULT nextval('people_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Molly
--

ALTER TABLE ONLY weapons ALTER COLUMN id SET DEFAULT nextval('weapons_id_seq'::regclass);


--
-- Data for Name: people; Type: TABLE DATA; Schema: public; Owner: Molly
--

COPY people (id, name, x_coordinate, y_coordinate, health) FROM stdin;
\.


--
-- Name: people_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Molly
--

SELECT pg_catalog.setval('people_id_seq', 1, false);


--
-- Data for Name: weapons; Type: TABLE DATA; Schema: public; Owner: Molly
--

COPY weapons (id, nameofweapon, damage, numberofuses, broken, person_id) FROM stdin;
\.


--
-- Name: weapons_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Molly
--

SELECT pg_catalog.setval('weapons_id_seq', 1, false);


--
-- Name: people_pkey; Type: CONSTRAINT; Schema: public; Owner: Molly; Tablespace: 
--

ALTER TABLE ONLY people
    ADD CONSTRAINT people_pkey PRIMARY KEY (id);


--
-- Name: weapons_pkey; Type: CONSTRAINT; Schema: public; Owner: Molly; Tablespace: 
--

ALTER TABLE ONLY weapons
    ADD CONSTRAINT weapons_pkey PRIMARY KEY (id);


--
-- Name: public; Type: ACL; Schema: -; Owner: Molly
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM "Molly";
GRANT ALL ON SCHEMA public TO "Molly";
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

