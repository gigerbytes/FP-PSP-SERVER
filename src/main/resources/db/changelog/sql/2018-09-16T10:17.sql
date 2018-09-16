CREATE TABLE ps_network.groups_organizations
(
    id bigserial NOT NULL,
    name character varying(50) NOT NULL,
    description character varying(50),
    created_date date NOT NULL,
    organization bigint NOT NULL,
    constraint  groups_organizations_pkey PRIMARY KEY (id),
    CONSTRAINT fk_organization_id FOREIGN KEY (organization) REFERENCES ps_network.groups_organizations (id)
);

--CREATE SEQUENCE ps_network.groups_organizations_id_seq;
ALTER TABLE ps_network.groups_organizations ALTER COLUMN id SET DEFAULT nextval('ps_network.groups_organizations_id_seq');
