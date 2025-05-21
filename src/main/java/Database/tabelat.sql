--
-- PostgreSQL database dump
--

-- Dumped from database version 17.2
-- Dumped by pg_dump version 17.2

-- Started on 2025-05-21 23:57:46

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 226 (class 1259 OID 16922)
-- Name: adresa; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.adresa (
    id integer NOT NULL,
    rruga character varying(100) NOT NULL,
    numri integer NOT NULL,
    kodi_postar integer NOT NULL
);


ALTER TABLE public.adresa OWNER TO postgres;

--
-- TOC entry 225 (class 1259 OID 16921)
-- Name: adresa_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.adresa_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.adresa_id_seq OWNER TO postgres;

--
-- TOC entry 5032 (class 0 OID 0)
-- Dependencies: 225
-- Name: adresa_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.adresa_id_seq OWNED BY public.adresa.id;


--
-- TOC entry 236 (class 1259 OID 17135)
-- Name: fshati; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.fshati (
    id integer NOT NULL,
    emri character varying(100) NOT NULL,
    komuna_id integer NOT NULL
);


ALTER TABLE public.fshati OWNER TO postgres;

--
-- TOC entry 235 (class 1259 OID 17134)
-- Name: fshati_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.fshati_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.fshati_id_seq OWNER TO postgres;

--
-- TOC entry 5033 (class 0 OID 0)
-- Dependencies: 235
-- Name: fshati_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.fshati_id_seq OWNED BY public.fshati.id;


--
-- TOC entry 224 (class 1259 OID 16898)
-- Name: kodi_postar; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.kodi_postar (
    id integer NOT NULL,
    kodi character varying(20) NOT NULL,
    nenregjioni character varying(100),
    regjioni character varying(100),
    komuna_id integer
);


ALTER TABLE public.kodi_postar OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 16897)
-- Name: kodi_postar_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.kodi_postar_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.kodi_postar_id_seq OWNER TO postgres;

--
-- TOC entry 5034 (class 0 OID 0)
-- Dependencies: 223
-- Name: kodi_postar_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.kodi_postar_id_seq OWNED BY public.kodi_postar.id;


--
-- TOC entry 220 (class 1259 OID 16879)
-- Name: komuna; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.komuna (
    id integer NOT NULL,
    emri character varying(100) NOT NULL
);


ALTER TABLE public.komuna OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 16878)
-- Name: komuna_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.komuna_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.komuna_id_seq OWNER TO postgres;

--
-- TOC entry 5035 (class 0 OID 0)
-- Dependencies: 219
-- Name: komuna_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.komuna_id_seq OWNED BY public.komuna.id;


--
-- TOC entry 238 (class 1259 OID 17162)
-- Name: lagjja; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.lagjja (
    id integer NOT NULL,
    emri character varying(100) NOT NULL,
    komuna_id integer NOT NULL,
    siperfaqja double precision,
    pershkrimi text,
    statusi_zyrtar boolean DEFAULT false
);


ALTER TABLE public.lagjja OWNER TO postgres;

--
-- TOC entry 237 (class 1259 OID 17161)
-- Name: lagjja_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.lagjja_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.lagjja_id_seq OWNER TO postgres;

--
-- TOC entry 5036 (class 0 OID 0)
-- Dependencies: 237
-- Name: lagjja_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.lagjja_id_seq OWNED BY public.lagjja.id;


--
-- TOC entry 228 (class 1259 OID 16977)
-- Name: objekti; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.objekti (
    id integer NOT NULL,
    tipi_objektit_id integer NOT NULL,
    emri character varying(100) NOT NULL,
    rruga_id integer NOT NULL,
    numri_nderteses character varying(20),
    koordinatat_gps character varying(100),
    kodi_postar_id integer NOT NULL
);


ALTER TABLE public.objekti OWNER TO postgres;

--
-- TOC entry 227 (class 1259 OID 16976)
-- Name: objekti_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.objekti_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.objekti_id_seq OWNER TO postgres;

--
-- TOC entry 5037 (class 0 OID 0)
-- Dependencies: 227
-- Name: objekti_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.objekti_id_seq OWNED BY public.objekti.id;


--
-- TOC entry 222 (class 1259 OID 16886)
-- Name: qyteti; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.qyteti (
    id integer NOT NULL,
    emri character varying(100) NOT NULL,
    komuna_id integer NOT NULL
);


