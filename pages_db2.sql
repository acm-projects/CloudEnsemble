-- 10/7/20
-- pages_db
/*
Changes from Josephs SQL dump:
- tables bands uses band_id pk (so people can change the band name)
- date_created uses DATE datatype and defaults to CURRENT_TIMESTAMP


*/

-- drop tables that reference credentials
DROP TABLE IF EXISTS bands_members;
DROP TABLE IF EXISTS followers;
DROP TABLE IF EXISTS clips;

DROP TABLE IF EXISTS credentials;
CREATE TABLE credentials (
	user_name VARCHAR(100) NOT NULL PRIMARY KEY,
    email VARCHAR(100) NOT NULL UNIQUE,
    pic_key VARCHAR(1000),
    password VARCHAR(1000) NOT NULL,
    description VARCHAR(200),
    salt VARCHAR(100) NOT NULL
);

DROP TABLE IF EXISTS bands;
CREATE TABLE bands (
	band_id INT AUTO_INCREMENT PRIMARY KEY,
    band_name VARCHAR(100) NOT NULL,
    date_created DATE NOT NULL
);

DROP TABLE IF EXISTS bands_members;
CREATE TABLE bands_members (
	band_id INT NOT NULL,
    user_name VARCHAR(100) NOT NULL,
    FOREIGN KEY (band_id) REFERENCES bands(band_id),
    FOREIGN KEY (user_name) REFERENCES credentials(user_name)
);

DROP TABLE IF EXISTS followers;
CREATE TABLE followers (
	id INT AUTO_INCREMENT PRIMARY KEY,
    follower VARCHAR(100) NOT NULL,
    following VARCHAR(100) NOT NULL,
    FOREIGN KEY (follower) REFERENCES credentials(user_name),
    FOREIGN KEY (following) REFERENCES credentials(user_name)
);

DROP TABLE IF EXISTS clips_tags;
DROP TABLE IF EXISTS clips;
CREATE TABLE clips (
	clip_key VARCHAR(500) NOT NULL PRIMARY KEY,
    clip_uploader VARCHAR(100) NOT NULL,
    clip_name VARCHAR(100) NOT NULL,
    date_uploaded TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY(clip_uploader) REFERENCES credentials(user_name)
);

DROP TABLE IF EXISTS tags;
DROP TABLE IF EXISTS tag_types;
CREATE TABLE tag_types (
	type_id INT AUTO_INCREMENT PRIMARY KEY,
    type_name VARCHAR(50) NOT NULL
);

DROP TABLE IF EXISTS clips_tags;
DROP TABLE IF EXISTS tags;
CREATE TABLE tags (
	tag_id VARCHAR(50) UNIQUE NOT NULL PRIMARY KEY,
    tag_type INT NOT NULL,
    FOREIGN KEY (tag_type) REFERENCES tag_types(type_id)
);

DROP TABLE IF EXISTS clips_tags;
CREATE TABLE clips_tags (
	id INT AUTO_INCREMENT PRIMARY KEY,
    clip_key VARCHAR(500) NOT NULL,
    tag_id VARCHAR(50) NOT NULL,
    FOREIGN KEY(clip_key) REFERENCES clips(clip_key),
    FOREIGN KEY(tag_id) REFERENCES tags(tag_id)
);



INSERT INTO credentials (user_name, email, pic_key, password, salt) VALUES ('another_username','another_email','default_profile_pic.png','a1de8b63bc3b0905be12553963d5f9b51ef27eae81696916123a95997d4a9b58cc07e5d434846d88ba9705d8fd6cfb32848f3348fe0915551ab45b70cfaa73d','lYKKohjPY48u63AQrp+vhpOodbc='),('username_test','email_test','default_profile_pic.png','3134fdf8b85b1f9e8571fb81ff7301d248b18a0711cb05dcfc5b9a5f44105755691f390d64c1b7ecbcfa6063bb93f93731b56d58af137d502906856e80399e68','9Z0s/+n5YzhNR4r//swNowijdaE=');
INSERT INTO followers (follower, following) VALUES ('username_test', 'another_username');
INSERT INTO clips VALUES ('6b2e25d2-0067-11eb-84b6-a5ff373173f6','username_test','sound_testing.wav','2020-09-26 21:16:15'),('9356c3de-0386-11eb-89f7-ebfd53bd0672','username_test','Windows_snd.mp3','2020-09-30 20:36:50'),('9fce6edd-046b-11eb-900a-59103fe2c6be','username_test','Come As You Are.mp3','2020-10-01 23:56:31'),('c0295398-012a-11eb-9672-85303f65d6cd','username_test','sound_test.wav','2020-09-27 20:34:29'),('f8132f6d-0391-11eb-8260-eb04af04ff1d','username_test','Windows_sound.mp3','2020-09-30 21:58:24');
INSERT INTO tag_types (type_name) VALUES ('Genre'), ('Artist'), ('Instrument'), ('Other');
