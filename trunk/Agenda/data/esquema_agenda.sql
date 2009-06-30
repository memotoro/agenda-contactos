--
-- PostgreSQL database dump
--

-- Started on 2009-06-03 22:09:28

SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

--
-- TOC entry 1621 (class 0 OID 0)
-- Dependencies: 5
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON SCHEMA public IS 'Standard public schema';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 1271 (class 1259 OID 17320)
-- Dependencies: 5
-- Name: celular; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE celular (
    id_celular integer NOT NULL,
    telefono_celular bigint,
    id_persona integer NOT NULL
);


ALTER TABLE public.celular OWNER TO postgres;

--
-- TOC entry 1272 (class 1259 OID 17322)
-- Dependencies: 1271 5
-- Name: celular_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE celular_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.celular_id_seq OWNER TO postgres;

--
-- TOC entry 1623 (class 0 OID 0)
-- Dependencies: 1272
-- Name: celular_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE celular_id_seq OWNED BY celular.id_celular;


--
-- TOC entry 1273 (class 1259 OID 17324)
-- Dependencies: 5
-- Name: correo; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE correo (
    id_correo integer NOT NULL,
    correo character varying(50) NOT NULL,
    id_persona integer NOT NULL
);


ALTER TABLE public.correo OWNER TO postgres;

--
-- TOC entry 1274 (class 1259 OID 17326)
-- Dependencies: 5 1273
-- Name: correo_id_correo_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE correo_id_correo_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.correo_id_correo_seq OWNER TO postgres;

--
-- TOC entry 1624 (class 0 OID 0)
-- Dependencies: 1274
-- Name: correo_id_correo_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE correo_id_correo_seq OWNED BY correo.id_correo;


--
-- TOC entry 1275 (class 1259 OID 17328)
-- Dependencies: 5
-- Name: persona; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE persona (
    id_persona integer NOT NULL,
    nombres character varying(50) NOT NULL,
    apellidos character varying(50) NOT NULL,
    telefono_fijo integer
);


ALTER TABLE public.persona OWNER TO postgres;

--
-- TOC entry 1276 (class 1259 OID 17330)
-- Dependencies: 5 1275
-- Name: persona_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE persona_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.persona_id_seq OWNER TO postgres;

--
-- TOC entry 1625 (class 0 OID 0)
-- Dependencies: 1276
-- Name: persona_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE persona_id_seq OWNED BY persona.id_persona;


--
-- TOC entry 1607 (class 2604 OID 17332)
-- Dependencies: 1272 1271
-- Name: id_celular; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE celular ALTER COLUMN id_celular SET DEFAULT nextval('celular_id_seq'::regclass);


--
-- TOC entry 1608 (class 2604 OID 17333)
-- Dependencies: 1274 1273
-- Name: id_correo; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE correo ALTER COLUMN id_correo SET DEFAULT nextval('correo_id_correo_seq'::regclass);


--
-- TOC entry 1609 (class 2604 OID 17334)
-- Dependencies: 1276 1275
-- Name: id_persona; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE persona ALTER COLUMN id_persona SET DEFAULT nextval('persona_id_seq'::regclass);


--
-- TOC entry 1611 (class 2606 OID 17336)
-- Dependencies: 1271 1271
-- Name: id_celular_pk; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY celular
    ADD CONSTRAINT id_celular_pk PRIMARY KEY (id_celular);


--
-- TOC entry 1613 (class 2606 OID 17338)
-- Dependencies: 1273 1273
-- Name: id_correo_pk; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY correo
    ADD CONSTRAINT id_correo_pk PRIMARY KEY (id_correo);


--
-- TOC entry 1615 (class 2606 OID 17340)
-- Dependencies: 1275 1275
-- Name: id_persona_pk; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY persona
    ADD CONSTRAINT id_persona_pk PRIMARY KEY (id_persona);


--
-- TOC entry 1616 (class 2606 OID 17341)
-- Dependencies: 1614 1275 1271
-- Name: id_persona_fk1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY celular
    ADD CONSTRAINT id_persona_fk1 FOREIGN KEY (id_persona) REFERENCES persona(id_persona);


--
-- TOC entry 1617 (class 2606 OID 17346)
-- Dependencies: 1614 1275 1273
-- Name: id_persona_fk2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY correo
    ADD CONSTRAINT id_persona_fk2 FOREIGN KEY (id_persona) REFERENCES persona(id_persona);


--
-- TOC entry 1622 (class 0 OID 0)
-- Dependencies: 5
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2009-06-03 22:09:28

--
-- PostgreSQL database dump complete
--

