CREATE TABLE ps_network.groups_organizations
(
    id bigserial NOT NULL,
    name character varying(50) NOT NULL,
    description character varying(50),
    created_date date NOT NULL,
    organization_id bigint NOT NULL,
    application_id bigint NOT NULL,
    constraint  groups_organizations_pkey PRIMARY KEY (id),
    CONSTRAINT fk_organization_id FOREIGN KEY (organization_id) REFERENCES ps_network.organizations (id),
    CONSTRAINT fk_application_id FOREIGN KEY (application_id) REFERENCES ps_network.applications (id)
);

--CREATE SEQUENCE ps_network.groups_organizations_id_seq;
ALTER TABLE ps_network.groups_organizations ALTER COLUMN id SET DEFAULT nextval('ps_network.groups_organizations_id_seq');

ALTER TABLE ps_network.organizations ADD COLUMN group_organization_id bigint;

ALTER TABLE ps_network.organizations
ADD CONSTRAINT fk_organizations_groups_organizations FOREIGN KEY (group_organization_id) REFERENCES ps_network.groups_organizations (id);
