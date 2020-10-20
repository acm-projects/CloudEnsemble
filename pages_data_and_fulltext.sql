ALTER TABLE clips ADD FULLTEXT (clip_name);
ALTER TABLE clips ADD FULLTEXT (clip_uploader);
ALTER TABLE tracks ADD FULLTEXT (track_name);
ALTER TABLE tracks ADD FULLTEXT (track_uploader);
ALTER TABLE tags ADD FULLTEXT (tag_id);
ALTER TABLE credentials ADD FULLTEXT (user_name);
ALTER TABLE bands ADD FULLTEXT (band_name);

INSERT INTO credentials (user_name, email, pic_key, password, salt) 
VALUES ('Elizabeth', 'elizabeth@gmail.com', '1', 'clements', '1'),
	   ('Meinhard', 'meinhard@gmail.com', '1', 'capucao', '1'),
       ('Joseph', 'joseph@gmail.com', '1', 'prichard', '1'),
       ('Francisca', 'francisca@gmail.com', '1', 'li', '1'),
       ('Jocelyn', 'jocelyn@gmail.com', '1', 'heckenkamp', '1'),
       ('MusicQueen1324', 'queen@gmail.com', '1', 'queen', '1');

INSERT INTO clips (clip_key, clip_uploader, clip_name, date_uploaded, access)
VALUES ('1', 'Elizabeth', 'windows start', '2020/10/18', 1),
	   ('2', 'Meinhard', 'alarm', '2020/10/18', 2),
       ('3', 'Joseph', 'circle of life', '2020/10/18', 2),
       ('4', 'Elizabeth', 'Guitar jam', '2020/10/18', 2), 
       ('5', 'Meinhard', 'piano cords in d minor', '2020/10/18', 3),
       ('6', 'Joseph', 'alarms and music', '2020/10/18', 2),
       ('7', 'Elizabeth', 'The Minstrel Boy', '2020/10/18', 1),
       ('8', 'Meinhard', 'Johnny Boy', '2020/10/18', 2),
       ('9', 'Joseph', 'Chaminade', '2020/10/18', 2),
       ('10', 'MusicQueen1324', 'blues beat', '2020/10/18', 4),
	   ('11', 'MusicQueen1324', 'Let it Go solo', '2020/10/18', 3);
       
INSERT INTO tracks (track_key, track_uploader, track_name, date_uploaded, access)
VALUES ('101', 'Elizabeth', 'Star Spangled Banner brass arrangement', '10/18/20', 1),
	   ('102', 'Francisca', 'In the Mood', '10/18/20', 1),
       ('103', 'Meinhard', 'Star Wars Theme Remix', '10/18/20', 1),
       ('104', 'Jocelyn', 'Drumline Cadence', '10/18/20', 1),
       ('105', 'Joseph', 'Bohemian Rhapsody', '10/18/20', 1),
       ('106', 'Elizabeth', 'Dancing Queen on bagpipes', '10/18/20', 1),
       ('107', 'Francisca', 'Mozart String Quartet No 19', '10/18/20', 1),
       ('108', 'Jocelyn', 'Yankee Doodle', '10/18/20', 1),
       ('109', 'Meinhard', 'God Bless the USA', '10/18/20', 1),
       ('110', 'Joseph', 'Miscellaneous jazz jam', '10/18/20', 1),
       ('111', 'Jocelyn', 'The Minstrel Boy', '10/18/20', 1),
       ('112', 'Francisca', 'Shostakovich string quartet', '10/18/20', 1),
       ('113', 'Meinhard', 'So What', '10/18/20', 1),
       ('114', 'Jocelyn', 'In a Sentimental Mood', '10/18/20', 1),
       ('115', 'Elizabeth', 'Rhapsody in Blue', '10/18/20', 1),
       ('116', 'Meinhard', 'Duet for two guitars', '10/18/20', 1),
       ('117', 'Francisca', 'The Irish Rover', '10/18/20', 1),
       ('118', 'MusicQueen1324', 'Another One Bites the Dust', '10/18/20', 1),
       ('119', 'MusicQueen1324', 'We Will Rock You', '10/18/20', 1);
       
-- some fake tags for clips
INSERT INTO tags (object_key, tag_id, tag_type) 
VALUES ('1', 'bells', 0),
	   ('1', 'windows', 0),
       ('2', 'guitar', 0),
       ('2', 'rock', 0),
       ('3', 'animals', 0),
       ('3', 'life', 0),
       ('3', 'tim rice', 0),
       ('4', 'rock', 0),
       ('4', 'guitar', 0),
       ('5', 'piano', 0),
       ('5', 'bells', 0),
       ('6', 'animals', 0),
       ('6', 'rock', 0),
       ('6', 'classical', 0),
       ('7', 'irish', 0),
       ('7', 'flute', 0),
       ('7', 'violin', 0),
       ('8', 'irish', 0),
       ('8', 'violin', 0),
       ('9', 'classical', 0),
       ('9', 'flute', 0),
       ('9', 'chaminade', 0),
       ('10', 'jazz', 0),
       ('10', 'drumset', 0),
       ('10', 'blues', 0),
       ('11', 'voice', 0),
       ('11', 'frozen', 0);
       
-- some fake tags for tracks
INSERT INTO tags (object_key, tag_id, tag_type)
VALUES ('101', 'brass', 0), ('101', 'trumpet', 0), ('101', 'patriotic', 0), ('101', 'Jamie Salisbury', 0), ('101', 'American', 0),
	   ('102', 'jazz', 0), ('102', 'saxophone', 0), ('102', 'Joe Garland', 0), ('102', 'Wingy Manone', 0),
       ('103', 'star wars', 0), ('103', 'bells', 0), ('103', 'synthetic', 0),
       ('104', 'drumline', 0), ('104', 'drumline', 0), ('104', 'cadence', 0),
       ('105', 'piano', 0), ('105', 'pop', 0), ('105', 'voice', 0), ('105', 'Queen', 0),
       ('106', 'bagpipes', 0), ('106', 'ABBA', 0),
       ('107', 'classical', 0), ('107', 'strings', 0), 
       ('108', 'folk', 0), ('108', 'patriotic', 0), ('108', 'American', 0), ('108', 'whistles', 0), ('108', 'voice', 0), ('108', 'guitar', 0),
       ('109', 'American', 0), ('109', 'patriotic', 0), ('109', 'voice', 0), ('109', 'Lee Greenwood', 0),
       ('110', 'jazz', 0), ('110', 'guitar', 0), ('110', 'bass', 0),
       ('111', 'irish', 0), ('111', 'bagpipes', 0), ('111', 'flute', 0), ('111', 'tin whistle', 0), ('111', 'folk', 0),
       ('112', 'strings', 0), ('112', 'classical', 0),
       ('113', 'jazz', 0), ('113', 'saxophone', 0), ('113', 'trumpet', 0), ('113', 'Miles Davis', 0), ('113', 'bass', 0), ('113', 'drumset', 0),
       ('114', 'jazz', 0), ('114', 'Duke Ellington', 0),
       ('115', 'jazz', 0), ('114', 'George Gershwin', 0),
       ('116', 'guitar', 0),
       ('117', 'folk', 0), ('117', 'irish', 0), ('117', 'voice', 0),
       ('118', 'Queen', 0), ('118', 'rock', 0), ('118', 'rock', 0), ('118', 'voice', 0),
       ('119', 'Queen', 0), ('119', 'rock', 0), ('119', 'rock', 0), ('119', 'voice', 0);