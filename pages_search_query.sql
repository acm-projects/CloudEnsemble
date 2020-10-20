ALTER TABLE clips ADD FULLTEXT (clip_name);
ALTER TABLE clips ADD FULLTEXT (clip_uploader);
ALTER TABLE tracks ADD FULLTEXT (track_name);
ALTER TABLE tracks ADD FULLTEXT (track_uploader);
ALTER TABLE tags ADD FULLTEXT (tag_id);
ALTER TABLE credentials ADD FULLTEXT (user_name);
ALTER TABLE bands ADD FULLTEXT (band_name);

SET @squery = 'Francisca jazz queen guitar';

SELECT 'clip' AS 'type', clips.clip_name, clips.clip_uploader, 
	   (MATCH (clips.clip_name) AGAINST (@squery) + 
            IF(clips.clip_name LIKE CONCAT('%', CAST(@squery AS CHAR CHARACTER SET utf8), '%'), 0.2, 0) + 
			MATCH (clips.clip_uploader) AGAINST (@squery) + 
			SUM(MATCH (tags.tag_id) AGAINST (@squery))) AS 'match_score' 
FROM   clips 
RIGHT  JOIN tags 
ON	   tags.object_key = clips.clip_key 
WHERE  clips.access != 0 
GROUP  BY clips.clip_key  
UNION  ALL 
SELECT 'track', tracks.track_name, tracks.track_uploader,  
       (MATCH (tracks.track_name) AGAINST (@squery) + 
            IF(tracks.track_name LIKE CONCAT('%', CAST(@squery AS CHAR CHARACTER SET utf8), '%'), 0.2, 0) + 
			MATCH (tracks.track_uploader) AGAINST (@squery) + 
			SUM(MATCH (tags.tag_id) AGAINST (@squery))) 
FROM   tracks 
RIGHT  JOIN tags 
ON	   tags.object_key = tracks.track_key 
WHERE  tracks.access != 0 
GROUP  BY tracks.track_key 
UNION  ALL 
SELECT 'username', NULL, user_name, 
       MATCH (user_name) AGAINST (@squery) + 
	      IF(user_name LIKE CONCAT('%', CAST(@squery AS CHAR CHARACTER SET utf8), '%'), 0.5, 0)  
FROM   credentials 
UNION  ALL 
SELECT 'band', NULL, band_name, 
       MATCH (band_name) AGAINST (@squery) + 
	      IF(band_name LIKE CONCAT('%', CAST(@squery AS CHAR CHARACTER SET utf8), '%'), 0.6, 0)  
FROM   bands 
ORDER  BY match_score DESC 
LIMIT  25;

-- Full stats version: use the commented query below to get individual stats for title/username/tag match scores
/*
SELECT 'clip' AS 'type', clips.clip_name, clips.clip_uploader, COUNT(tags.tag_id) AS 'num_tags', 
	   MATCH (clips.clip_name) AGAINST (@squery) + 
          IF(clips.clip_name LIKE CONCAT('%', CAST(@squery AS CHAR CHARACTER SET utf8), '%'), 0.2, 0) 
          AS 'title_score', 
       MATCH (clips.clip_uploader) AGAINST (@squery) AS 'username_score',
       SUM(MATCH (tags.tag_id) AGAINST (@squery)) AS 'tag_score', 
       (MATCH (clips.clip_name) AGAINST (@squery) + 
            IF(clips.clip_name LIKE CONCAT('%', CAST(@squery AS CHAR CHARACTER SET utf8), '%'), 0.2, 0) + 
			MATCH (clips.clip_uploader) AGAINST (@squery) + 
			SUM(MATCH (tags.tag_id) AGAINST (@squery))) AS 'total_score' 
FROM   clips 
RIGHT  JOIN tags 
ON	   tags.object_key = clips.clip_key 
WHERE  clips.access != 0 
GROUP  BY clips.clip_key  
UNION  ALL 
SELECT 'track', tracks.track_name, tracks.track_uploader, COUNT(tags.tag_id) AS 'num_tags', 
	   MATCH (tracks.track_name) AGAINST (@squery) + 
          IF(tracks.track_name LIKE CONCAT('%', CAST(@squery AS CHAR CHARACTER SET utf8), '%'), 0.2, 0) AS 'title_score',
       MATCH (tracks.track_uploader) AGAINST (@squery) AS 'username_score',
       SUM(MATCH (tags.tag_id) AGAINST (@squery)) AS 'tag_score', 
       (MATCH (tracks.track_name) AGAINST (@squery) + 
            IF(tracks.track_name LIKE CONCAT('%', CAST(@squery AS CHAR CHARACTER SET utf8), '%'), 0.2, 0) + 
			MATCH (tracks.track_uploader) AGAINST (@squery) + 
			SUM(MATCH (tags.tag_id) AGAINST (@squery))) AS 'total_score' 
FROM   tracks 
RIGHT  JOIN tags 
ON	   tags.object_key = tracks.track_key 
WHERE  tracks.access != 0 
GROUP  BY tracks.track_key 
UNION  ALL 
SELECT 'username', NULL, user_name, NULL, NULL, 
       MATCH (user_name) AGAINST (@squery) + 
	      IF(user_name LIKE CONCAT('%', CAST(@squery AS CHAR CHARACTER SET utf8), '%'), 0.5, 0), NULL, 
       MATCH (user_name) AGAINST (@squery) + 
	      IF(user_name LIKE CONCAT('%', CAST(@squery AS CHAR CHARACTER SET utf8), '%'), 0.5, 0)  
FROM   credentials 
UNION  ALL 
SELECT 'band', NULL, band_name, NULL, NULL, 
       MATCH (band_name) AGAINST (@squery) + 
	      IF(band_name LIKE CONCAT('%', CAST(@squery AS CHAR CHARACTER SET utf8), '%'), 0.6, 0), NULL, 
       MATCH (band_name) AGAINST (@squery) + 
	      IF(band_name LIKE CONCAT('%', CAST(@squery AS CHAR CHARACTER SET utf8), '%'), 0.6, 0)  
FROM   bands 
ORDER  BY total_score DESC 
LIMIT  25;*/