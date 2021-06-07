CREATE DATABASE  IF NOT EXISTS `course_portal`;
USE `course_portal`;

DROP TABLE IF EXISTS `authorities`;
DROP TABLE IF EXISTS `course_student`;
DROP TABLE IF EXISTS `course`;
DROP TABLE IF EXISTS `users`;

--
-- Table structure for table `employee`
--

CREATE TABLE `course` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) DEFAULT NULL,
  `duration` varchar(45) DEFAULT NULL,
  `rating` real DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

--
-- Data for table `employee`
--

INSERT INTO `course` VALUES
	(1,'Spring with Hibernate','30 hours',4.5),
	(2,'Problem Solving with Java','23 hours',4.7),
	(3,'Linux Essentials','12 hours',4.2),
	(4,'Networking Basics','17 hours',3.9),
    (5,'Solid Principles','8 hours',4);



--
-- Table structure for table `users`
--


CREATE TABLE `users` (
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`username`)
);

--
-- Inserting data for table `users`
--

INSERT INTO `users`
VALUES
('john','{noop}test123',1),
('student','{noop}test123',1),
('admin','{noop}test123',1);


--
-- Table structure for table `authorities`
--

CREATE TABLE `authorities` (
  `username` varchar(50) NOT NULL,
  `authority` varchar(50) NOT NULL,
  UNIQUE KEY `authorities_idx_1` (`username`,`authority`),
  CONSTRAINT `authorities_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
);

--
-- Inserting data for table `authorities`
--

INSERT INTO `authorities`
VALUES
('john','ROLE_Student'),
('student','ROLE_Student'),
('admin','ROLE_Admin');



--
-- Table structure for table `course_student`
--

CREATE TABLE `course_student` (
  `username` varchar(50) NOT NULL,
  `course_id` int(11) NOT NULL,
UNIQUE KEY `course_student_idx_1` (`username`,`course_id`),
CONSTRAINT `course_student_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`),
CONSTRAINT `course_student_ibfk_2` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`)

);

