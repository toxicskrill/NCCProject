

DELIMITER $$
CREATE PROCEDURE getDataPagingLogTimeSheet(start_get int(11), offset int(11))
BEGIN
    SELECT log.id, log.role, log.type, log.hours, log.date, p.projectName, log.description, u.username FROM log_time_sheet log INNER JOIN project p ON  log.project_id = p.project_id INNER JOIN user u ON u.id = log.user_id LIMIT start_get, offset;
END; $$



DELIMITER $$
CREATE PROCEDURE findLogTimeSheetByUserId(user_id bigint)
BEGIN
    SELECT log.id, log.project_id, log.role, type, hours, projectName, log.date, log.description, u.username FROM log_time_sheet log INNER JOIN project ON  log.project_id = project.project_id INNER JOIN user u ON u.id = log.user_id WHERE log.user_id = user_id;
END; $$



DELIMITER $$
CREATE PROCEDURE getUserDataPaging(start_get int(11), offset int(11))
BEGIN
    SELECT u.id, u.username, u.role, ui.job FROM user u INNER JOIN user_info ui ON  u.id = ui.user_id LIMIT start_get, offset;
END; $$



use ncc;
DELIMITER $$
CREATE PROCEDURE `filter`(in field varchar(40), in giatri varchar(40),in index_of_page int, in page_size int)
BEGIN
   
    declare xx int ;
        set xx = (index_of_page-1)*page_size;
    if (field = 'projectName') then
        SET @Sql = CONCAT("SELECT log.id , log.role , log.type , log.hours , log.date , p.projectName , log.description , u.username FROM log_time_sheet log INNER JOIN project p ON  log.project_id = p.project_id INNER JOIN user u ON u.id = log.user_id where p.", field, " like CONCAT('%','" ,giatri,"','%') limit ", xx ,",",page_size);
    elseif (field = 'username') then
        SET @Sql = CONCAT("SELECT log.id , log.role , log.type , log.hours , log.date , p.projectName , log.description , u.username FROM log_time_sheet log INNER JOIN project p ON  log.project_id = p.project_id INNER JOIN user u ON u.id = log.user_id where u.", field, " like CONCAT('%','" ,giatri,"','%') limit ", xx ,",",page_size);
	 elseif (field = '') then
        SET @Sql = CONCAT("SELECT log.id, log.role, log.type, log.hours, log.date, p.projectName, log.description, u.username FROM log_time_sheet log INNER JOIN project p ON  log.project_id = p.project_id INNER JOIN user u ON u.id = log.user_id limit ", xx ,",",page_size);
    else 
        SET @Sql = CONCAT("SELECT log.id , log.role , log.type , log.hours , log.date , p.projectName , log.description , u.username FROM log_time_sheet log INNER JOIN project p ON  log.project_id = p.project_id INNER JOIN user u ON u.id = log.user_id where log.", field, " like CONCAT('%','" ,giatri,"','%') limit ", xx ,",",page_size);
    end if;
 
    
    PREPARE STMT FROM @Sql; 
    EXECUTE STMT; 
    DEALLOCATE PREPARE STMT;
END; $$





DELIMITER $$
CREATE PROCEDURE `filter_count_all`(in field varchar(40), in giatri varchar(40))
BEGIN
   
    if (field = 'projectName') then
        SET @Sql = CONCAT("SELECT count(log.id) FROM log_time_sheet log INNER JOIN project p ON  log.project_id = p.project_id INNER JOIN user u ON u.id = log.user_id where p.", field, " like CONCAT('%','" ,giatri,"','%') ");
    elseif (field = 'username') then
        SET @Sql = CONCAT("SELECT count(log.id) FROM log_time_sheet log INNER JOIN project p ON  log.project_id = p.project_id INNER JOIN user u ON u.id = log.user_id where u.", field, " like CONCAT('%','" ,giatri,"','%')");
	 elseif (field = '') then
        SET @Sql = CONCAT("SELECT count(log.id) FROM log_time_sheet log INNER JOIN project p ON  log.project_id = p.project_id INNER JOIN user u ON u.id = log.user_id ");
    else 
        SET @Sql = CONCAT("SELECT count(log.id) FROM log_time_sheet log INNER JOIN project p ON  log.project_id = p.project_id INNER JOIN user u ON u.id = log.user_id where log.", field, " like CONCAT('%','" ,giatri,"','%')");
    end if;
 
    
    PREPARE STMT FROM @Sql; 
    EXECUTE STMT; 
    DEALLOCATE PREPARE STMT;
END; $$




use ncc;
DELIMITER $$
CREATE PROCEDURE `filter_user`(in field varchar(40), in giatri varchar(40),in index_of_page int, in page_size int)
BEGIN
   
    declare xx int ;
        set xx = (index_of_page-1)*page_size;
    if (field = 'job') then
        SET @Sql = CONCAT("SELECT u.id, u.username, u.role, ui.job FROM user u INNER JOIN user_info ui ON  u.id = ui.user_id  where ui.", field, " like CONCAT('%','" ,giatri,"','%') limit ", xx ,",",page_size);
    
    else 
        SET @Sql = CONCAT("SELECT u.id, u.username, u.role, ui.job FROM user u INNER JOIN user_info ui ON  u.id = ui.user_id  where u.", field, " like CONCAT('%','" ,giatri,"','%') limit ", xx ,",",page_size);
    end if;
 
    
    PREPARE STMT FROM @Sql; 
    EXECUTE STMT; 
    DEALLOCATE PREPARE STMT;
END; $$

use ncc;
DELIMITER $$
CREATE PROCEDURE `user_filter_count_all`(in field varchar(40), in giatri varchar(40))
BEGIN
   
    if (field = 'job') then
        SET @Sql = CONCAT("SELECT count(u.id) FROM user u INNER JOIN user_info ui ON  u.id = ui.user_id  where ui.", field, " like CONCAT('%','" ,giatri,"','%')");
    
    else 
        SET @Sql = CONCAT("SELECT count(u.id) FROM user u INNER JOIN user_info ui ON  u.id = ui.user_id  where u.", field, " like CONCAT('%','" ,giatri,"','%')");
    end if;
 
    
    PREPARE STMT FROM @Sql; 
    EXECUTE STMT; 
    DEALLOCATE PREPARE STMT;
END; $$
