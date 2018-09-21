---creates new survey_version table. This will contain the different definition versions of a survey
CREATE TABLE data_collect.survey_version (
	id bigint NOT NULL,
	created_at timestamp without time zone,
	survey_definition jsonb,
	current boolean,
	survey_id bigint,
	PRIMARY KEY (id),
	CONSTRAINT fk_survey_id FOREIGN KEY (survey_id) REFERENCES data_collect.surveys (id)

);

---Sequence for the survey_version ids
CREATE SEQUENCE data_collect.survey_version_id_seq;
ALTER TABLE data_collect.survey_version ALTER COLUMN id SET DEFAULT nextval('data_collect.survey_version_id_seq');

---The creation date of the survey_version will correspond to the last_modified _at of the surveys
INSERT INTO data_collect.survey_version (created_at,survey_definition,survey_id)
SELECT last_modified_at, survey_definition, id
FROM data_collect.surveys;

---set current = true for all surveys, since at the begining, all of them will be the current in use
update data_collect.survey_version
set current = TRUE;

/*The surveys will no longer be updated. Instead, if a update comes, a new row is added for a new version
  Thus, the last_modified_at and survey_definition properties are not longer needed.
  Instead, we will set the created_at and survey_definition for each new version
 */
alter table data_collect.surveys
drop COLUMN last_modified_at,
drop COLUMN survey_definition;