ALTER TABLE public.qyteti OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 16885)
-- Name: qyteti_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.qyteti_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.qyteti_id_seq OWNER TO postgres;

--
-- TOC entry 5038 (class 0 OID 0)
-- Dependencies: 221
-- Name: qyteti_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.qyteti_id_seq OWNED BY public.qyteti.id;


--
-- TOC entry 234 (class 1259 OID 17117)
-- Name: recent_searches; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.recent_searches (
    id integer NOT NULL,
    user_id integer,
    adresa_id integer,
    search_time timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);


ALTER TABLE public.recent_searches OWNER TO postgres;

--
-- TOC entry 233 (class 1259 OID 17116)
-- Name: recent_searches_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.recent_searches_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.recent_searches_id_seq OWNER TO postgres;

--
-- TOC entry 5039 (class 0 OID 0)
-- Dependencies: 233
-- Name: recent_searches_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.recent_searches_id_seq OWNED BY public.recent_searches.id;


--
-- TOC entry 240 (class 1259 OID 17177)
-- Name: rruga; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.rruga (
    id integer NOT NULL,
    emri character varying(100) NOT NULL,
    komuna_id integer NOT NULL,
    qyteti_id integer,
    fshati_id integer,
    lagjja_id integer,
    CONSTRAINT rruga_check CHECK ((((qyteti_id IS NOT NULL) AND (fshati_id IS NULL)) OR ((qyteti_id IS NULL) AND (fshati_id IS NOT NULL))))
);


ALTER TABLE public.rruga OWNER TO postgres;

--
-- TOC entry 239 (class 1259 OID 17176)
-- Name: rruga_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.rruga_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.rruga_id_seq OWNER TO postgres;

--
-- TOC entry 5040 (class 0 OID 0)
-- Dependencies: 239
-- Name: rruga_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.rruga_id_seq OWNED BY public.rruga.id;


--
-- TOC entry 218 (class 1259 OID 16872)
-- Name: tipi_objektit; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tipi_objektit (
    id integer NOT NULL,
    emri character varying(100) NOT NULL
);


ALTER TABLE public.tipi_objektit OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 16871)
-- Name: tipi_objektit_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tipi_objektit_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.tipi_objektit_id_seq OWNER TO postgres;

--
-- TOC entry 5041 (class 0 OID 0)
-- Dependencies: 217
-- Name: tipi_objektit_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.tipi_objektit_id_seq OWNED BY public.tipi_objektit.id;


--
-- TOC entry 232 (class 1259 OID 17011)
-- Name: user_activity; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_activity (
    id integer NOT NULL,
    user_id integer NOT NULL,
    veprimi character varying(50) NOT NULL,
    entiteti character varying(50) NOT NULL,
    entiteti_id integer NOT NULL,
    data timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);


ALTER TABLE public.user_activity OWNER TO postgres;

--
-- TOC entry 231 (class 1259 OID 17010)
-- Name: user_activity_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.user_activity_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.user_activity_id_seq OWNER TO postgres;

--
-- TOC entry 5042 (class 0 OID 0)
-- Dependencies: 231
-- Name: user_activity_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.user_activity_id_seq OWNED BY public.user_activity.id;


--
-- TOC entry 230 (class 1259 OID 17000)
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    id integer NOT NULL,
    name character varying(100) NOT NULL,
    email character varying(100),
    age integer,
    roli character varying(20) DEFAULT 'qytetar'::character varying,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    password_hash character varying(255),
    salt character varying(255),
    last_login timestamp without time zone
);


ALTER TABLE public.users OWNER TO postgres;

--
-- TOC entry 229 (class 1259 OID 16999)
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.users_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.users_id_seq OWNER TO postgres;

--
-- TOC entry 5043 (class 0 OID 0)
-- Dependencies: 229
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;


--
-- TOC entry 4801 (class 2604 OID 16925)
-- Name: adresa id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.adresa ALTER COLUMN id SET DEFAULT nextval('public.adresa_id_seq'::regclass);


--
-- TOC entry 4810 (class 2604 OID 17138)
-- Name: fshati id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.fshati ALTER COLUMN id SET DEFAULT nextval('public.fshati_id_seq'::regclass);


