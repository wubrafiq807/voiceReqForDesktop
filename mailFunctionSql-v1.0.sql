ALTER TABLE weekly_report_history
CHANGE COLUMN `created_at` `created_at` TIMESTAMP(6) NOT NULL DEFAULT 
CURRENT_TIMESTAMP(6);

ALTER TABLE daily_report_history 
CHANGE COLUMN `created_at` `created_at` TIMESTAMP(6) NOT NULL DEFAULT 
CURRENT_TIMESTAMP(6);


--
-- Triggers `weekly_report`
--
DROP TRIGGER IF EXISTS weekly_report_insert;
DELIMITER $$
CREATE TRIGGER `weekly_report_insert` AFTER INSERT ON `weekly_report` FOR EACH ROW INSERT weekly_report_history(report_id,report_status) VALUES(NEW.report_id,NEW.last_status)
$$
DELIMITER ;
DROP TRIGGER IF EXISTS weekly_report_update;
DELIMITER $$
CREATE TRIGGER `weekly_report_update` AFTER UPDATE ON `weekly_report` FOR EACH ROW INSERT weekly_report_history(report_id,report_status) VALUES(NEW.report_id,NEW.last_status)
$$
DELIMITER ;


--
-- Triggers `daily_report`
--
DROP TRIGGER IF EXISTS daily_report_insert;
DELIMITER $$
CREATE TRIGGER `daily_report_insert` AFTER INSERT ON `daily_report` FOR EACH ROW INSERT daily_report_history(report_id,report_status) VALUES(NEW.report_id,NEW.last_status)
$$
DELIMITER ;
DROP TRIGGER IF EXISTS daily_report_update;
DELIMITER $$
CREATE TRIGGER `daily_report_update` AFTER UPDATE ON `daily_report` FOR EACH ROW INSERT daily_report_history(report_id,report_status) VALUES(NEW.report_id,NEW.last_status)
$$
DELIMITER ;

--
-- PROCEDURE `get_report_list_with_user_id`
--
DROP PROCEDURE IF EXISTS get_report_list_with_user_id;

