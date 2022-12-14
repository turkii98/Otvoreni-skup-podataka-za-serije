PGDMP     &                
    z         
   netflixhbo    15.0    15.0 K    H           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            I           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            J           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            K           1262    18074 
   netflixhbo    DATABASE     ?   CREATE DATABASE netflixhbo WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Croatian_Croatia.1252';
    DROP DATABASE netflixhbo;
                postgres    false            ?            1259    18095    glumci    TABLE     z   CREATE TABLE public.glumci (
    id integer NOT NULL,
    ime character varying(25),
    prezime character varying(25)
);
    DROP TABLE public.glumci;
       public         heap    postgres    false            ?            1259    18094    glumci_id_seq    SEQUENCE     ?   CREATE SEQUENCE public.glumci_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.glumci_id_seq;
       public          postgres    false    220            L           0    0    glumci_id_seq    SEQUENCE OWNED BY     ?   ALTER SEQUENCE public.glumci_id_seq OWNED BY public.glumci.id;
          public          postgres    false    219            ?            1259    18177    glumci_u_seriji    TABLE     h   CREATE TABLE public.glumci_u_seriji (
    glumac_id integer NOT NULL,
    serija_id integer NOT NULL
);
 #   DROP TABLE public.glumci_u_seriji;
       public         heap    postgres    false            ?            1259    18175    glumci_u_seriji_glumac_id_seq    SEQUENCE     ?   CREATE SEQUENCE public.glumci_u_seriji_glumac_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 4   DROP SEQUENCE public.glumci_u_seriji_glumac_id_seq;
       public          postgres    false    231            M           0    0    glumci_u_seriji_glumac_id_seq    SEQUENCE OWNED BY     _   ALTER SEQUENCE public.glumci_u_seriji_glumac_id_seq OWNED BY public.glumci_u_seriji.glumac_id;
          public          postgres    false    229            ?            1259    18176    glumci_u_seriji_serija_id_seq    SEQUENCE     ?   CREATE SEQUENCE public.glumci_u_seriji_serija_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 4   DROP SEQUENCE public.glumci_u_seriji_serija_id_seq;
       public          postgres    false    231            N           0    0    glumci_u_seriji_serija_id_seq    SEQUENCE OWNED BY     _   ALTER SEQUENCE public.glumci_u_seriji_serija_id_seq OWNED BY public.glumci_u_seriji.serija_id;
          public          postgres    false    230            ?            1259    18142 	   redatelji    TABLE     ?   CREATE TABLE public.redatelji (
    id integer NOT NULL,
    ime character varying(25),
    prezime character varying(25),
    serija_id integer NOT NULL
);
    DROP TABLE public.redatelji;
       public         heap    postgres    false            ?            1259    18140    redatelji_id_seq    SEQUENCE     ?   CREATE SEQUENCE public.redatelji_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 '   DROP SEQUENCE public.redatelji_id_seq;
       public          postgres    false    228            O           0    0    redatelji_id_seq    SEQUENCE OWNED BY     E   ALTER SEQUENCE public.redatelji_id_seq OWNED BY public.redatelji.id;
          public          postgres    false    226            ?            1259    18141    redatelji_serija_id_seq    SEQUENCE     ?   CREATE SEQUENCE public.redatelji_serija_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 .   DROP SEQUENCE public.redatelji_serija_id_seq;
       public          postgres    false    228            P           0    0    redatelji_serija_id_seq    SEQUENCE OWNED BY     S   ALTER SEQUENCE public.redatelji_serija_id_seq OWNED BY public.redatelji.serija_id;
          public          postgres    false    227            ?            1259    18114    serija    TABLE     )  CREATE TABLE public.serija (
    id integer NOT NULL,
    naziv character varying(250),
    prvo_emitiranje date,
    imdb_ocjena numeric,
    "završeno" boolean,
    prosjecno_trajanje integer,
    zanr_id integer NOT NULL,
    platforma_id integer NOT NULL,
    tematika_id integer NOT NULL
);
    DROP TABLE public.serija;
       public         heap    postgres    false            ?            1259    18110    serija_id_seq    SEQUENCE     ?   CREATE SEQUENCE public.serija_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.serija_id_seq;
       public          postgres    false    225            Q           0    0    serija_id_seq    SEQUENCE OWNED BY     ?   ALTER SEQUENCE public.serija_id_seq OWNED BY public.serija.id;
          public          postgres    false    221            ?            1259    18112    serija_platforma_id_seq    SEQUENCE     ?   CREATE SEQUENCE public.serija_platforma_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 .   DROP SEQUENCE public.serija_platforma_id_seq;
       public          postgres    false    225            R           0    0    serija_platforma_id_seq    SEQUENCE OWNED BY     S   ALTER SEQUENCE public.serija_platforma_id_seq OWNED BY public.serija.platforma_id;
          public          postgres    false    223            ?            1259    18113    serija_tematika_id_seq    SEQUENCE     ?   CREATE SEQUENCE public.serija_tematika_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 -   DROP SEQUENCE public.serija_tematika_id_seq;
       public          postgres    false    225            S           0    0    serija_tematika_id_seq    SEQUENCE OWNED BY     Q   ALTER SEQUENCE public.serija_tematika_id_seq OWNED BY public.serija.tematika_id;
          public          postgres    false    224            ?            1259    18111    serija_zanr_id_seq    SEQUENCE     ?   CREATE SEQUENCE public.serija_zanr_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.serija_zanr_id_seq;
       public          postgres    false    225            T           0    0    serija_zanr_id_seq    SEQUENCE OWNED BY     I   ALTER SEQUENCE public.serija_zanr_id_seq OWNED BY public.serija.zanr_id;
          public          postgres    false    222            ?            1259    18081    streamingplatforme    TABLE     e   CREATE TABLE public.streamingplatforme (
    id integer NOT NULL,
    naziv character varying(25)
);
 &   DROP TABLE public.streamingplatforme;
       public         heap    postgres    false            ?            1259    18080    streamingplatforme_tem_id_seq    SEQUENCE     ?   CREATE SEQUENCE public.streamingplatforme_tem_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 4   DROP SEQUENCE public.streamingplatforme_tem_id_seq;
       public          postgres    false    216            U           0    0    streamingplatforme_tem_id_seq    SEQUENCE OWNED BY     [   ALTER SEQUENCE public.streamingplatforme_tem_id_seq OWNED BY public.streamingplatforme.id;
          public          postgres    false    215            ?            1259    18075    tematika    TABLE     [   CREATE TABLE public.tematika (
    id integer NOT NULL,
    naziv character varying(25)
);
    DROP TABLE public.tematika;
       public         heap    postgres    false            ?            1259    18088    zanr    TABLE     W   CREATE TABLE public.zanr (
    id integer NOT NULL,
    naziv character varying(25)
);
    DROP TABLE public.zanr;
       public         heap    postgres    false            ?            1259    18087    zanr_id_seq    SEQUENCE     ?   CREATE SEQUENCE public.zanr_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 "   DROP SEQUENCE public.zanr_id_seq;
       public          postgres    false    218            V           0    0    zanr_id_seq    SEQUENCE OWNED BY     ;   ALTER SEQUENCE public.zanr_id_seq OWNED BY public.zanr.id;
          public          postgres    false    217            ?           2604    18098 	   glumci id    DEFAULT     f   ALTER TABLE ONLY public.glumci ALTER COLUMN id SET DEFAULT nextval('public.glumci_id_seq'::regclass);
 8   ALTER TABLE public.glumci ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    219    220    220            ?           2604    18180    glumci_u_seriji glumac_id    DEFAULT     ?   ALTER TABLE ONLY public.glumci_u_seriji ALTER COLUMN glumac_id SET DEFAULT nextval('public.glumci_u_seriji_glumac_id_seq'::regclass);
 H   ALTER TABLE public.glumci_u_seriji ALTER COLUMN glumac_id DROP DEFAULT;
       public          postgres    false    229    231    231            ?           2604    18181    glumci_u_seriji serija_id    DEFAULT     ?   ALTER TABLE ONLY public.glumci_u_seriji ALTER COLUMN serija_id SET DEFAULT nextval('public.glumci_u_seriji_serija_id_seq'::regclass);
 H   ALTER TABLE public.glumci_u_seriji ALTER COLUMN serija_id DROP DEFAULT;
       public          postgres    false    230    231    231            ?           2604    18145    redatelji id    DEFAULT     l   ALTER TABLE ONLY public.redatelji ALTER COLUMN id SET DEFAULT nextval('public.redatelji_id_seq'::regclass);
 ;   ALTER TABLE public.redatelji ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    226    228    228            ?           2604    18146    redatelji serija_id    DEFAULT     z   ALTER TABLE ONLY public.redatelji ALTER COLUMN serija_id SET DEFAULT nextval('public.redatelji_serija_id_seq'::regclass);
 B   ALTER TABLE public.redatelji ALTER COLUMN serija_id DROP DEFAULT;
       public          postgres    false    228    227    228            ?           2604    18117 	   serija id    DEFAULT     f   ALTER TABLE ONLY public.serija ALTER COLUMN id SET DEFAULT nextval('public.serija_id_seq'::regclass);
 8   ALTER TABLE public.serija ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    221    225    225            ?           2604    18118    serija zanr_id    DEFAULT     p   ALTER TABLE ONLY public.serija ALTER COLUMN zanr_id SET DEFAULT nextval('public.serija_zanr_id_seq'::regclass);
 =   ALTER TABLE public.serija ALTER COLUMN zanr_id DROP DEFAULT;
       public          postgres    false    222    225    225            ?           2604    18119    serija platforma_id    DEFAULT     z   ALTER TABLE ONLY public.serija ALTER COLUMN platforma_id SET DEFAULT nextval('public.serija_platforma_id_seq'::regclass);
 B   ALTER TABLE public.serija ALTER COLUMN platforma_id DROP DEFAULT;
       public          postgres    false    223    225    225            ?           2604    18120    serija tematika_id    DEFAULT     x   ALTER TABLE ONLY public.serija ALTER COLUMN tematika_id SET DEFAULT nextval('public.serija_tematika_id_seq'::regclass);
 A   ALTER TABLE public.serija ALTER COLUMN tematika_id DROP DEFAULT;
       public          postgres    false    224    225    225            ?           2604    18084    streamingplatforme id    DEFAULT     ?   ALTER TABLE ONLY public.streamingplatforme ALTER COLUMN id SET DEFAULT nextval('public.streamingplatforme_tem_id_seq'::regclass);
 D   ALTER TABLE public.streamingplatforme ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    215    216    216            ?           2604    18091    zanr id    DEFAULT     b   ALTER TABLE ONLY public.zanr ALTER COLUMN id SET DEFAULT nextval('public.zanr_id_seq'::regclass);
 6   ALTER TABLE public.zanr ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    217    218    218            :          0    18095    glumci 
   TABLE DATA           2   COPY public.glumci (id, ime, prezime) FROM stdin;
    public          postgres    false    220   .T       E          0    18177    glumci_u_seriji 
   TABLE DATA           ?   COPY public.glumci_u_seriji (glumac_id, serija_id) FROM stdin;
    public          postgres    false    231   YU       B          0    18142 	   redatelji 
   TABLE DATA           @   COPY public.redatelji (id, ime, prezime, serija_id) FROM stdin;
    public          postgres    false    228   ?U       ?          0    18114    serija 
   TABLE DATA           ?   COPY public.serija (id, naziv, prvo_emitiranje, imdb_ocjena, "završeno", prosjecno_trajanje, zanr_id, platforma_id, tematika_id) FROM stdin;
    public          postgres    false    225   ?V       6          0    18081    streamingplatforme 
   TABLE DATA           7   COPY public.streamingplatforme (id, naziv) FROM stdin;
    public          postgres    false    216   ?W       4          0    18075    tematika 
   TABLE DATA           -   COPY public.tematika (id, naziv) FROM stdin;
    public          postgres    false    214   ?W       8          0    18088    zanr 
   TABLE DATA           )   COPY public.zanr (id, naziv) FROM stdin;
    public          postgres    false    218   :X       W           0    0    glumci_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.glumci_id_seq', 1, false);
          public          postgres    false    219            X           0    0    glumci_u_seriji_glumac_id_seq    SEQUENCE SET     L   SELECT pg_catalog.setval('public.glumci_u_seriji_glumac_id_seq', 1, false);
          public          postgres    false    229            Y           0    0    glumci_u_seriji_serija_id_seq    SEQUENCE SET     L   SELECT pg_catalog.setval('public.glumci_u_seriji_serija_id_seq', 1, false);
          public          postgres    false    230            Z           0    0    redatelji_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.redatelji_id_seq', 1, false);
          public          postgres    false    226            [           0    0    redatelji_serija_id_seq    SEQUENCE SET     F   SELECT pg_catalog.setval('public.redatelji_serija_id_seq', 1, false);
          public          postgres    false    227            \           0    0    serija_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.serija_id_seq', 1, false);
          public          postgres    false    221            ]           0    0    serija_platforma_id_seq    SEQUENCE SET     F   SELECT pg_catalog.setval('public.serija_platforma_id_seq', 1, false);
          public          postgres    false    223            ^           0    0    serija_tematika_id_seq    SEQUENCE SET     E   SELECT pg_catalog.setval('public.serija_tematika_id_seq', 1, false);
          public          postgres    false    224            _           0    0    serija_zanr_id_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.serija_zanr_id_seq', 1, false);
          public          postgres    false    222            `           0    0    streamingplatforme_tem_id_seq    SEQUENCE SET     L   SELECT pg_catalog.setval('public.streamingplatforme_tem_id_seq', 1, false);
          public          postgres    false    215            a           0    0    zanr_id_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.zanr_id_seq', 1, false);
          public          postgres    false    217            ?           2606    18100    glumci glumci_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.glumci
    ADD CONSTRAINT glumci_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.glumci DROP CONSTRAINT glumci_pkey;
       public            postgres    false    220            ?           2606    18183    glumci_u_seriji gus_pkey 
   CONSTRAINT     h   ALTER TABLE ONLY public.glumci_u_seriji
    ADD CONSTRAINT gus_pkey PRIMARY KEY (glumac_id, serija_id);
 B   ALTER TABLE ONLY public.glumci_u_seriji DROP CONSTRAINT gus_pkey;
       public            postgres    false    231    231            ?           2606    18148    redatelji redatelji_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.redatelji
    ADD CONSTRAINT redatelji_pkey PRIMARY KEY (id);
 B   ALTER TABLE ONLY public.redatelji DROP CONSTRAINT redatelji_pkey;
       public            postgres    false    228            ?           2606    18124    serija serija_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.serija
    ADD CONSTRAINT serija_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.serija DROP CONSTRAINT serija_pkey;
       public            postgres    false    225            ?           2606    18086 *   streamingplatforme streamingplatforme_pkey 
   CONSTRAINT     h   ALTER TABLE ONLY public.streamingplatforme
    ADD CONSTRAINT streamingplatforme_pkey PRIMARY KEY (id);
 T   ALTER TABLE ONLY public.streamingplatforme DROP CONSTRAINT streamingplatforme_pkey;
       public            postgres    false    216            ?           2606    18079    tematika tematika_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.tematika
    ADD CONSTRAINT tematika_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.tematika DROP CONSTRAINT tematika_pkey;
       public            postgres    false    214            ?           2606    18093    zanr zanr_pkey 
   CONSTRAINT     L   ALTER TABLE ONLY public.zanr
    ADD CONSTRAINT zanr_pkey PRIMARY KEY (id);
 8   ALTER TABLE ONLY public.zanr DROP CONSTRAINT zanr_pkey;
       public            postgres    false    218            ?           2606    18184 .   glumci_u_seriji glumci_u_seriji_glumac_id_fkey    FK CONSTRAINT     ?   ALTER TABLE ONLY public.glumci_u_seriji
    ADD CONSTRAINT glumci_u_seriji_glumac_id_fkey FOREIGN KEY (glumac_id) REFERENCES public.glumci(id) ON UPDATE CASCADE ON DELETE CASCADE;
 X   ALTER TABLE ONLY public.glumci_u_seriji DROP CONSTRAINT glumci_u_seriji_glumac_id_fkey;
       public          postgres    false    231    3225    220            ?           2606    18189 .   glumci_u_seriji glumci_u_seriji_serija_id_fkey    FK CONSTRAINT     ?   ALTER TABLE ONLY public.glumci_u_seriji
    ADD CONSTRAINT glumci_u_seriji_serija_id_fkey FOREIGN KEY (serija_id) REFERENCES public.serija(id) ON UPDATE CASCADE;
 X   ALTER TABLE ONLY public.glumci_u_seriji DROP CONSTRAINT glumci_u_seriji_serija_id_fkey;
       public          postgres    false    3227    231    225            ?           2606    18149 "   redatelji redatelji_serija_id_fkey    FK CONSTRAINT     ?   ALTER TABLE ONLY public.redatelji
    ADD CONSTRAINT redatelji_serija_id_fkey FOREIGN KEY (serija_id) REFERENCES public.serija(id) ON UPDATE CASCADE;
 L   ALTER TABLE ONLY public.redatelji DROP CONSTRAINT redatelji_serija_id_fkey;
       public          postgres    false    225    3227    228            ?           2606    18130    serija serija_platforma_id_fkey    FK CONSTRAINT     ?   ALTER TABLE ONLY public.serija
    ADD CONSTRAINT serija_platforma_id_fkey FOREIGN KEY (platforma_id) REFERENCES public.streamingplatforme(id);
 I   ALTER TABLE ONLY public.serija DROP CONSTRAINT serija_platforma_id_fkey;
       public          postgres    false    3221    225    216            ?           2606    18135    serija serija_tematika_id_fkey    FK CONSTRAINT     ?   ALTER TABLE ONLY public.serija
    ADD CONSTRAINT serija_tematika_id_fkey FOREIGN KEY (tematika_id) REFERENCES public.tematika(id);
 H   ALTER TABLE ONLY public.serija DROP CONSTRAINT serija_tematika_id_fkey;
       public          postgres    false    3219    225    214            ?           2606    18125    serija serija_zanr_id_fkey    FK CONSTRAINT     x   ALTER TABLE ONLY public.serija
    ADD CONSTRAINT serija_zanr_id_fkey FOREIGN KEY (zanr_id) REFERENCES public.zanr(id);
 D   ALTER TABLE ONLY public.serija DROP CONSTRAINT serija_zanr_id_fkey;
       public          postgres    false    225    3223    218            :     x?-??j?0?ϻO?'(??LM?	M	uO??????e????߾???;3?8???;??+xc?JE?????+???3?C?cɸ?c?r??P??N7p?Yh????,?"?B1?qG??4?Ùc?G??x?[a??????h?pQ?mI,ˢN?M???h"4F?l??)$qzQA3???????
>? m???z???Yh6p???+??l??C???&ۣ???Rۗ?jug??(?݄??(8???Z?+??^t??W:0u???U?????E?_O????v?      E   D   x?Ĺ 1??[̍)????:N
 +???H3-???:7?i`WQuM<????6????W?h??FD?>???       B   ?   x?%??N?0D?s?"_?I?ZRR(UQ????RY?E?Ѝ???ǔ?<?4?p?????a?w?PTო/2??TT?Y?G??[??=;???????Oӗ??h?M???lh?=G???????E?l2X?ݜN?D3cE[?:?]?+??8?dvMj?sJ;Nf?1aCJ?38X???_=????aĖT??`<e?'??F-?Z?/G?D7      ?   *  x?E?Kn?0?דS??<v?$]?Tj?"U??J?X????Uq??M
???!?ޚ?a޳??@
?r?\
??TH@?
	?????E??.?֧????cm??G?P(?x?~6!zsz???*?#$?$_?K<??a?q?9??PK?V??h??̻??DJɓf??Z??-???'????%X?????o?l?n???E????\uz?i?v?X?Fu?ʛ??7n3_?d???-?6?{6?E??8?\\??c?>??0?%Q??k?0?Se*-?>????3???6?"?J?7?)???9?j?      6   &   x?3??K-I?ɬ?2??H??2?L?,?K??????? ?+7      4   6   x?326?̯J????K?264?,K-N?I?2252?L??.?M?+I,J??qqq P??      8   S   x?%?K
?0?u?)<???2nX1֦?AO???N'1???I??91?k?R?`lvZe??Q?????4,???Y??k??a?     