--
-- TOC entry 4800 (class 2604 OID 16901)
-- Name: kodi_postar id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.kodi_postar ALTER COLUMN id SET DEFAULT nextval('public.kodi_postar_id_seq'::regclass);


--
-- TOC entry 4798 (class 2604 OID 16882)
-- Name: komuna id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.komuna ALTER COLUMN id SET DEFAULT nextval('public.komuna_id_seq'::regclass);


--
-- TOC entry 4811 (class 2604 OID 17165)
-- Name: lagjja id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.lagjja ALTER COLUMN id SET DEFAULT nextval('public.lagjja_id_seq'::regclass);


--
-- TOC entry 4802 (class 2604 OID 16980)
-- Name: objekti id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.objekti ALTER COLUMN id SET DEFAULT nextval('public.objekti_id_seq'::regclass);


--
-- TOC entry 4799 (class 2604 OID 16889)
-- Name: qyteti id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.qyteti ALTER COLUMN id SET DEFAULT nextval('public.qyteti_id_seq'::regclass);


--
-- TOC entry 4808 (class 2604 OID 17120)
-- Name: recent_searches id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.recent_searches ALTER COLUMN id SET DEFAULT nextval('public.recent_searches_id_seq'::regclass);


--
-- TOC entry 4813 (class 2604 OID 17180)
-- Name: rruga id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.rruga ALTER COLUMN id SET DEFAULT nextval('public.rruga_id_seq'::regclass);


--
-- TOC entry 4797 (class 2604 OID 16875)
-- Name: tipi_objektit id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tipi_objektit ALTER COLUMN id SET DEFAULT nextval('public.tipi_objektit_id_seq'::regclass);


--
-- TOC entry 4806 (class 2604 OID 17014)
-- Name: user_activity id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_activity ALTER COLUMN id SET DEFAULT nextval('public.user_activity_id_seq'::regclass);


--
-- TOC entry 4803 (class 2604 OID 17003)
-- Name: users id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);


--
-- TOC entry 5012 (class 0 OID 16922)
-- Dependencies: 226
-- Data for Name: adresa; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.adresa (id, rruga, numri, kodi_postar) FROM stdin;
26	Iliria	12	10000
27	Ahmet gashi	12	10000
28	Shaban Shala	27	10000
29	Shaban Shala	30	10000
30	Ahmet gashi	38	10000
32	Shaban Shala	33	10000
\.


--
-- TOC entry 5022 (class 0 OID 17135)
-- Dependencies: 236
-- Data for Name: fshati; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.fshati (id, emri, komuna_id) FROM stdin;
1	Veterrnik	1
\.


--
-- TOC entry 5010 (class 0 OID 16898)
-- Dependencies: 224
-- Data for Name: kodi_postar; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.kodi_postar (id, kodi, nenregjioni, regjioni, komuna_id) FROM stdin;
1	10000	Qendra	Regjioni Qendror	1
\.


--
-- TOC entry 5006 (class 0 OID 16879)
-- Dependencies: 220
-- Data for Name: komuna; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.komuna (id, emri) FROM stdin;
1	Prishtina
2	Komuna e Prishtinës
\.


--
-- TOC entry 5024 (class 0 OID 17162)
-- Dependencies: 238
-- Data for Name: lagjja; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.lagjja (id, emri, komuna_id, siperfaqja, pershkrimi, statusi_zyrtar) FROM stdin;
1	Lagjja Qendrore	1	2.5	Lagje afër qendrës së qytetit	t
\.


--
-- TOC entry 5014 (class 0 OID 16977)
-- Dependencies: 228
-- Data for Name: objekti; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.objekti (id, tipi_objektit_id, emri, rruga_id, numri_nderteses, koordinatat_gps, kodi_postar_id) FROM stdin;
2	1	Shkolla 'Naim Frashëri'	1	12B	\N	1
\.


--
-- TOC entry 5008 (class 0 OID 16886)
-- Dependencies: 222
-- Data for Name: qyteti; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.qyteti (id, emri, komuna_id) FROM stdin;
1	Prishtina	2
2	Prishtina	1
3	Komuna e Prishtinës	2
\.