DELIMITER $$
CREATE  PROCEDURE `get_report_list_with_user_id`(IN `p_user_id` VARCHAR(100), IN `p_authority_id` INT(11))
BEGIN
	DROP TABLE IF EXISTS weekly_report_history_tmp;
	CREATE TABLE weekly_report_history_tmp
		SELECT REPLACE(GROUP_CONCAT(created_at ORDER BY report_status DESC SEPARATOR '<br>'), '-', '/') AS created_at, report_id,report_status FROM
			(SELECT created_at, report_id,report_status FROM
				(SELECT CONCAT(INSERT(SUBSTRING(wrh.created_at,1,19), 11, 0,CONCAT('(',SUBSTRING( DAYNAME(wrh.created_at),1,1),')')),rs.description) AS created_at, wrh.report_id,wrh.report_status FROM 
					(SELECT a.* FROM   weekly_report_history a 
					 WHERE  a.report_status = 0
						AND (
							SELECT COUNT(*) 
							FROM   weekly_report_history b
							WHERE  b.report_id = a.report_id AND b.created_at <= a.created_at and b.report_status = 0
							) <= 1
					) wrh, report_status rs
					WHERE rs.status = wrh.report_status and wrh.report_status = 0
					GROUP BY wrh.report_id, wrh.created_at
					ORDER BY wrh.report_status DESC
				) tb1
			UNION
			SELECT created_at, report_id,report_status FROM
				(SELECT CONCAT(INSERT(SUBSTRING(wrh.created_at,1,19), 11, 0, CONCAT('(',SUBSTRING( DAYNAME(wrh.created_at),1,1),')')),rs.description) AS created_at, wrh.report_id,wrh.report_status FROM 
					(SELECT a.* FROM   weekly_report_history a
					WHERE   a.report_status = 1
						AND (
							SELECT COUNT(*) 
							FROM   weekly_report_history b
							WHERE  b.report_id = a.report_id AND b.created_at <= a.created_at and b.report_status = 1
							) <= 1
					) wrh, report_status rs
				WHERE rs.status = wrh.report_status and wrh.report_status = 1
				GROUP BY wrh.report_id, wrh.created_at
				ORDER BY wrh.report_status DESC
				) tb2
			UNION
			SELECT created_at, report_id,report_status FROM
				(SELECT CONCAT(INSERT(SUBSTRING(wrh.created_at,1,19), 11, 0, CONCAT('(',SUBSTRING( DAYNAME(wrh.created_at),1,1),')')),rs.description) AS created_at, wrh.report_id,wrh.report_status FROM 
					(SELECT a.* FROM   weekly_report_history a
					WHERE   a.report_status = 1
						AND (
							SELECT COUNT(*) 
							FROM   weekly_report_history b
							WHERE  b.report_id = a.report_id AND b.created_at >= a.created_at and b.report_status = 1
							) <= 1
					) wrh, report_status rs
				WHERE rs.status = wrh.report_status and wrh.report_status = 1
				GROUP BY wrh.report_id, wrh.created_at
				ORDER BY wrh.report_status DESC
				) tb3
			UNION
			SELECT created_at, report_id,report_status FROM
				(SELECT CONCAT(INSERT(SUBSTRING(wrh.created_at,1,19), 11, 0, CONCAT('(',SUBSTRING( DAYNAME(wrh.created_at),1,1),')')),rs.description) AS created_at, wrh.report_id,wrh.report_status FROM 
					(SELECT a.* FROM   weekly_report_history a
					WHERE   a.report_status = 4
						AND (
							SELECT COUNT(*) 
							FROM   weekly_report_history b
							WHERE  b.report_id = a.report_id AND b.created_at >= a.created_at and b.report_status = 4
							) <= 1
					) wrh, report_status rs
				WHERE rs.status = wrh.report_status and wrh.report_status = 4
				GROUP BY wrh.report_id, wrh.created_at
				ORDER BY wrh.report_status DESC
				) tb4	
			) tb
			GROUP BY report_id;
	
	DROP TABLE IF EXISTS daily_report_history_tmp;
	CREATE TABLE daily_report_history_tmp
		SELECT REPLACE(GROUP_CONCAT(created_at ORDER BY report_status DESC SEPARATOR '<br>'), '-', '/') AS created_at, report_id,report_status FROM
			(SELECT created_at, report_id,report_status FROM
				(SELECT CONCAT(INSERT(SUBSTRING(drh.created_at,1,19), 11, 0, CONCAT('(',SUBSTRING( DAYNAME(drh.created_at),1,1),')')),rs.description) AS created_at, drh.report_id,drh.report_status FROM 
					(SELECT a.* FROM   daily_report_history a 
					 WHERE  a.report_status = 2
						AND (
							SELECT COUNT(*) 
							FROM   daily_report_history b
							WHERE  b.report_id = a.report_id AND b.created_at <= a.created_at and b.report_status = 2
							) <= 1
					) drh, report_status rs
					WHERE rs.status = drh.report_status and drh.report_status = 2
					GROUP BY drh.report_id, drh.created_at
					ORDER BY drh.report_status DESC
				) tb1
			UNION
			SELECT created_at, report_id,report_status FROM
				(SELECT CONCAT(INSERT(SUBSTRING(drh.created_at,1,19), 11, 0, CONCAT('(',SUBSTRING(DAYNAME(drh.created_at),1,1),')')),rs.description) AS created_at, drh.report_id,drh.report_status FROM 
					(SELECT a.* FROM   daily_report_history a
					WHERE   a.report_status = 3
						AND (
							SELECT COUNT(*) 
							FROM   daily_report_history b
							WHERE  b.report_id = a.report_id AND b.created_at <= a.created_at and b.report_status = 3
							) <= 1
					) drh, report_status rs
				WHERE rs.status = drh.report_status and drh.report_status = 3
				GROUP BY drh.report_id, drh.created_at
				ORDER BY drh.report_status DESC
				) tb2
			UNION
			SELECT created_at, report_id,report_status FROM
				(SELECT CONCAT(INSERT(SUBSTRING(drh.created_at,1,19), 11, 0, CONCAT('(',SUBSTRING(DAYNAME(drh.created_at),1,1),')')),rs.description) AS created_at, drh.report_id,drh.report_status FROM 
					(SELECT a.* FROM   daily_report_history a
					WHERE   a.report_status = 3
						AND (
							SELECT COUNT(*) 
							FROM   daily_report_history b
							WHERE  b.report_id = a.report_id AND b.created_at >= a.created_at and b.report_status = 3
							) <= 1
					) drh, report_status rs
				WHERE rs.status = drh.report_status and drh.report_status = 3
				GROUP BY drh.report_id, drh.created_at
				ORDER BY drh.report_status DESC
				) tb3
			UNION
			SELECT created_at, report_id,report_status FROM
				(SELECT CONCAT(INSERT(SUBSTRING(drh.created_at,1,19), 11, 0, CONCAT('(',SUBSTRING(DAYNAME(drh.created_at),1,1),')')),rs.description) AS created_at, drh.report_id,drh.report_status FROM 
					(SELECT a.* FROM   daily_report_history a
					WHERE   a.report_status = 4
						AND (
							SELECT COUNT(*) 
							FROM   daily_report_history b
							WHERE  b.report_id = a.report_id AND b.created_at >= a.created_at and b.report_status = 4
							) <= 1
					) drh, report_status rs
				WHERE rs.status = drh.report_status and drh.report_status = 4
				GROUP BY drh.report_id, drh.created_at
				ORDER BY drh.report_status DESC
				) tb4	
			) tb
			GROUP BY report_id;
			
	IF p_authority_id >= 2 THEN
		SELECT * FROM
		(
		(SELECT (SELECT u.user_name FROM user u WHERE u.user_id = wr.user_id) AS name,
			wr.title AS title,
             (SELECT department.name  FROM `user` JOIN  department on (user.department=department.department_id) 
			WHERE user.user_id = wr.user_id) as department,	
            (SELECT section.name  FROM `user` JOIN  section on (user.section=section.section_id) 
			WHERE user.user_id = wr.user_id) as section, 
			(SELECT rs.description FROM report_status rs WHERE rs.status = wr.last_status) AS last_status,
			(SELECT created_at FROM weekly_report_history_tmp wtmp
				WHERE wtmp.report_id = wr.report_id
			) AS created_at,
			wr.user_id AS user_id,
			wr.report_id AS report_id,
			(SELECT wrh.created_at FROM weekly_report_history wrh
				WHERE wrh.report_id = wr.report_id 
				ORDER BY wrh.created_at DESC
				LIMIT 1) AS last_edit
			FROM weekly_report wr
			WHERE wr.user_id = p_user_id)
		
		UNION

		(SELECT (SELECT u.user_name FROM user u WHERE u.user_id = a.user_id) AS name,
			a.title AS title,
            (SELECT department.name  FROM `user` JOIN  department on (user.department=department.department_id) 
			WHERE user.user_id = a.user_id) as department,
          (SELECT section.name  FROM `user` JOIN  section on (user.section=section.section_id) 
			WHERE user.user_id = a.user_id) as section,			
			(SELECT d.description FROM report_status d WHERE d.status = a.last_status) AS last_status,
			(SELECT created_at FROM daily_report_history_tmp dtmp
				WHERE dtmp.report_id = a.report_id
			) AS created_at,
			a.user_id AS user_id,
			a.report_id AS report_id,
			(SELECT drh.created_at FROM daily_report_history drh
				WHERE drh.report_id = a.report_id
				ORDER BY drh.created_at DESC
				LIMIT 1) AS last_edit
		FROM daily_report a
		WHERE a.user_id = p_user_id)
		) tb
		ORDER BY last_edit DESC;                    
		
	ELSE
		SELECT * FROM
		(
		(SELECT (SELECT u.user_name FROM user u WHERE u.user_id = wr.user_id) AS name,
			wr.title AS title,
             (SELECT department.name  FROM `user` JOIN  department on (user.department=department.department_id) 
			WHERE user.user_id = wr.user_id) as department,	
            (SELECT section.name  FROM `user` JOIN  section on (user.section=section.section_id) 
			WHERE user.user_id = wr.user_id) as section,			
			(SELECT rs.description FROM report_status rs WHERE rs.status = wr.last_status) AS last_status,
			(SELECT created_at FROM weekly_report_history_tmp wtmp
				WHERE wtmp.report_id = wr.report_id
			) AS created_at,
			wr.user_id AS user_id,
			wr.report_id AS report_id,
			(SELECT wrh.created_at FROM weekly_report_history wrh
				WHERE wrh.report_id = wr.report_id 
				ORDER BY wrh.created_at DESC
				LIMIT 1) AS last_edit
			FROM weekly_report wr
			WHERE wr.user_id = p_user_id)
		
		UNION

		(SELECT (SELECT u.user_name FROM user u WHERE u.user_id = a.user_id) AS name,
			a.title AS title,
            (SELECT department.name  FROM `user` JOIN  department on (user.department=department.department_id) 
			WHERE user.user_id = a.user_id) as department,
             (SELECT section.name  FROM `user` JOIN  section on (user.section=section.section_id) 
			WHERE user.user_id = a.user_id) as section,			
			(SELECT d.description FROM report_status d WHERE d.status = a.last_status) AS last_status,
			(SELECT created_at FROM daily_report_history_tmp dtmp
				WHERE dtmp.report_id = a.report_id
			) AS created_at,
			a.user_id AS user_id,
			a.report_id AS report_id,
			(SELECT drh.created_at FROM daily_report_history drh
				WHERE drh.report_id = a.report_id
				ORDER BY drh.created_at DESC
				LIMIT 1) AS last_edit
		FROM daily_report a
		WHERE a.user_id = p_user_id)
		
		UNION
		
		(SELECT (SELECT u.user_name FROM user u WHERE u.user_id = wr.user_id) AS name,
			wr.title AS title,
            (SELECT department.name  FROM `user` JOIN  department on (user.department=department.department_id)  
			WHERE user.user_id = wr.user_id) as department, 
            (SELECT section.name  FROM `user` JOIN  section on (user.section=section.section_id) 
			WHERE user.user_id = wr.user_id) as section,			
			(SELECT rs.description FROM report_status rs WHERE rs.status = wr.last_status) AS last_status,
			(SELECT created_at FROM weekly_report_history_tmp wtmp
				WHERE wtmp.report_id = wr.report_id
			) AS created_at,
			wr.user_id AS user_id,
			wr.report_id AS report_id,
			(SELECT wrh.created_at FROM weekly_report_history wrh
				WHERE wrh.report_id = wr.report_id 
				ORDER BY wrh.created_at DESC
				LIMIT 1) AS last_edit
			FROM weekly_report wr
			WHERE wr.user_id in (SELECT user_id FROM user WHERE  wr.last_status != 0))
		
		UNION

		(SELECT (SELECT u.user_name FROM user u WHERE u.user_id = a.user_id) AS name,
			a.title AS title,
            (SELECT department.name  FROM `user` JOIN  department on (user.department=department.department_id) 
			WHERE user.user_id = a.user_id) as department,
            (SELECT section.name  FROM `user` JOIN  section on (user.section=section.section_id) 
			WHERE user.user_id = a.user_id) as section,			
			(SELECT d.description FROM report_status d WHERE d.status = a.last_status AND a.last_status != 2) AS last_status,
			(SELECT created_at FROM daily_report_history_tmp dtmp
				WHERE dtmp.report_id = a.report_id
			) AS created_at,
			a.user_id AS user_id,
			a.report_id AS report_id,
			(SELECT drh.created_at FROM daily_report_history drh
				WHERE drh.report_id = a.report_id
				ORDER BY drh.created_at DESC
				LIMIT 1) AS last_edit
		FROM daily_report a
		WHERE a.user_id in (SELECT user_id FROM user WHERE  a.last_status != 2))
		) tb
		ORDER BY last_edit DESC;
		
	END IF;
	
	DROP TABLE IF EXISTS weekly_report_history_tmp;
	DROP TABLE IF EXISTS daily_report_history_tmp;
END$$
DELIMITER ;