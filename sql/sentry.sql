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
-- Name: sentry; Type: SCHEMA; Schema: -; Owner: sentry
--

CREATE SCHEMA sentry;


ALTER SCHEMA sentry OWNER TO sentry;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = sentry, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: hero; Type: TABLE; Schema: sentry; Owner: sentry; Tablespace: 
--

CREATE TABLE hero (
    id integer NOT NULL,
    paragonchd integer,
    name text NOT NULL,
    paragonias integer,
    dpselite numeric,
    profile integer NOT NULL,
    paragoncc integer,
    hero integer NOT NULL,
    dpsnonelite numeric,
    paragoncdr integer
);


ALTER TABLE sentry.hero OWNER TO sentry;

--
-- Name: hero_id_seq; Type: SEQUENCE; Schema: sentry; Owner: sentry
--

CREATE SEQUENCE hero_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE sentry.hero_id_seq OWNER TO sentry;

--
-- Name: hero_id_seq; Type: SEQUENCE OWNED BY; Schema: sentry; Owner: sentry
--

ALTER SEQUENCE hero_id_seq OWNED BY hero.id;


--
-- Name: profile; Type: TABLE; Schema: sentry; Owner: sentry; Tablespace: 
--

CREATE TABLE profile (
    id integer NOT NULL,
    tag integer NOT NULL,
    realm text NOT NULL,
    profile text NOT NULL
);


ALTER TABLE sentry.profile OWNER TO sentry;

--
-- Name: profile_id_seq; Type: SEQUENCE; Schema: sentry; Owner: sentry
--

CREATE SEQUENCE profile_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE sentry.profile_id_seq OWNER TO sentry;

--
-- Name: profile_id_seq; Type: SEQUENCE OWNED BY; Schema: sentry; Owner: sentry
--

ALTER SEQUENCE profile_id_seq OWNED BY profile.id;


--
-- Name: id; Type: DEFAULT; Schema: sentry; Owner: sentry
--

ALTER TABLE ONLY hero ALTER COLUMN id SET DEFAULT nextval('hero_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: sentry; Owner: sentry
--

ALTER TABLE ONLY profile ALTER COLUMN id SET DEFAULT nextval('profile_id_seq'::regclass);


--
-- Data for Name: hero; Type: TABLE DATA; Schema: sentry; Owner: sentry
--

COPY hero (id, paragonchd, name, paragonias, dpselite, profile, paragoncc, hero, dpsnonelite, paragoncdr) FROM stdin;
\.


--
-- Name: hero_id_seq; Type: SEQUENCE SET; Schema: sentry; Owner: sentry
--

SELECT pg_catalog.setval('hero_id_seq', 1, false);


--
-- Data for Name: profile; Type: TABLE DATA; Schema: sentry; Owner: sentry
--

COPY profile (id, tag, realm, profile) FROM stdin;
1	1416	US	dawg6
\.


--
-- Name: profile_id_seq; Type: SEQUENCE SET; Schema: sentry; Owner: sentry
--

SELECT pg_catalog.setval('profile_id_seq', 1, true);


--
-- Name: hero_pkey; Type: CONSTRAINT; Schema: sentry; Owner: sentry; Tablespace: 
--

ALTER TABLE ONLY hero
    ADD CONSTRAINT hero_pkey PRIMARY KEY (id);


--
-- Name: hero_profile_key; Type: CONSTRAINT; Schema: sentry; Owner: sentry; Tablespace: 
--

ALTER TABLE ONLY hero
    ADD CONSTRAINT hero_profile_key UNIQUE (hero, profile);


--
-- Name: profile_battletag_key; Type: CONSTRAINT; Schema: sentry; Owner: sentry; Tablespace: 
--

ALTER TABLE ONLY profile
    ADD CONSTRAINT profile_battletag_key UNIQUE (realm, tag, profile);


--
-- Name: profile_pkey; Type: CONSTRAINT; Schema: sentry; Owner: sentry; Tablespace: 
--

ALTER TABLE ONLY profile
    ADD CONSTRAINT profile_pkey PRIMARY KEY (id);


--
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