--
-- TOC entry 5020 (class 0 OID 17117)
-- Dependencies: 234
-- Data for Name: recent_searches; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.recent_searches (id, user_id, adresa_id, search_time) FROM stdin;
13	11	27	2025-05-21 18:45:38.742658
14	11	27	2025-05-21 18:46:03.255763
15	11	27	2025-05-21 18:47:47.88542
16	11	27	2025-05-21 18:48:07.494294
17	11	28	2025-05-21 20:46:17.463542
18	11	28	2025-05-21 20:54:38.180007
19	11	28	2025-05-21 21:24:10.929727
20	11	28	2025-05-21 21:25:09.215702
21	11	27	2025-05-21 21:25:30.340143
22	11	28	2025-05-21 21:26:13.632162
23	11	27	2025-05-21 21:26:25.202141
24	13	27	2025-05-21 22:21:57.128576
25	13	27	2025-05-21 22:26:01.289547
26	13	28	2025-05-21 22:33:21.292717
27	13	28	2025-05-21 22:34:43.554233
28	13	27	2025-05-21 23:35:10.541278
29	13	27	2025-05-21 23:38:09.860732
30	13	27	2025-05-21 23:41:00.87563
31	13	27	2025-05-21 23:44:21.026336
32	13	27	2025-05-21 23:44:46.965845
33	13	27	2025-05-21 23:46:33.296475
\.


--
-- TOC entry 5026 (class 0 OID 17177)
-- Dependencies: 240
-- Data for Name: rruga; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.rruga (id, emri, komuna_id, qyteti_id, fshati_id, lagjja_id) FROM stdin;
1	Shaban Shala	1	\N	1	1
3	Ahmet gashi	1	1	\N	1
4	Ahmet gashi	1	2	\N	1
\.


--
-- TOC entry 5004 (class 0 OID 16872)
-- Dependencies: 218
-- Data for Name: tipi_objektit; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tipi_objektit (id, emri) FROM stdin;
1	Shkollë
\.


--
-- TOC entry 5018 (class 0 OID 17011)
-- Dependencies: 232
-- Data for Name: user_activity; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.user_activity (id, user_id, veprimi, entiteti, entiteti_id, data) FROM stdin;
\.


--
-- TOC entry 5016 (class 0 OID 17000)
-- Dependencies: 230
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users (id, name, email, age, roli, created_at, password_hash, salt, last_login) FROM stdin;
9	Admin Test	admin@example.com	40	admin	2025-05-19 23:27:47.037322	hash789	salt789	\N
8	Zyrtar Test	zyrtar@example.com	35	qytetar	2025-05-19 23:27:47.037322	hash456	salt456	\N
7	Qytetar Test	qytetar@example.com	28	admin	2025-05-19 23:27:47.037322	hash123	salt123	\N
3	Arben	\N	25	zyrtar_komunal	2025-05-09 01:18:12.716846	\N	\N	\N
10	Anite	anite@gmail.com	20	zyrtar_komunal	2025-05-20 01:32:09.704874	8089a62249be53e81e1304fda4bdf1e75b9621d9c153365676365fe16013865f	sq5nqIfKqU7RmlCOxYWcxcdrc4bMHmg9h6lhBgAu2ng=	\N
11	Arita	arita@gmail.com	19	qytetar	2025-05-20 11:47:52.331849	90b1eeea5cf4f8e53b378996a537454f73f7d726b6c79bc999595b166b80dcfb	Mn/g+cgH3mRObN40ssYWqBVoO1db3m0XKYPUasd2ZGU=	\N
12	Erion	e@gmail.com	19	qytetar	2025-05-20 12:29:52.051489	a16bab1014934f290c1e8e72859c77b54a1018564a082a0ebb88c7c0f6bd5e6a	XQg6+ROz27fqesFCAJRcAkUg4hGkuUaQ5wuwmWMbNvM=	\N
13	E	er@gmail.com	20	admin	2025-05-20 12:39:25.815274	7467d1f709612c255a64ac5299d6a82ba93109144dcd6b3d8efbdb1686062c74	1IOWq1Dy5SQSKKVIUFiZiPzzHugzIkCT1QVPz6MwU94=	\N
\.


--
-- TOC entry 5044 (class 0 OID 0)
-- Dependencies: 225
-- Name: adresa_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.adresa_id_seq', 32, true);


--
-- TOC entry 5045 (class 0 OID 0)
-- Dependencies: 235
-- Name: fshati_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.fshati_id_seq', 1, true);


