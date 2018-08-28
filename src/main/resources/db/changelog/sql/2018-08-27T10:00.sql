---creates columns to reference the specific version of a survey.
ALTER TABLE data_collect.snapshot_draft ADD COLUMN survey_version_id bigint;
ALTER TABLE data_collect.snapshots_economics ADD COLUMN survey_version_id bigint;

---set survey_version_id to current saved drafts
update data_collect.snapshot_draft draft set survey_version_id = snapshot_survey_version.version_id from
(select sv.id version_id, sv.survey_id from data_collect.surveys s join data_collect.survey_version sv on sv.survey_id = s.id
join data_collect.snapshot_draft sd on sd.survey_definition_id = s.id) as snapshot_survey_version
where snapshot_survey_version.survey_id = draft.survey_definition_id;

---set survey_version_id to current saved snapshots
update data_collect.snapshots_economics snapshot set survey_version_id = snapshot_survey_version.version_id from
(select sv.id version_id, sv.survey_id from data_collect.surveys s join data_collect.survey_version sv on sv.survey_id = s.id
join data_collect.snapshots_economics se on se.survey_definition_id = s.id) as snapshot_survey_version
where snapshot_survey_version.survey_id = snapshot.survey_definition_id;