--
-- TOC entry 5046 (class 0 OID 0)
-- Dependencies: 223
-- Name: kodi_postar_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.kodi_postar_id_seq', 3, true);


--
-- TOC entry 5047 (class 0 OID 0)
-- Dependencies: 219
-- Name: komuna_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.komuna_id_seq', 1, false);


--
-- TOC entry 5048 (class 0 OID 0)
-- Dependencies: 237
-- Name: lagjja_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.lagjja_id_seq', 1, true);


--
-- TOC entry 5049 (class 0 OID 0)
-- Dependencies: 227
-- Name: objekti_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.objekti_id_seq', 2, true);


--
-- TOC entry 5050 (class 0 OID 0)
-- Dependencies: 221
-- Name: qyteti_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.qyteti_id_seq', 3, true);


--
-- TOC entry 5051 (class 0 OID 0)
-- Dependencies: 233
-- Name: recent_searches_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.recent_searches_id_seq', 33, true);


--
-- TOC entry 5052 (class 0 OID 0)
-- Dependencies: 239
-- Name: rruga_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.rruga_id_seq', 4, true);


--
-- TOC entry 5053 (class 0 OID 0)
-- Dependencies: 217
-- Name: tipi_objektit_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tipi_objektit_id_seq', 1, false);


--
-- TOC entry 5054 (class 0 OID 0)
-- Dependencies: 231
-- Name: user_activity_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.user_activity_id_seq', 1, false);


--
-- TOC entry 5055 (class 0 OID 0)
-- Dependencies: 229
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.users_id_seq', 13, true);


--
-- TOC entry 4824 (class 2606 OID 16927)
-- Name: adresa adresa_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.adresa
    ADD CONSTRAINT adresa_pkey PRIMARY KEY (id);


--
-- TOC entry 4836 (class 2606 OID 17140)
-- Name: fshati fshati_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.fshati
    ADD CONSTRAINT fshati_pkey PRIMARY KEY (id);


--
-- TOC entry 4822 (class 2606 OID 16903)
-- Name: kodi_postar kodi_postar_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.kodi_postar
    ADD CONSTRAINT kodi_postar_pkey PRIMARY KEY (id);


--
-- TOC entry 4818 (class 2606 OID 16884)
-- Name: komuna komuna_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.komuna
    ADD CONSTRAINT komuna_pkey PRIMARY KEY (id);


--
-- TOC entry 4838 (class 2606 OID 17170)
-- Name: lagjja lagjja_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.lagjja
    ADD CONSTRAINT lagjja_pkey PRIMARY KEY (id);


--
-- TOC entry 4826 (class 2606 OID 16982)
-- Name: objekti objekti_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.objekti
    ADD CONSTRAINT objekti_pkey PRIMARY KEY (id);


--
-- TOC entry 4820 (class 2606 OID 16891)
-- Name: qyteti qyteti_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.qyteti
    ADD CONSTRAINT qyteti_pkey PRIMARY KEY (id);


--
-- TOC entry 4834 (class 2606 OID 17123)
-- Name: recent_searches recent_searches_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.recent_searches
    ADD CONSTRAINT recent_searches_pkey PRIMARY KEY (id);


--
-- TOC entry 4840 (class 2606 OID 17183)
-- Name: rruga rruga_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.rruga
    ADD CONSTRAINT rruga_pkey PRIMARY KEY (id);


--
-- TOC entry 4816 (class 2606 OID 16877)
-- Name: tipi_objektit tipi_objektit_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tipi_objektit
    ADD CONSTRAINT tipi_objektit_pkey PRIMARY KEY (id);


--
-- TOC entry 4832 (class 2606 OID 17017)
-- Name: user_activity user_activity_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_activity
    ADD CONSTRAINT user_activity_pkey PRIMARY KEY (id);


--
-- TOC entry 4828 (class 2606 OID 17009)
-- Name: users users_email_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_email_key UNIQUE (email);


--
-- TOC entry 4830 (class 2606 OID 17007)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- TOC entry 4843 (class 2606 OID 17110)
-- Name: kodi_postar fk_kodipostar_qyteti; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.kodi_postar
    ADD CONSTRAINT fk_kodipostar_qyteti FOREIGN KEY (komuna_id) REFERENCES public.komuna(id);


--
-- TOC entry 4844 (class 2606 OID 17024)
-- Name: kodi_postar fk_komuna; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.kodi_postar
    ADD CONSTRAINT fk_komuna FOREIGN KEY (komuna_id) REFERENCES public.komuna(id) ON DELETE SET NULL;


--
-- TOC entry 4845 (class 2606 OID 17095)
-- Name: objekti fk_objekti_kodipostar; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.objekti
    ADD CONSTRAINT fk_objekti_kodipostar FOREIGN KEY (kodi_postar_id) REFERENCES public.kodi_postar(id);


--
-- TOC entry 4846 (class 2606 OID 17085)
-- Name: objekti fk_objekti_tipi; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.objekti
    ADD CONSTRAINT fk_objekti_tipi FOREIGN KEY (tipi_objektit_id) REFERENCES public.tipi_objektit(id);


--
-- TOC entry 4841 (class 2606 OID 17035)
-- Name: qyteti fk_qyteti_komuna; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.qyteti
    ADD CONSTRAINT fk_qyteti_komuna FOREIGN KEY (komuna_id) REFERENCES public.komuna(id);


--
-- TOC entry 4852 (class 2606 OID 17141)
-- Name: fshati fshati_komuna_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.fshati
    ADD CONSTRAINT fshati_komuna_id_fkey FOREIGN KEY (komuna_id) REFERENCES public.komuna(id);


--
-- TOC entry 4853 (class 2606 OID 17171)
-- Name: lagjja lagjja_komuna_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.lagjja
    ADD CONSTRAINT lagjja_komuna_id_fkey FOREIGN KEY (komuna_id) REFERENCES public.komuna(id);


--
-- TOC entry 4847 (class 2606 OID 16993)
-- Name: objekti objekti_kodi_postar_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.objekti
    ADD CONSTRAINT objekti_kodi_postar_id_fkey FOREIGN KEY (kodi_postar_id) REFERENCES public.kodi_postar(id);


--
-- TOC entry 4848 (class 2606 OID 16983)
-- Name: objekti objekti_tipi_objektit_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.objekti
    ADD CONSTRAINT objekti_tipi_objektit_id_fkey FOREIGN KEY (tipi_objektit_id) REFERENCES public.tipi_objektit(id);


--
-- TOC entry 4842 (class 2606 OID 16892)
-- Name: qyteti qyteti_komuna_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.qyteti
    ADD CONSTRAINT qyteti_komuna_id_fkey FOREIGN KEY (komuna_id) REFERENCES public.komuna(id);


--
-- TOC entry 4850 (class 2606 OID 17129)
-- Name: recent_searches recent_searches_adresa_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.recent_searches
    ADD CONSTRAINT recent_searches_adresa_id_fkey FOREIGN KEY (adresa_id) REFERENCES public.adresa(id);


--
-- TOC entry 4851 (class 2606 OID 17124)
-- Name: recent_searches recent_searches_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.recent_searches
    ADD CONSTRAINT recent_searches_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- TOC entry 4854 (class 2606 OID 17194)
-- Name: rruga rruga_fshati_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.rruga
    ADD CONSTRAINT rruga_fshati_id_fkey FOREIGN KEY (fshati_id) REFERENCES public.fshati(id);


--
-- TOC entry 4855 (class 2606 OID 17184)
-- Name: rruga rruga_komuna_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.rruga
    ADD CONSTRAINT rruga_komuna_id_fkey FOREIGN KEY (komuna_id) REFERENCES public.komuna(id);


--
-- TOC entry 4856 (class 2606 OID 17199)
-- Name: rruga rruga_lagjja_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.rruga
    ADD CONSTRAINT rruga_lagjja_id_fkey FOREIGN KEY (lagjja_id) REFERENCES public.lagjja(id);


--
-- TOC entry 4857 (class 2606 OID 17189)
-- Name: rruga rruga_qyteti_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.rruga
    ADD CONSTRAINT rruga_qyteti_id_fkey FOREIGN KEY (qyteti_id) REFERENCES public.qyteti(id);


--
-- TOC entry 4849 (class 2606 OID 17018)
-- Name: user_activity user_activity_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_activity
    ADD CONSTRAINT user_activity_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(id);


-- Completed on 2025-05-21 23:57:46

--
-- PostgreSQL database dump complete
--

