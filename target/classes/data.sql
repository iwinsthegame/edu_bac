-- MySQL dump 10.13  Distrib 9.4.0, for macos15 (x86_64)
--
-- Host: localhost    Database: edufortest
-- ------------------------------------------------------
-- Server version	9.4.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `attempt_questions`
--
SET FOREIGN_KEY_CHECKS=0;


DROP TABLE IF EXISTS `attempt_questions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `attempt_questions` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `is_correct` bit(1) DEFAULT NULL,
  `selected_option_ids` varchar(255) DEFAULT NULL,
  `time_spent_seconds` bigint DEFAULT NULL,
  `attempt_id` bigint DEFAULT NULL,
  `question_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6et0d2yu4ol6isy9atpatgftb` (`attempt_id`),
  KEY `FK1rfdj8x8i6k85bqldau9g43u8` (`question_id`),
  CONSTRAINT `FK1rfdj8x8i6k85bqldau9g43u8` FOREIGN KEY (`question_id`) REFERENCES `questions` (`id`),
  CONSTRAINT `FK6et0d2yu4ol6isy9atpatgftb` FOREIGN KEY (`attempt_id`) REFERENCES `student_attempts` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attempt_questions`
--

LOCK TABLES `attempt_questions` WRITE;
/*!40000 ALTER TABLE `attempt_questions` DISABLE KEYS */;
INSERT INTO `attempt_questions` VALUES (1,_binary '','2',1,3,1),(2,_binary '','7',1,3,2),(3,_binary '','9',1,3,3),(4,_binary '','14',1,3,4),(5,_binary '','17',1,3,5),(6,_binary '\0','22',1,3,6),(7,_binary '','26',1,3,7),(8,_binary '\0','31',1,3,8),(9,_binary '\0','36',1,3,9),(10,_binary '\0','38',1,3,10),(11,_binary '','53',1,3,14),(12,_binary '\0','61',1,3,16),(13,_binary '','94',1,3,24),(14,_binary '','179',1,3,45),(15,NULL,'2',1,5,1),(16,NULL,'8',1,5,2),(17,NULL,'',1,5,3),(18,NULL,'15',1,5,4),(19,_binary '','242',1,22,61),(20,_binary '','245',1,22,62),(21,_binary '','249',1,24,63),(22,_binary '','2',1,27,1),(23,_binary '','7',1,27,2),(24,_binary '','9',1,27,3),(25,_binary '','14',1,28,4),(26,_binary '','17',1,28,5),(27,_binary '','21',1,28,6),(28,_binary '','2',1,29,1),(29,_binary '','7',1,29,2),(30,_binary '','261',1,32,66),(31,_binary '','265',1,32,67),(32,_binary '','269',1,32,68),(33,NULL,'2',1,35,1),(34,NULL,'7',1,35,2),(35,NULL,'11',1,35,3);
/*!40000 ALTER TABLE `attempt_questions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `discussion`
--

DROP TABLE IF EXISTS `discussion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `discussion` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `accepted` bit(1) NOT NULL,
  `content` text,
  `created_at` datetime(6) DEFAULT NULL,
  `entity_id` bigint DEFAULT NULL,
  `entity_type` enum('MOCK_TEST','QUESTION','TEST_SERIES') DEFAULT NULL,
  `parent_id` bigint DEFAULT NULL,
  `upvotes` int NOT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `discussion`
--

LOCK TABLES `discussion` WRITE;
/*!40000 ALTER TABLE `discussion` DISABLE KEYS */;
INSERT INTO `discussion` VALUES (1,_binary '\0','Is this test series enough for full syllabus coverage?','2025-12-24 17:27:17.551676',2,'TEST_SERIES',NULL,0,5),(2,_binary '\0','How close are the questions to the real exam level?','2025-12-24 17:27:37.265881',2,'TEST_SERIES',NULL,2,5),(3,_binary '\0','yes','2025-12-24 17:28:00.312065',2,'TEST_SERIES',1,0,5),(4,_binary '\0','very close','2025-12-25 10:13:16.722068',2,'TEST_SERIES',2,0,5),(5,_binary '\0','yes, full syllabus','2025-12-25 10:13:51.634031',2,'TEST_SERIES',1,0,5),(6,_binary '\0','This test series is designed to simulate real exam conditions. Attempt tests in a timed environment and analyze your mistakes carefully. Focus more on accuracy than speed in the beginning.','2025-12-25 17:27:49.596915',1,'TEST_SERIES',NULL,3,5),(7,_binary '\0','great','2025-12-25 17:28:12.163969',1,'TEST_SERIES',6,0,5),(8,_binary '\0','yes','2025-12-27 08:11:06.109683',1,'TEST_SERIES',6,0,5);
/*!40000 ALTER TABLE `discussion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `exam_category`
--

DROP TABLE IF EXISTS `exam_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `exam_category` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `logo_url` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKl8k4dmswin6posqg5dv2n9n9r` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exam_category`
--

LOCK TABLES `exam_category` WRITE;
/*!40000 ALTER TABLE `exam_category` DISABLE KEYS */;
INSERT INTO `exam_category` VALUES (1,'Staff Selection Commission exams for government jobs.','/uploads/logos/ssc.png','SSC'),(2,'Bank PO, Clerk and Specialist Officer exams.','/uploads/logos/banking.png','Banking'),(3,'RRB NTPC, Group D and technical exams.','/uploads/logos/railway.png','Railway'),(4,'Civil Services and other UPSC examinations.','/uploads/logos/upsc.png','UPSC'),(5,'State Public Service Commission exams.','/uploads/logos/state-psc.png','State PSC'),(6,'NDA, CDS, AFCAT and defence entry exams.','/uploads/logos/defence.png','Defence'),(7,'CTET, TET and teaching eligibility exams.','/uploads/logos/teaching.png','Teaching'),(8,'GATE, ESE and engineering competitive exams.','/uploads/logos/engineering.png','Engineering'),(9,'Judicial services and law entrance exams.','/uploads/logos/judiciary.png','Judiciary'),(10,'Engineering entrance exams for IITs and NITs.','/uploads/logos/iit-jee.png','IIT-JEE'),(11,'Medical entrance exam for MBBS and BDS.','/uploads/logos/neet.png','NEET'),(12,'Defence entrance exam for Army, Navy and Air Force.','/uploads/logos/nda.png','NDA'),(13,'MBA entrance exam for IIMs and top B-schools.','/uploads/logos/cat.png','CAT'),(14,'MBA entrance exam for IIMs and top B-schools','/uploads/logos/cat.png','MBA'),(15,'this is for Law exam','/uploads/logos/clat.png','CLAT');
/*!40000 ALTER TABLE `exam_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `exam_sub_categories`
--

DROP TABLE IF EXISTS `exam_sub_categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `exam_sub_categories` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `exam_category_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK38oa7mg3p5hjwk0jklt5w950g` (`exam_category_id`),
  CONSTRAINT `FK38oa7mg3p5hjwk0jklt5w950g` FOREIGN KEY (`exam_category_id`) REFERENCES `exam_category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exam_sub_categories`
--

LOCK TABLES `exam_sub_categories` WRITE;
/*!40000 ALTER TABLE `exam_sub_categories` DISABLE KEYS */;
INSERT INTO `exam_sub_categories` VALUES (1,NULL,'SSC CGL',1),(2,'Combined Higher Secondary Level exam','SSC CHSL',1),(3,'Multi Tasking Staff exam','SSC MTS',1),(4,'General Duty Constable exam','SSC GD',1),(5,'Probationary Officer exam','IBPS PO',2),(6,'Clerk level banking exam','IBPS Clerk',2),(7,'State Bank Probationary Officer exam','SBI PO',2),(8,'State Bank Clerk exam','SBI Clerk',2),(9,'Non-Technical Popular Categories exam','RRB NTPC',3),(10,'Group D recruitment exam','RRB Group D',3),(11,'Assistant Loco Pilot exam','RRB ALP',3),(12,'Civil Services Examination','UPSC CSE',4),(13,'Combined Defence Services exam','UPSC CDS',4),(14,'National Defence Academy exam','UPSC NDA',4),(15,'State civil services exam','State PCS',5),(16,'State police recruitment exam','State Police',5),(17,'National Defence Academy exam','NDA',6),(18,'Combined Defence Services exam','CDS',6),(19,'Air Force Common Admission Test','AFCAT',6),(20,'Central Teacher Eligibility Test','CTET',7),(21,'State Teacher Eligibility Test','TET',7),(22,'Kendriya Vidyalaya recruitment exam','KVS',7),(23,'Graduate Aptitude Test in Engineering','GATE',8),(24,'Engineering Services Examination','ESE',8),(25,'Civil Judge recruitment exam','Judicial Services',9),(26,'Law admission and eligibility exams','Law Entrance',9),(27,'Entrance exam for NITs and IIITs','JEE Main',10),(28,'Entrance exam for IITs','JEE Advanced',10),(29,'Undergraduate medical entrance exam','NEET UG',11),(30,'Postgraduate medical entrance exam','NEET PG',11),(31,'Army wing NDA exam','NDA Army',12),(32,'Navy wing NDA exam','NDA Navy',12),(33,'Air Force wing NDA exam','NDA Air Force',12),(34,'Quantitative Aptitude preparation','CAT Quant',13),(35,'Verbal Ability & Reading Comprehension','CAT VARC',13),(36,'Data Interpretation & Logical Reasoning','CAT DILR',13),(37,'Engineering exams for PSU recruitments','PSU Exams',8),(38,'Engineering recruitment exam for ISRO','ISRO',8),(39,'Defence R&D engineering recruitment exam','DRDO',8),(40,'Engineering exam for BARC scientific officers','BARC',8),(41,'Foundation preparation for engineering services','IES Foundation',8),(42,'Engineering recruitment exam for Indian Oil','IOCL',8),(43,'Engineering recruitment exam for NTPC','NTPC',8),(44,'Engineering recruitment exam for ONGC','ONGC',8),(45,'SSC CPO exam','SSC CPO',1),(46,'law exam','CLAT_1',15);
/*!40000 ALTER TABLE `exam_sub_categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `instruction_categories`
--

DROP TABLE IF EXISTS `instruction_categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `instruction_categories` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `display_order` int DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `instruction_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKc74qh5172yyo67kifeqnnmaey` (`instruction_id`),
  CONSTRAINT `FKc74qh5172yyo67kifeqnnmaey` FOREIGN KEY (`instruction_id`) REFERENCES `test_instructions` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `instruction_categories`
--

LOCK TABLES `instruction_categories` WRITE;
/*!40000 ALTER TABLE `instruction_categories` DISABLE KEYS */;
INSERT INTO `instruction_categories` VALUES (1,1,'General Instructions',1),(2,2,'Navigating to a Question',1),(3,3,'Answering a Question',1),(4,4,'Navigation Through Sections',1),(5,1,'General Instructions',2),(6,2,'Navigating to a Question',2),(7,3,'Answering a Question',2),(8,4,'Navigation Through Sections',2);
/*!40000 ALTER TABLE `instruction_categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `instruction_points`
--

DROP TABLE IF EXISTS `instruction_points`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `instruction_points` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `display_order` int DEFAULT NULL,
  `text` text,
  `category_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3a0um5wfrwyxfx5l1ny2tiylh` (`category_id`),
  CONSTRAINT `FK3a0um5wfrwyxfx5l1ny2tiylh` FOREIGN KEY (`category_id`) REFERENCES `instruction_categories` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `instruction_points`
--

LOCK TABLES `instruction_points` WRITE;
/*!40000 ALTER TABLE `instruction_points` DISABLE KEYS */;
INSERT INTO `instruction_points` VALUES (1,1,'Total of 1 Hour duration will be given to attempt all the questions.',1),(2,2,'The countdown timer on the screen shows remaining time.',1),(3,3,'When time ends, the exam submits automatically.',1),(4,4,'The question palette on the right helps you navigate.',1),(5,1,'Click a question number in the palette to go to that question.',2),(6,2,'Click Save & Next to save and move to the next question.',2),(7,3,'Click Mark for Review & Next to save, mark for review, and move next.',2),(8,4,'You can view the entire paper by clicking the Question Paper button.',2),(9,1,'To select an answer, click on the option button.',3),(10,2,'To change your answer, click on another option.',3),(11,3,'To save your answer, click Save & Next.',3),(12,4,'Mark for Review keeps your answer but marks it for later review.',3),(13,1,'You can move between sections anytime using the section tabs.',4),(14,2,'The timer is common for all sections and does not reset.',4),(15,1,'Total of 1 Hour duration will be given to attempt all the questions.',5),(16,2,'The countdown timer on the screen shows remaining time.',5),(17,3,'When time ends, the exam submits automatically.',5),(18,4,'The question palette on the right helps you navigate.',5),(19,1,'Click a question number in the palette to go to that question.',6),(20,2,'Click Save & Next to save and move to the next question.',6),(21,3,'Click Mark for Review & Next to save, mark for review, and move next.',6),(22,4,'You can view the entire paper by clicking the Question Paper button.',6),(23,1,'To select an answer, click on the option button.',7),(24,2,'To change your answer, click on another option.',7),(25,3,'To save your answer, click Save & Next.',7),(26,4,'Mark for Review keeps your answer but marks it for later review.',7),(27,1,'You can move between sections anytime using the section tabs.',8),(28,2,'The timer is common for all sections and does not reset.',8);
/*!40000 ALTER TABLE `instruction_points` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `instruction_sections`
--

DROP TABLE IF EXISTS `instruction_sections`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `instruction_sections` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `correct_marks` double DEFAULT NULL,
  `max_score` int DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `negative_marks` double DEFAULT NULL,
  `total_questions` int DEFAULT NULL,
  `instruction_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKeek6tx8vl0i67ht3a9y9aw5vr` (`instruction_id`),
  CONSTRAINT `FKeek6tx8vl0i67ht3a9y9aw5vr` FOREIGN KEY (`instruction_id`) REFERENCES `test_instructions` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `instruction_sections`
--

LOCK TABLES `instruction_sections` WRITE;
/*!40000 ALTER TABLE `instruction_sections` DISABLE KEYS */;
INSERT INTO `instruction_sections` VALUES (1,2,50,'General Intelligence',0.5,25,1),(2,2,50,'General Awareness',0.5,25,1),(3,2,50,'Quantitative Aptitude',0.5,25,1),(4,2,50,'English Comprehension',0.5,25,1),(5,2,50,'General Intelligence',0.5,25,2),(6,2,50,'General Awareness',0.5,25,2),(7,2,50,'Quantitative Aptitude',0.5,25,2),(8,2,50,'English Comprehension',0.5,25,2);
/*!40000 ALTER TABLE `instruction_sections` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `instruction_symbols`
--

DROP TABLE IF EXISTS `instruction_symbols`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `instruction_symbols` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `description` text,
  `label` varchar(255) DEFAULT NULL,
  `instruction_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKevd0rgkp19mhdpuft5j96pr1p` (`instruction_id`),
  CONSTRAINT `FKevd0rgkp19mhdpuft5j96pr1p` FOREIGN KEY (`instruction_id`) REFERENCES `test_instructions` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `instruction_symbols`
--

LOCK TABLES `instruction_symbols` WRITE;
/*!40000 ALTER TABLE `instruction_symbols` DISABLE KEYS */;
INSERT INTO `instruction_symbols` VALUES (1,'You have not visited this question yet.','not_visited',1),(2,'You visited the question but did not answer it.','not_answered',1),(3,'You have successfully answered this question.','answered',1),(4,'You have marked this question for review without answering it.','marked',1),(5,'The question is marked for review and also has an answer.','marked_and_answered',1),(6,'You have not visited this question yet.','not_visited',2),(7,'You visited the question but did not answer it.','not_answered',2),(8,'You have successfully answered this question.','answered',2),(9,'You have marked this question for review without answering it.','marked',2),(10,'The question is marked for review and also has an answer.','marked_and_answered',2);
/*!40000 ALTER TABLE `instruction_symbols` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mock_test`
--

DROP TABLE IF EXISTS `mock_test`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mock_test` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `duration_minutes` int DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `total_marks` int DEFAULT NULL,
  `total_questions` int DEFAULT NULL,
  `test_series_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3v601rc0si0vskhjkc8o6e95b` (`test_series_id`),
  CONSTRAINT `FK3v601rc0si0vskhjkc8o6e95b` FOREIGN KEY (`test_series_id`) REFERENCES `test_series` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=257 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mock_test`
--

LOCK TABLES `mock_test` WRITE;
/*!40000 ALTER TABLE `mock_test` DISABLE KEYS */;
INSERT INTO `mock_test` VALUES (1,60,_binary '','Quantitative Aptitude Test 1',NULL,25,1),(2,60,_binary '','Quantitative Aptitude Test 2',NULL,25,1),(3,60,_binary '','Reasoning Test 1',NULL,25,1),(4,60,_binary '','Reasoning Test 2',NULL,25,1),(5,60,_binary '','English Test 1',NULL,25,1),(6,60,_binary '','English Test 2',NULL,25,1),(7,60,_binary '','General Awareness Test 1',NULL,25,1),(8,60,_binary '','General Awareness Test 2',NULL,25,1),(9,60,_binary '','Quantitative Aptitude Test 1',NULL,25,2),(10,60,_binary '','Quantitative Aptitude Test 2',NULL,25,2),(11,60,_binary '','Reasoning Test 1',NULL,25,2),(12,60,_binary '','Reasoning Test 2',NULL,25,2),(13,60,_binary '','English Test 1',NULL,25,2),(14,60,_binary '','English Test 2',NULL,25,2),(15,60,_binary '','General Awareness Test 1',NULL,25,2),(16,60,_binary '','General Awareness Test 2',NULL,25,2),(17,60,_binary '','Quantitative Aptitude Test 1',NULL,25,3),(18,60,_binary '','Quantitative Aptitude Test 2',NULL,25,3),(19,60,_binary '','Reasoning Test 1',NULL,25,3),(20,60,_binary '','Reasoning Test 2',NULL,25,3),(21,60,_binary '','English Language Test 1',NULL,25,3),(22,60,_binary '','English Language Test 2',NULL,25,3),(23,60,_binary '','General Awareness Test 1',NULL,25,3),(24,60,_binary '','General Awareness Test 2',NULL,25,3),(25,60,_binary '','Quantitative Aptitude Test 1',NULL,25,4),(26,60,_binary '','Quantitative Aptitude Test 2',NULL,25,4),(27,60,_binary '','Reasoning Test 1',NULL,25,4),(28,60,_binary '','Reasoning Test 2',NULL,25,4),(29,60,_binary '','English Language Test 1',NULL,25,4),(30,60,_binary '','English Language Test 2',NULL,25,4),(31,60,_binary '','General Awareness Test 1',NULL,25,4),(32,60,_binary '','General Awareness Test 2',NULL,25,4),(33,45,_binary '','Numerical Aptitude Test 1',NULL,25,5),(34,45,_binary '','Numerical Aptitude Test 2',NULL,25,5),(35,45,_binary '','Reasoning Ability Test 1',NULL,25,5),(36,45,_binary '','Reasoning Ability Test 2',NULL,25,5),(37,45,_binary '','General Awareness Test 1',NULL,25,5),(38,45,_binary '','General Awareness Test 2',NULL,25,5),(39,45,_binary '','English Language Test 1',NULL,25,5),(40,45,_binary '','English Language Test 2',NULL,25,5),(41,45,_binary '','Numerical Aptitude Test 1',NULL,25,6),(42,45,_binary '','Numerical Aptitude Test 2',NULL,25,6),(43,45,_binary '','Reasoning Ability Test 1',NULL,25,6),(44,45,_binary '','Reasoning Ability Test 2',NULL,25,6),(45,45,_binary '','General Awareness Test 1',NULL,25,6),(46,45,_binary '','General Awareness Test 2',NULL,25,6),(47,45,_binary '','English Language Test 1',NULL,25,6),(48,45,_binary '','English Language Test 2',NULL,25,6),(49,45,_binary '','Numerical Aptitude Test 1',NULL,25,7),(50,45,_binary '','Numerical Aptitude Test 2',NULL,25,7),(51,45,_binary '','Reasoning Ability Test 1',NULL,25,7),(52,45,_binary '','Reasoning Ability Test 2',NULL,25,7),(53,45,_binary '','General Awareness Test 1',NULL,25,7),(54,45,_binary '','General Awareness Test 2',NULL,25,7),(55,45,_binary '','English Language Test 1',NULL,25,7),(56,45,_binary '','English Language Test 2',NULL,25,7),(57,45,_binary '','Numerical Aptitude Test 1',NULL,25,8),(58,45,_binary '','Numerical Aptitude Test 2',NULL,25,8),(59,45,_binary '','Reasoning Ability Test 1',NULL,25,8),(60,45,_binary '','Reasoning Ability Test 2',NULL,25,8),(61,45,_binary '','General Awareness Test 1',NULL,25,8),(62,45,_binary '','General Awareness Test 2',NULL,25,8),(63,45,_binary '','English Language Test 1',NULL,25,8),(64,45,_binary '','English Language Test 2',NULL,25,8),(65,45,_binary '','Numerical Aptitude Test 1',NULL,25,9),(66,45,_binary '','Numerical Aptitude Test 2',NULL,25,9),(67,45,_binary '','Reasoning Ability Test 1',NULL,25,9),(68,45,_binary '','Reasoning Ability Test 2',NULL,25,9),(69,45,_binary '','General Awareness Test 1',NULL,25,9),(70,45,_binary '','General Awareness Test 2',NULL,25,9),(71,45,_binary '','English Language Test 1',NULL,25,9),(72,45,_binary '','English Language Test 2',NULL,25,9),(73,45,_binary '','Quantitative Aptitude Test 1',NULL,35,10),(74,45,_binary '','Quantitative Aptitude Test 2',NULL,35,10),(75,45,_binary '','Reasoning Ability Test 1',NULL,35,10),(76,45,_binary '','Reasoning Ability Test 2',NULL,35,10),(77,30,_binary '','English Language Test 1',NULL,30,10),(78,30,_binary '','English Language Test 2',NULL,30,10),(79,25,_binary '','General Awareness Test 1',NULL,25,10),(80,25,_binary '','General Awareness Test 2',NULL,25,10),(81,45,_binary '','Quantitative Aptitude Test 1',NULL,35,11),(82,45,_binary '','Quantitative Aptitude Test 2',NULL,35,11),(83,45,_binary '','Reasoning Ability Test 1',NULL,35,11),(84,45,_binary '','Reasoning Ability Test 2',NULL,35,11),(85,30,_binary '','English Language Test 1',NULL,30,11),(86,30,_binary '','English Language Test 2',NULL,30,11),(87,25,_binary '','General Awareness Test 1',NULL,25,11),(88,25,_binary '','General Awareness Test 2',NULL,25,11),(89,45,_binary '','Quantitative Aptitude Test 1',NULL,35,12),(90,45,_binary '','Quantitative Aptitude Test 2',NULL,35,12),(91,45,_binary '','Reasoning Ability Test 1',NULL,35,12),(92,45,_binary '','Reasoning Ability Test 2',NULL,35,12),(93,30,_binary '','English Language Test 1',NULL,30,12),(94,30,_binary '','English Language Test 2',NULL,30,12),(95,25,_binary '','General Awareness Test 1',NULL,25,12),(96,25,_binary '','General Awareness Test 2',NULL,25,12),(97,35,_binary '','Numerical Ability Test 1',NULL,35,13),(98,35,_binary '','Numerical Ability Test 2',NULL,35,13),(99,35,_binary '','Reasoning Ability Test 1',NULL,35,13),(100,35,_binary '','Reasoning Ability Test 2',NULL,35,13),(101,20,_binary '','English Language Test 1',NULL,30,13),(102,20,_binary '','English Language Test 2',NULL,30,13),(103,20,_binary '','GA & Computer Test 1',NULL,30,13),(104,20,_binary '','GA & Computer Test 2',NULL,30,13),(105,35,_binary '','Numerical Ability Test 1',NULL,35,14),(106,35,_binary '','Numerical Ability Test 2',NULL,35,14),(107,35,_binary '','Reasoning Ability Test 1',NULL,35,14),(108,35,_binary '','Reasoning Ability Test 2',NULL,35,14),(109,20,_binary '','English Language Test 1',NULL,30,14),(110,20,_binary '','English Language Test 2',NULL,30,14),(111,20,_binary '','GA & Computer Test 1',NULL,30,14),(112,20,_binary '','GA & Computer Test 2',NULL,30,14),(113,45,_binary '','Quantitative Aptitude Test 1',NULL,35,15),(114,45,_binary '','Quantitative Aptitude Test 2',NULL,35,15),(115,45,_binary '','Reasoning Ability Test 1',NULL,35,15),(116,45,_binary '','Reasoning Ability Test 2',NULL,35,15),(117,30,_binary '','English Language Test 1',NULL,30,15),(118,30,_binary '','English Language Test 2',NULL,30,15),(119,30,_binary '','GA & Banking Awareness Test 1',NULL,30,15),(120,30,_binary '','GA & Banking Awareness Test 2',NULL,30,15),(121,45,_binary '','Quantitative Aptitude Test 1',NULL,35,16),(122,45,_binary '','Quantitative Aptitude Test 2',NULL,35,16),(123,45,_binary '','Reasoning Ability Test 1',NULL,35,16),(124,45,_binary '','Reasoning Ability Test 2',NULL,35,16),(125,30,_binary '','English Language Test 1',NULL,30,16),(126,30,_binary '','English Language Test 2',NULL,30,16),(127,30,_binary '','GA & Banking Awareness Test 1',NULL,30,16),(128,30,_binary '','GA & Banking Awareness Test 2',NULL,30,16),(129,45,_binary '','Mathematics Test 1',NULL,30,17),(130,45,_binary '','Mathematics Test 2',NULL,30,17),(131,45,_binary '','Reasoning Test 1',NULL,30,17),(132,45,_binary '','Reasoning Test 2',NULL,30,17),(133,30,_binary '','General Awareness Test 1',NULL,40,17),(134,30,_binary '','General Awareness Test 2',NULL,40,17),(135,90,_binary '','Full Mock Test 1',NULL,100,17),(136,90,_binary '','Full Mock Test 2',NULL,100,17),(137,45,_binary '','Mathematics Test 1',NULL,30,18),(138,45,_binary '','Mathematics Test 2',NULL,30,18),(139,45,_binary '','Reasoning Test 1',NULL,30,18),(140,45,_binary '','Reasoning Test 2',NULL,30,18),(141,30,_binary '','General Awareness Test 1',NULL,40,18),(142,30,_binary '','General Awareness Test 2',NULL,40,18),(143,90,_binary '','Full Mock Test 1',NULL,100,18),(144,90,_binary '','Full Mock Test 2',NULL,100,18),(145,120,_binary '','GS Paper I Test 1',NULL,100,19),(146,120,_binary '','GS Paper I Test 2',NULL,100,19),(147,120,_binary '','CSAT Test 1',NULL,80,19),(148,120,_binary '','CSAT Test 2',NULL,80,19),(149,60,_binary '','Polity Test',NULL,50,19),(150,60,_binary '','Economy Test',NULL,50,19),(151,60,_binary '','History Test',NULL,50,19),(152,60,_binary '','Geography Test',NULL,50,19),(153,120,_binary '','GS Paper I Test 1',NULL,100,20),(154,120,_binary '','GS Paper I Test 2',NULL,100,20),(155,120,_binary '','CSAT Test 1',NULL,80,20),(156,120,_binary '','CSAT Test 2',NULL,80,20),(157,60,_binary '','Polity Test',NULL,50,20),(158,60,_binary '','Economy Test',NULL,50,20),(159,60,_binary '','History Test',NULL,50,20),(160,60,_binary '','Geography Test',NULL,50,20),(161,45,_binary '','Quant Test 1',NULL,35,21),(162,45,_binary '','Quant Test 2',NULL,35,21),(163,45,_binary '','Reasoning Test 1',NULL,35,21),(164,45,_binary '','Reasoning Test 2',NULL,35,21),(165,30,_binary '','English Test 1',NULL,30,21),(166,30,_binary '','English Test 2',NULL,30,21),(167,30,_binary '','Banking Awareness Test 1',NULL,30,21),(168,30,_binary '','Banking Awareness Test 2',NULL,30,21),(169,30,_binary '','Mathematics Test 1',NULL,25,22),(170,30,_binary '','Mathematics Test 2',NULL,25,22),(171,30,_binary '','Reasoning Test 1',NULL,25,22),(172,30,_binary '','Reasoning Test 2',NULL,25,22),(173,30,_binary '','General Science Test 1',NULL,25,22),(174,30,_binary '','General Science Test 2',NULL,25,22),(175,30,_binary '','GK & Current Affairs Test 1',NULL,25,22),(176,30,_binary '','GK & Current Affairs Test 2',NULL,25,22),(177,120,_binary '','English Test 1',NULL,120,23),(178,120,_binary '','English Test 2',NULL,120,23),(179,120,_binary '','General Knowledge Test 1',NULL,120,23),(180,120,_binary '','General Knowledge Test 2',NULL,120,23),(181,120,_binary '','Elementary Mathematics Test 1',NULL,100,23),(182,120,_binary '','Elementary Mathematics Test 2',NULL,100,23),(183,180,_binary '','Full Mock Test 1',NULL,300,23),(184,180,_binary '','Full Mock Test 2',NULL,300,23),(185,120,_binary '','Mathematics Test 1',NULL,120,24),(186,120,_binary '','Mathematics Test 2',NULL,120,24),(187,60,_binary '','GAT English Test',NULL,50,24),(188,90,_binary '','GAT GK Test',NULL,100,24),(189,60,_binary '','Physics Test',NULL,50,24),(190,60,_binary '','Chemistry Test',NULL,50,24),(191,60,_binary '','Biology Test',NULL,50,24),(192,300,_binary '','Full Length NDA Mock',NULL,270,24),(193,120,_binary '','Maths Test 1',NULL,120,25),(194,120,_binary '','Maths Test 2',NULL,120,25),(195,60,_binary '','English Test',NULL,50,25),(196,90,_binary '','Science Test',NULL,100,25),(197,60,_binary '','Current Affairs Test',NULL,50,25),(198,60,_binary '','History Test',NULL,50,25),(199,60,_binary '','Geography Test',NULL,50,25),(200,300,_binary '','Full NDA Navy Mock',NULL,270,25),(201,40,_binary '','Arithmetic Test',NULL,22,26),(202,40,_binary '','Algebra Test',NULL,22,26),(203,40,_binary '','Geometry Test',NULL,22,26),(204,40,_binary '','Number System Test',NULL,22,26),(205,40,_binary '','Mixed Quant Test 1',NULL,22,26),(206,40,_binary '','Mixed Quant Test 2',NULL,22,26),(207,30,_binary '','Speed Maths Test',NULL,20,26),(208,40,_binary '','CAT Quant Full Section Mock',NULL,22,26),(209,40,_binary '','Data Interpretation Test 1',NULL,20,27),(210,40,_binary '','Data Interpretation Test 2',NULL,20,27),(211,40,_binary '','Logical Reasoning Test 1',NULL,20,27),(212,40,_binary '','Logical Reasoning Test 2',NULL,20,27),(213,30,_binary '','Caselet Test',NULL,15,27),(214,30,_binary '','Puzzle Test',NULL,15,27),(215,40,_binary '','Mixed DILR Test',NULL,20,27),(216,40,_binary '','CAT DILR Full Section Mock',NULL,20,27),(217,90,_binary '','Technical Subject Test 1',NULL,65,28),(218,90,_binary '','Technical Subject Test 2',NULL,65,28),(219,60,_binary '','Engineering Maths Test',NULL,40,28),(220,45,_binary '','General Aptitude Test',NULL,30,28),(221,45,_binary '','Reasoning Test',NULL,30,28),(222,30,_binary '','English Test',NULL,25,28),(223,120,_binary '','Previous Year Pattern Test',NULL,100,28),(224,180,_binary '','PSU Full Length Mock',NULL,140,28),(225,60,_binary '','Physics Test 1',NULL,25,29),(226,60,_binary '','Physics Test 2',NULL,25,29),(227,60,_binary '','Chemistry Test 1',NULL,25,29),(228,60,_binary '','Chemistry Test 2',NULL,25,29),(229,60,_binary '','Mathematics Test 1',NULL,25,29),(230,60,_binary '','Mathematics Test 2',NULL,25,29),(231,90,_binary '','Mixed PCM Test',NULL,45,29),(232,180,_binary '','Full Length JEE Main Mock',NULL,75,29),(233,90,_binary '','Physics Advanced Test 1',NULL,18,30),(234,90,_binary '','Physics Advanced Test 2',NULL,18,30),(235,90,_binary '','Chemistry Advanced Test 1',NULL,18,30),(236,90,_binary '','Chemistry Advanced Test 2',NULL,18,30),(237,90,_binary '','Mathematics Advanced Test 1',NULL,18,30),(238,90,_binary '','Mathematics Advanced Test 2',NULL,18,30),(239,180,_binary '','Paper 1 Mock',NULL,54,30),(240,180,_binary '','Paper 2 Mock',NULL,54,30),(241,60,_binary '','Physics Test 1',NULL,45,31),(242,60,_binary '','Physics Test 2',NULL,45,31),(243,60,_binary '','Chemistry Test 1',NULL,45,31),(244,60,_binary '','Chemistry Test 2',NULL,45,31),(245,60,_binary '','Biology Botany Test',NULL,45,31),(246,60,_binary '','Biology Zoology Test',NULL,45,31),(247,120,_binary '','Mixed PCB Test',NULL,90,31),(248,200,_binary '','Full Length NEET UG Mock',NULL,180,31),(249,60,_binary '','Anatomy Test',NULL,50,32),(250,60,_binary '','Physiology Test',NULL,50,32),(251,60,_binary '','Biochemistry Test',NULL,50,32),(252,60,_binary '','Pathology Test',NULL,50,32),(253,60,_binary '','Pharmacology Test',NULL,50,32),(254,60,_binary '','Microbiology Test',NULL,50,32),(255,90,_binary '','Clinical Subjects Test',NULL,75,32),(256,210,_binary '','Full Length NEET PG Mock',NULL,200,32);
/*!40000 ALTER TABLE `mock_test` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `options`
--

DROP TABLE IF EXISTS `options`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `options` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `is_correct` bit(1) DEFAULT NULL,
  `option_key` varchar(255) DEFAULT NULL,
  `option_text` varchar(1000) NOT NULL,
  `question_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5bmv46so2y5igt9o9n9w4fh6y` (`question_id`),
  CONSTRAINT `FK5bmv46so2y5igt9o9n9w4fh6y` FOREIGN KEY (`question_id`) REFERENCES `questions` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=273 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `options`
--

LOCK TABLES `options` WRITE;
/*!40000 ALTER TABLE `options` DISABLE KEYS */;
INSERT INTO `options` VALUES (1,_binary '\0','A','25',1),(2,_binary '','B','30',1),(3,_binary '\0','C','35',1),(4,_binary '\0','D','40',1),(5,_binary '\0','A','10',2),(6,_binary '\0','B','11',2),(7,_binary '','C','12',2),(8,_binary '\0','D','13',2),(9,_binary '','A','61',3),(10,_binary '\0','B','60',3),(11,_binary '\0','C','62',3),(12,_binary '\0','D','59',3),(13,_binary '\0','A','54',4),(14,_binary '','B','56',4),(15,_binary '\0','C','58',4),(16,_binary '\0','D','60',4),(17,_binary '','A','20',5),(18,_binary '\0','B','15',5),(19,_binary '\0','C','25',5),(20,_binary '\0','D','30',5),(21,_binary '','A','81',6),(22,_binary '\0','B','72',6),(23,_binary '\0','C','91',6),(24,_binary '\0','D','99',6),(25,_binary '\0','A','30',7),(26,_binary '','B','40',7),(27,_binary '\0','C','50',7),(28,_binary '\0','D','35',7),(29,_binary '','A','11',8),(30,_binary '\0','B','9',8),(31,_binary '\0','C','10',8),(32,_binary '\0','D','12',8),(33,_binary '','A','121',9),(34,_binary '\0','B','111',9),(35,_binary '\0','C','131',9),(36,_binary '\0','D','112',9),(37,_binary '','A','343',10),(38,_binary '\0','B','333',10),(39,_binary '\0','C','353',10),(40,_binary '\0','D','323',10),(41,_binary '','A','6',11),(42,_binary '\0','B','5',11),(43,_binary '\0','C','7',11),(44,_binary '\0','D','8',11),(45,_binary '\0','A','2',12),(46,_binary '','B','3',12),(47,_binary '\0','C','4',12),(48,_binary '\0','D','5',12),(49,_binary '','A','42',13),(50,_binary '\0','B','40',13),(51,_binary '\0','C','41',13),(52,_binary '\0','D','43',13),(53,_binary '','A','20',14),(54,_binary '\0','B','25',14),(55,_binary '\0','C','30',14),(56,_binary '\0','D','15',14),(57,_binary '','A','28',15),(58,_binary '\0','B','24',15),(59,_binary '\0','C','30',15),(60,_binary '\0','D','26',15),(61,_binary '\0','A','18',16),(62,_binary '\0','B','24',16),(63,_binary '','C','32',16),(64,_binary '\0','D','30',16),(65,_binary '\0','A','Dog',17),(66,_binary '\0','B','Cat',17),(67,_binary '\0','C','Cow',17),(68,_binary '','D','Sparrow',17),(69,_binary '\0','A','60',18),(70,_binary '','B','80',18),(71,_binary '\0','C','70',18),(72,_binary '\0','D','100',18),(73,_binary '\0','A','Circle',19),(74,_binary '\0','B','Triangle',19),(75,_binary '\0','C','Square',19),(76,_binary '','D','Apple',19),(77,_binary '\0','A','20',20),(78,_binary '','B','25',20),(79,_binary '\0','C','24',20),(80,_binary '\0','D','30',20),(81,_binary '\0','A','Mercury',21),(82,_binary '\0','B','Venus',21),(83,_binary '\0','C','Mars',21),(84,_binary '','D','Pluto',21),(85,_binary '\0','A','36',22),(86,_binary '','B','48',22),(87,_binary '\0','C','42',22),(88,_binary '\0','D','50',22),(89,_binary '\0','A','Rose',23),(90,_binary '\0','B','Lily',23),(91,_binary '\0','C','Tulip',23),(92,_binary '','D','Mango',23),(93,_binary '\0','A','24',24),(94,_binary '','B','26',24),(95,_binary '\0','C','25',24),(96,_binary '\0','D','28',24),(97,_binary '\0','A','21',25),(98,_binary '\0','B','24',25),(99,_binary '\0','C','27',25),(100,_binary '','D','31',25),(101,_binary '\0','A','Pen',26),(102,_binary '\0','B','Pencil',26),(103,_binary '\0','C','Eraser',26),(104,_binary '','D','Chair',26),(105,_binary '\0','A','60',27),(106,_binary '','B','120',27),(107,_binary '\0','C','100',27),(108,_binary '\0','D','150',27),(109,_binary '\0','A','Red',28),(110,_binary '\0','B','Blue',28),(111,_binary '\0','C','Green',28),(112,_binary '','D','Circle',28),(113,_binary '','A','160',29),(114,_binary '\0','B','150',29),(115,_binary '\0','C','140',29),(116,_binary '\0','D','120',29),(117,_binary '\0','A','Slow',30),(118,_binary '','B','Fast',30),(119,_binary '\0','C','Weak',30),(120,_binary '\0','D','Late',30),(121,_binary '\0','A','Recieve',31),(122,_binary '','B','Receive',31),(123,_binary '\0','C','Receeve',31),(124,_binary '\0','D','Receve',31),(125,_binary '','A','Selfish',32),(126,_binary '\0','B','Kind',32),(127,_binary '\0','C','Helpful',32),(128,_binary '\0','D','Polite',32),(129,_binary '\0','A','Cowardly',33),(130,_binary '','B','Fearless',33),(131,_binary '\0','C','Timid',33),(132,_binary '\0','D','Shy',33),(133,_binary '\0','A','Occured',34),(134,_binary '','B','Occurred',34),(135,_binary '\0','C','Ocurred',34),(136,_binary '\0','D','Occurrd',34),(137,_binary '\0','A','Truthful',35),(138,_binary '\0','B','Loyal',35),(139,_binary '','C','Dishonest',35),(140,_binary '\0','D','Kind',35),(141,_binary '','A','Small',36),(142,_binary '\0','B','Huge',36),(143,_binary '\0','C','Tall',36),(144,_binary '\0','D','Wide',36),(145,_binary '\0','A','Definately',37),(146,_binary '','B','Definitely',37),(147,_binary '\0','C','Definetly',37),(148,_binary '\0','D','Defanitely',37),(149,_binary '\0','A','Kind',38),(150,_binary '','B','Selfish',38),(151,_binary '\0','C','Helpful',38),(152,_binary '\0','D','Polite',38),(153,_binary '','A','Smart',39),(154,_binary '\0','B','Stupid',39),(155,_binary '\0','C','Dull',39),(156,_binary '\0','D','Slow',39),(157,_binary '\0','A','Seperate',40),(158,_binary '','B','Separate',40),(159,_binary '\0','C','Seperete',40),(160,_binary '\0','D','Seperat',40),(161,_binary '','A','Modern',41),(162,_binary '\0','B','Old',41),(163,_binary '\0','C','Historic',41),(164,_binary '\0','D','Vintage',41),(165,_binary '\0','A','Sad',42),(166,_binary '','B','Joyful',42),(167,_binary '\0','C','Angry',42),(168,_binary '\0','D','Upset',42),(169,_binary '\0','A','Acommodate',43),(170,_binary '','B','Accommodate',43),(171,_binary '\0','C','Acomodate',43),(172,_binary '\0','D','Acommadate',43),(173,_binary '','A','Easy',44),(174,_binary '\0','B','Hard',44),(175,_binary '\0','C','Tough',44),(176,_binary '\0','D','Challenging',44),(177,_binary '\0','A','Mahatma Gandhi',45),(178,_binary '\0','B','Jawaharlal Nehru',45),(179,_binary '','C','B. R. Ambedkar',45),(180,_binary '\0','D','Rajendra Prasad',45),(181,_binary '\0','A','Earth',46),(182,_binary '','B','Mars',46),(183,_binary '\0','C','Jupiter',46),(184,_binary '\0','D','Venus',46),(185,_binary '\0','A','Lion',47),(186,_binary '','B','Tiger',47),(187,_binary '\0','C','Elephant',47),(188,_binary '\0','D','Peacock',47),(189,_binary '\0','A','Africa',48),(190,_binary '','B','Asia',48),(191,_binary '\0','C','Europe',48),(192,_binary '\0','D','Antarctica',48),(193,_binary '','A','Rabindranath Tagore',49),(194,_binary '\0','B','Bankim Chandra Chatterjee',49),(195,_binary '\0','C','Sarojini Naidu',49),(196,_binary '\0','D','Kavi Pradeep',49),(197,_binary '\0','A','Oxygen',50),(198,_binary '','B','Carbon Dioxide',50),(199,_binary '\0','C','Nitrogen',50),(200,_binary '\0','D','Hydrogen',50),(201,_binary '\0','A','Mahatma Gandhi',51),(202,_binary '','B','Jawaharlal Nehru',51),(203,_binary '\0','C','Indira Gandhi',51),(204,_binary '\0','D','Sardar Patel',51),(205,_binary '\0','A','Amazon',52),(206,_binary '','B','Nile',52),(207,_binary '\0','C','Yangtze',52),(208,_binary '\0','D','Mississippi',52),(209,_binary '\0','A','China',53),(210,_binary '','B','Japan',53),(211,_binary '\0','C','Thailand',53),(212,_binary '\0','D','South Korea',53),(213,_binary '\0','A','Albert Einstein',54),(214,_binary '','B','Isaac Newton',54),(215,_binary '\0','C','Galileo Galilei',54),(216,_binary '\0','D','Nikola Tesla',54),(217,_binary '\0','A','Gold',55),(218,_binary '','B','Oxygen',55),(219,_binary '\0','C','Osmium',55),(220,_binary '\0','D','Iron',55),(221,_binary '\0','A','Atlantic Ocean',56),(222,_binary '','B','Pacific Ocean',56),(223,_binary '\0','C','Indian Ocean',56),(224,_binary '\0','D','Arctic Ocean',56),(225,_binary '','A','Alexander Graham Bell',57),(226,_binary '\0','B','Thomas Edison',57),(227,_binary '\0','C','Nikola Tesla',57),(228,_binary '\0','D','Guglielmo Marconi',57),(229,_binary '\0','A','Monaco',58),(230,_binary '','B','Vatican City',58),(231,_binary '\0','C','San Marino',58),(232,_binary '\0','D','Liechtenstein',58),(233,_binary '\0','A','K2',59),(234,_binary '','B','Mount Everest',59),(235,_binary '\0','C','Kangchenjunga',59),(236,_binary '\0','D','Lhotse',59),(241,_binary '\0','A','30',61),(242,_binary '','B','15',61),(243,_binary '\0','C','50',61),(244,_binary '\0','D','60',61),(245,_binary '','A','45',62),(246,_binary '\0','B','12',62),(247,_binary '\0','C','14',62),(248,_binary '\0','D','44',62),(249,_binary '','A','Mahatma Gandhi',63),(250,_binary '\0','B','mohan',63),(251,_binary '\0','C','jyoti',63),(252,_binary '\0','D','ritik',63),(253,_binary '\0','A','Earth',64),(254,_binary '','B','Mars',64),(255,_binary '\0','C','Jupiter',64),(256,_binary '\0','D','Venus',64),(257,_binary '\0','A','Gold',65),(258,_binary '','B','Oxygen',65),(259,_binary '\0','C','Osmium',65),(260,_binary '\0','D','Iron',65),(261,_binary '','A','A',66),(262,_binary '\0','B','b',66),(263,_binary '\0','C','d',66),(264,_binary '\0','D','f',66),(265,_binary '','A','d',67),(266,_binary '\0','B','98986875765745',67),(267,_binary '\0','C','wghg',67),(268,_binary '\0','D','434554647',67),(269,_binary '','A','awfras',68),(270,_binary '\0','B','67',68),(271,_binary '\0','C','78',68),(272,_binary '\0','D','3',68);
/*!40000 ALTER TABLE `options` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_entity`
--

DROP TABLE IF EXISTS `order_entity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_entity` (
  `id` bigint NOT NULL,
  `amount` int DEFAULT NULL,
  `razorpay_order_id` varchar(255) DEFAULT NULL,
  `status` enum('CREATED','FAILED','SUCCESS') DEFAULT NULL,
  `product_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK39ytj2bduaomcm7p4iue85jnx` (`product_id`),
  KEY `FK1aty8556l6ubkgne265npb41f` (`user_id`),
  CONSTRAINT `FK1aty8556l6ubkgne265npb41f` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FK39ytj2bduaomcm7p4iue85jnx` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_entity`
--

LOCK TABLES `order_entity` WRITE;
/*!40000 ALTER TABLE `order_entity` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_entity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_entity_seq`
--

DROP TABLE IF EXISTS `order_entity_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_entity_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_entity_seq`
--

LOCK TABLES `order_entity_seq` WRITE;
/*!40000 ALTER TABLE `order_entity_seq` DISABLE KEYS */;
INSERT INTO `order_entity_seq` VALUES (1);
/*!40000 ALTER TABLE `order_entity_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `id` bigint NOT NULL,
  `price` int DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `type` enum('COACHING','COURSE','MOCK_TEST','PLAN','PYQ','TEST_SERIES') DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_seq`
--

DROP TABLE IF EXISTS `product_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_seq`
--

LOCK TABLES `product_seq` WRITE;
/*!40000 ALTER TABLE `product_seq` DISABLE KEYS */;
INSERT INTO `product_seq` VALUES (1);
/*!40000 ALTER TABLE `product_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pyq_papers`
--

DROP TABLE IF EXISTS `pyq_papers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pyq_papers` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `exam_name` varchar(255) DEFAULT NULL,
  `exam_stage` enum('FINAL','INTERVIEW','MAINS','OTHER','PRELIMS','TIER_1','TIER_2','TIER_3') NOT NULL,
  `pdf_url` varchar(255) NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `year` int DEFAULT NULL,
  `exam_category_id` bigint DEFAULT NULL,
  `exam_subcategory_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKh8qdsxfq745cmo22timypd2t5` (`exam_category_id`),
  KEY `FKfq7b928j5h75y97r73axvprow` (`exam_subcategory_id`),
  CONSTRAINT `FKfq7b928j5h75y97r73axvprow` FOREIGN KEY (`exam_subcategory_id`) REFERENCES `exam_sub_categories` (`id`),
  CONSTRAINT `FKh8qdsxfq745cmo22timypd2t5` FOREIGN KEY (`exam_category_id`) REFERENCES `exam_category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pyq_papers`
--

LOCK TABLES `pyq_papers` WRITE;
/*!40000 ALTER TABLE `pyq_papers` DISABLE KEYS */;
INSERT INTO `pyq_papers` VALUES (1,'SSC CGL','TIER_1','/uploads/pyq/c3032311-1321-463e-8afb-5c3fae440fbe_ssc-cgl.pdf','SSC CGL Tier 1 PYQ 2023',2023,1,1),(2,'SSC CGL','TIER_1','/uploads/pyq/d58f52a5-9f88-4cd3-ad75-a4f69cae3a6c_ssc-cgl.pdf','SSC CGL Tier 1 PYQ 2022',2022,1,1),(3,'SSC CGL','TIER_2','/uploads/pyq/302eef56-77f9-4ddd-b74c-855e08788170_ssc-cgl.pdf','SSC CGL Tier 2 PYQ 2023',2023,1,1),(4,'SSC CGL','TIER_2','/uploads/pyq/b9763d78-c290-4bd7-b0d3-7b28ba921661_ssc-cgl.pdf','SSC CGL Tier 2 PYQ 2022',2022,1,1),(5,'SSC CGL','TIER_1','/uploads/pyq/83ac5579-51d2-4570-8ad2-f294e5d827a2_ssc-cgl.pdf','SSC CGL Tier 1 PYQ 2021',2021,1,1),(6,'SSC CGL','TIER_2','/uploads/pyq/f28833a0-ad34-4727-bfc5-e4f81e83090d_ssc-cgl.pdf','SSC CGL Tier 2 PYQ 2021',2021,1,1),(7,'SSC CGL','TIER_2','/uploads/pyq/25f965f6-db22-4b79-be0d-675ebce80f14_ssc-cgl.pdf','SSC CGL Tier 2 PYQ 2020',2020,1,1),(8,'SSC CGL','TIER_1','/uploads/pyq/37a2b4ae-c2a8-4d05-84ed-07aca6c7eaff_ssc-cgl.pdf','SSC CGL Tier 1 PYQ 2020',2020,1,1),(9,'SSC CGL','TIER_1','/uploads/pyq/59568206-be42-4df2-a816-ebe23eea440a_ssc-cgl.pdf','SSC CGL Tier 1 PYQ 2019',2019,1,1),(10,'SSC CGL','TIER_1','/uploads/pyq/c3e4db76-2ca2-4bfa-9a45-46738ecb3f3b_ssc-cgl.pdf','SSC CGL Tier 1 PYQ 2018',2018,1,1),(11,'SSC CGL','TIER_2','/uploads/pyq/e2e7b564-130c-48ee-9f27-1a7b2dbb2614_ssc-cgl.pdf','SSC CGL Tier 2 PYQ 2018',2018,1,1),(12,'SSC CGL','TIER_2','/uploads/pyq/630cf0d6-7ac8-4ee4-87dd-429cabdb1963_Reasoning_eng_SSC_CGL_2023_Tier_1_39_shifts_mock_form_RBE_compressed.pdf','SSC CGL Tier 2 PYQ 2017',2017,1,1);
/*!40000 ALTER TABLE `pyq_papers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `questions`
--

DROP TABLE IF EXISTS `questions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `questions` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `question_text` longtext NOT NULL,
  `question_type` enum('MCQ','MULTI_SELECT','TRUE_FALSE') NOT NULL,
  `subject` varchar(255) DEFAULT NULL,
  `mock_test_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK85gso0kaaw5o2uist4uxh7u8p` (`mock_test_id`),
  CONSTRAINT `FK85gso0kaaw5o2uist4uxh7u8p` FOREIGN KEY (`mock_test_id`) REFERENCES `mock_test` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `questions`
--

LOCK TABLES `questions` WRITE;
/*!40000 ALTER TABLE `questions` DISABLE KEYS */;
INSERT INTO `questions` VALUES (1,'What is 15% of 200?','MCQ','Math',1),(2,'Square root of 144?','MCQ','Math',1),(3,'What is 25 + 36?','MCQ','Math',1),(4,'What is 7 × 8?','MCQ','Math',1),(5,'What is 100 ÷ 5?','MCQ','Math',1),(6,'What is 9²?','MCQ','Math',1),(7,'What is 50% of 80?','MCQ','Math',1),(8,'Simplify: 15 - 7 + 3','MCQ','Math',1),(9,'What is 11 × 11?','MCQ','Math',1),(10,'What is 7³?','MCQ','Math',1),(11,'What is 18 ÷ 3?','MCQ','Math',1),(12,'What is the cube root of 27?','MCQ','Math',1),(13,'What is 14 + 28?','MCQ','Math',1),(14,'What is 5 × 6 - 10?','MCQ','Math',1),(15,'Simplify: 8 × 7 ÷ 2','MCQ','Math',1),(16,'Which number comes next: 2, 4, 8, 16, ?','MCQ','Reasoning',1),(17,'Find the odd one out: Dog, Cat, Cow, Sparrow','MCQ','Reasoning',1),(18,'Which is the next number in the series: 5, 10, 20, 40, ?','MCQ','Reasoning',1),(19,'Find the odd one out: Circle, Triangle, Square, Rectangle, Apple','MCQ','Reasoning',1),(20,'Complete the series: 1, 4, 9, 16, ?','MCQ','Reasoning',1),(21,'Find the odd one out: Mercury, Venus, Mars, Pluto','MCQ','Reasoning',1),(22,'Which number comes next: 3, 6, 12, 24, ?','MCQ','Reasoning',1),(23,'Find the odd one out: Rose, Lily, Tulip, Mango','MCQ','Reasoning',1),(24,'Complete the series: 2, 5, 10, 17, ?','MCQ','Reasoning',1),(25,'Which number does not belong: 21, 24, 27, 31, 33','MCQ','Reasoning',1),(26,'Find the odd one out: Pen, Pencil, Eraser, Notebook, Chair','MCQ','Reasoning',1),(27,'Which number comes next: 1, 2, 6, 24, ?','MCQ','Reasoning',1),(28,'Find the odd one out: Red, Blue, Green, Circle','MCQ','Reasoning',1),(29,'Complete the series: 10, 20, 40, 80, ?','MCQ','Reasoning',1),(30,'Choose the correct synonym of \'Rapid\'','MCQ','English',1),(31,'Choose the correct spelling','MCQ','English',1),(32,'Choose the antonym of \'Generous\'','MCQ','English',1),(33,'Choose the correct synonym of \'Brave\'','MCQ','English',1),(34,'Choose the correct spelling','MCQ','English',1),(35,'Choose the antonym of \'Honest\'','MCQ','English',1),(36,'Choose the correct synonym of \'Tiny\'','MCQ','English',1),(37,'Choose the correct spelling','MCQ','English',1),(38,'Choose the antonym of \'Generous\'','MCQ','English',1),(39,'Choose the correct synonym of \'Intelligent\'','MCQ','English',1),(40,'Choose the correct spelling','MCQ','English',1),(41,'Choose the antonym of \'Ancient\'','MCQ','English',1),(42,'Choose the correct synonym of \'Happy\'','MCQ','English',1),(43,'Choose the correct spelling','MCQ','English',1),(44,'Choose the antonym of \'Difficult\'','MCQ','English',1),(45,'Who is known as the Father of the Indian Constitution?','MCQ','GK',1),(46,'Which planet is known as the Red Planet?','MCQ','GK',1),(47,'What is the national animal of India?','MCQ','GK',1),(48,'Which is the largest continent by area?','MCQ','GK',1),(49,'Who wrote the national anthem of India?','MCQ','GK',1),(50,'Which gas is essential for photosynthesis?','MCQ','GK',1),(51,'Who was the first Prime Minister of India?','MCQ','GK',1),(52,'Which river is the longest in the world?','MCQ','GK',1),(53,'Which country is known as the Land of the Rising Sun?','MCQ','GK',1),(54,'Who discovered gravity?','MCQ','GK',1),(55,'Which element has the chemical symbol \'O\'?','MCQ','GK',1),(56,'Which is the largest ocean in the world?','MCQ','GK',1),(57,'Who invented the telephone?','MCQ','GK',1),(58,'Which is the smallest country in the world?','MCQ','GK',1),(59,'Which is the tallest mountain in the world?','MCQ','GK',1),(61,'What is 15% of 100?','MCQ','GK',1),(62,'What is 15% of 300?','MCQ','GK',1),(63,'Who is known as the Father of the Indian Constitution?','MCQ','GK',1),(64,'Which planet is known as the Red Planet?','MCQ','GK',1),(65,'Which element has the chemical symbol \'O\'?','MCQ','GK',9),(66,'Name','MCQ','GK',1),(67,'sdfsgr','MCQ','GK',1),(68,'lkjadefghiugafljwk','MCQ','GK',1);
/*!40000 ALTER TABLE `questions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `review`
--

DROP TABLE IF EXISTS `review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `review` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `comment` text,
  `created_at` datetime(6) DEFAULT NULL,
  `entity_id` bigint DEFAULT NULL,
  `entity_type` enum('MOCK_TEST','QUESTION','TEST_SERIES') DEFAULT NULL,
  `rating` int NOT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK39cposwpxeymrvndgw4ybevjj` (`entity_type`,`entity_id`,`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review`
--

LOCK TABLES `review` WRITE;
/*!40000 ALTER TABLE `review` DISABLE KEYS */;
/*!40000 ALTER TABLE `review` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student_answers`
--

DROP TABLE IF EXISTS `student_answers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student_answers` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `is_correct` bit(1) DEFAULT NULL,
  `selected_option_id` bigint DEFAULT NULL,
  `attempt_id` bigint DEFAULT NULL,
  `question_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4luqni555dbn8keddogfjxhxy` (`attempt_id`),
  KEY `FK8nyksamccim8emu803uhf2da` (`question_id`),
  CONSTRAINT `FK4luqni555dbn8keddogfjxhxy` FOREIGN KEY (`attempt_id`) REFERENCES `student_attempts` (`id`),
  CONSTRAINT `FK8nyksamccim8emu803uhf2da` FOREIGN KEY (`question_id`) REFERENCES `questions` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_answers`
--

LOCK TABLES `student_answers` WRITE;
/*!40000 ALTER TABLE `student_answers` DISABLE KEYS */;
/*!40000 ALTER TABLE `student_answers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student_attempts`
--

DROP TABLE IF EXISTS `student_attempts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student_attempts` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `correct_count` int DEFAULT NULL,
  `end_time` datetime(6) DEFAULT NULL,
  `percentage` double DEFAULT NULL,
  `start_time` datetime(6) DEFAULT NULL,
  `status` enum('EXPIRED','IN_PROGRESS','STARTED','SUBMITTED','TIME_UP') DEFAULT NULL,
  `total_marks` int DEFAULT NULL,
  `total_questions` int DEFAULT NULL,
  `unattempted_count` int DEFAULT NULL,
  `wrong_count` int DEFAULT NULL,
  `mock_test_id` bigint DEFAULT NULL,
  `student_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKhytir9pijqi0bbk20e7m1erml` (`mock_test_id`),
  KEY `FK8ghp77sjk7ybwnlx5e83cnop1` (`student_id`),
  CONSTRAINT `FK8ghp77sjk7ybwnlx5e83cnop1` FOREIGN KEY (`student_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKhytir9pijqi0bbk20e7m1erml` FOREIGN KEY (`mock_test_id`) REFERENCES `mock_test` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_attempts`
--

LOCK TABLES `student_attempts` WRITE;
/*!40000 ALTER TABLE `student_attempts` DISABLE KEYS */;
INSERT INTO `student_attempts` VALUES (1,NULL,NULL,NULL,'2025-12-23 01:11:07.350441','STARTED',NULL,0,NULL,NULL,1,2),(2,NULL,NULL,NULL,'2025-12-23 01:22:46.079123','STARTED',NULL,15,NULL,NULL,1,2),(3,9,'2025-12-23 01:26:56.413343',15.254237288135593,'2025-12-23 01:25:20.155414','SUBMITTED',9,59,45,5,1,2),(4,NULL,NULL,NULL,'2025-12-23 01:29:39.827984','STARTED',NULL,59,NULL,NULL,1,2),(5,NULL,NULL,NULL,'2025-12-23 10:51:51.207516','STARTED',NULL,59,NULL,NULL,1,2),(6,NULL,NULL,NULL,'2025-12-23 11:00:06.643121','STARTED',NULL,59,NULL,NULL,1,2),(7,NULL,NULL,NULL,'2025-12-23 12:26:03.656312','STARTED',NULL,59,NULL,NULL,1,2),(8,NULL,NULL,NULL,'2025-12-23 12:34:45.845739','STARTED',NULL,59,NULL,NULL,1,2),(9,NULL,NULL,NULL,'2025-12-23 12:37:09.982459','STARTED',NULL,59,NULL,NULL,1,2),(10,NULL,NULL,NULL,'2025-12-23 12:37:39.867221','STARTED',NULL,59,NULL,NULL,1,2),(11,NULL,NULL,NULL,'2025-12-23 12:38:20.902423','STARTED',NULL,59,NULL,NULL,1,2),(12,NULL,NULL,NULL,'2025-12-23 17:27:38.258825','STARTED',NULL,59,NULL,NULL,1,2),(13,NULL,NULL,NULL,'2025-12-25 10:25:31.391984','STARTED',NULL,59,NULL,NULL,1,2),(14,NULL,NULL,NULL,'2025-12-25 17:13:27.155890','STARTED',NULL,59,NULL,NULL,1,2),(15,NULL,NULL,NULL,'2025-12-25 17:28:19.604860','STARTED',NULL,59,NULL,NULL,1,2),(16,NULL,NULL,NULL,'2025-12-25 18:22:05.313038','STARTED',NULL,59,NULL,NULL,1,2),(17,NULL,NULL,NULL,'2025-12-25 19:30:29.954072','STARTED',NULL,59,NULL,NULL,1,2),(18,NULL,NULL,NULL,'2025-12-25 19:34:00.099136','STARTED',NULL,61,NULL,NULL,1,2),(19,NULL,NULL,NULL,'2025-12-25 19:35:37.559098','STARTED',NULL,61,NULL,NULL,1,2),(20,NULL,NULL,NULL,'2025-12-25 19:35:37.919849','STARTED',NULL,61,NULL,NULL,1,2),(21,NULL,NULL,NULL,'2025-12-25 19:38:45.958216','STARTED',NULL,61,NULL,NULL,1,2),(22,2,'2025-12-25 19:46:45.371565',3.278688524590164,'2025-12-25 19:38:46.100779','SUBMITTED',2,61,59,0,1,2),(23,0,'2025-12-25 20:03:03.778330',0,'2025-12-25 20:02:55.281822','SUBMITTED',0,63,63,0,1,2),(24,1,'2025-12-25 20:03:22.787873',1.5873015873015872,'2025-12-25 20:03:14.796456','SUBMITTED',1,63,62,0,1,2),(25,NULL,NULL,NULL,'2025-12-25 20:03:39.356428','STARTED',NULL,0,NULL,NULL,2,2),(26,NULL,NULL,NULL,'2025-12-25 20:04:44.285519','STARTED',NULL,0,NULL,NULL,2,2),(27,3,'2025-12-26 10:46:08.302815',4.761904761904762,'2025-12-26 10:45:19.202343','SUBMITTED',3,63,60,0,1,2),(28,3,'2025-12-26 11:59:07.973959',4.761904761904762,'2025-12-26 11:58:15.193189','SUBMITTED',3,63,60,0,1,2),(29,2,'2025-12-26 23:35:41.476788',3.1746031746031744,'2025-12-26 23:34:50.401172','SUBMITTED',2,63,61,0,1,2),(30,NULL,NULL,NULL,'2025-12-26 23:44:03.127924','STARTED',NULL,63,NULL,NULL,1,2),(31,NULL,NULL,NULL,'2025-12-26 23:50:57.677487','STARTED',NULL,63,NULL,NULL,1,2),(32,3,'2025-12-27 08:15:38.473423',4.545454545454546,'2025-12-27 08:15:17.257330','SUBMITTED',3,66,63,0,1,2),(33,NULL,NULL,NULL,'2025-12-27 08:37:31.513614','STARTED',NULL,66,NULL,NULL,1,2),(34,NULL,NULL,NULL,'2025-12-27 08:38:16.623113','STARTED',NULL,66,NULL,NULL,1,2),(35,NULL,NULL,NULL,'2025-12-27 08:54:27.977577','STARTED',NULL,66,NULL,NULL,1,2),(36,NULL,NULL,NULL,'2025-12-27 12:56:33.017136','STARTED',NULL,66,NULL,NULL,1,2);
/*!40000 ALTER TABLE `student_attempts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `test_instructions`
--

DROP TABLE IF EXISTS `test_instructions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `test_instructions` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `description` text,
  `title` varchar(255) DEFAULT NULL,
  `mock_test_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKlq9p48iw8v3u1ctsr437ngoab` (`mock_test_id`),
  CONSTRAINT `FKlltvgavyvccvujae3rsme4o0o` FOREIGN KEY (`mock_test_id`) REFERENCES `mock_test` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `test_instructions`
--

LOCK TABLES `test_instructions` WRITE;
/*!40000 ALTER TABLE `test_instructions` DISABLE KEYS */;
INSERT INTO `test_instructions` VALUES (1,'Instructions for SSC CGL Tier-I Mock Test. Total Number of Questions: 100, Total Time Available: 1 Hour.','Please read the following instructions carefully',1),(2,'Instructions for SSC CGL Tier-I Mock Test. Total Number of Questions: 100, Total Time Available: 1 Hour.','Please read the following instructions carefully',2);
/*!40000 ALTER TABLE `test_instructions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `test_results`
--

DROP TABLE IF EXISTS `test_results`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `test_results` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `correct_count` int DEFAULT NULL,
  `percentage` double DEFAULT NULL,
  `total_marks` int DEFAULT NULL,
  `wrong_count` int DEFAULT NULL,
  `attempt_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK5ork7t648dwn119rieeupyhxc` (`attempt_id`),
  CONSTRAINT `FKjk15h2kx0r9p954su9ngomx53` FOREIGN KEY (`attempt_id`) REFERENCES `student_attempts` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `test_results`
--

LOCK TABLES `test_results` WRITE;
/*!40000 ALTER TABLE `test_results` DISABLE KEYS */;
/*!40000 ALTER TABLE `test_results` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `test_series`
--

DROP TABLE IF EXISTS `test_series`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `test_series` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_by_admin_id` bigint DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `exam_subcategory_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8t7wr1l13s1g76gxw8q8hfg3a` (`exam_subcategory_id`),
  CONSTRAINT `FK8t7wr1l13s1g76gxw8q8hfg3a` FOREIGN KEY (`exam_subcategory_id`) REFERENCES `exam_sub_categories` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `test_series`
--

LOCK TABLES `test_series` WRITE;
/*!40000 ALTER TABLE `test_series` DISABLE KEYS */;
INSERT INTO `test_series` VALUES (1,5,'SSC CGL Full Test Series 2025 – Set 1',1),(2,5,'SSC CGL Full Test Series 2025 – Set 2',1),(3,5,'SSC CHSL Full Test Series 2025 – Set 1',2),(4,5,'SSC CHSL Full Test Series 2025 – Set 2',2),(5,5,'SSC MTS Full Test Series 2025 – Set 1',3),(6,5,'SSC MTS Full Test Series 2025 – Set 2',3),(7,5,'SSC MTS Full Test Series 2025 – Set 3',3),(8,5,'SSC MTS Full Test Series 2025 – Set 4',3),(9,5,'SSC MTS Full Test Series 2025 – Set 5',3),(10,5,'IBPS PO Full Test Series 2025 – Set 1',5),(11,5,'IBPS PO Full Test Series 2025 – Set 2',5),(12,5,'IBPS PO Full Test Series 2025 – Set 3',5),(13,5,'IBPS Clerk Full Test Series 2025 – Set 1',6),(14,5,'IBPS Clerk Full Test Series 2025 – Set 2',6),(15,5,'SBI PO Full Test Series 2025 – Set 1',7),(16,5,'SBI PO Full Test Series 2025 – Set 2',7),(17,5,'RRB NTPC Full Test Series 2025 – Set 1',9),(18,5,'RRB NTPC Full Test Series 2025 – Set 2',9),(19,5,'UPSC CSE Prelims Test Series 2025 – Set 1',12),(20,5,'UPSC CSE Prelims Test Series 2025 – Set 2',12),(21,5,'SBI PO Full Test Series 2025 – Set 3',7),(22,5,'RRB Group D Full Test Series 2025 – Set 3',10),(23,5,'UPSC CDS Full Test Series 2025 – Set 2',13),(24,5,'NDA Army Test Series 2025 – Set 1',31),(25,5,'NDA Navy Test Series 2025 – Set 1',32),(26,5,'CAT Quantitative Aptitude Test Series – Set 1',34),(27,5,'CAT DILR Test Series – Set 1',36),(28,5,'PSU Engineering Test Series – Set 1',37),(29,5,'JEE Main Test Series 2025 – Set 1',27),(30,5,'JEE Advanced Test Series 2025 – Set 1',28),(31,5,'NEET UG Test Series 2025 – Set 1',29),(32,5,'NEET PG Test Series 2025 – Set 1',30);
/*!40000 ALTER TABLE `test_series` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `city` varchar(255) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `full_name` varchar(255) NOT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `profile_image` varchar(255) DEFAULT NULL,
  `role` enum('ADMIN','STUDENT','SUPERADMIN','TEACHER') DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK6dotkott2kjsp8vw4d0m25fb7` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Patna','2025-12-22 19:06:06.803381','rohit.kumar@example.com','Rohit Kumar','9876543210','StrongPassword@123','/uploads/profile/rohit.png','STUDENT','Bihar','2025-12-22 19:06:06.803451'),(2,'Lucknow','2025-12-22 19:07:01.336481','amit.singh@example.com','Amit Singh','9123456780','Amit@12345','/uploads/profile/amit.png','STUDENT','Uttar Pradesh','2025-12-22 19:07:01.336538'),(3,'Jaipur','2025-12-22 19:07:10.130263','priya.sharma@example.com','Priya Sharma','9812345670','Priya@12345','/uploads/profile/priya.png','STUDENT','Rajasthan','2025-12-22 19:07:10.130306'),(4,'New Delhi','2025-12-22 19:07:17.110169','sandeep.verma@example.com','Sandeep Verma','9001122334','Sandeep@123','/uploads/profile/sandeep.png','TEACHER','Delhi','2025-12-22 19:07:17.110233'),(5,'Mumbai','2025-12-22 19:07:25.270428','neha.admin@rkedu.com','Neha Gupta','9090909090','Admin@Neha123','/uploads/profile/neha.png','ADMIN','Maharashtra','2025-12-22 19:07:25.270475'),(6,'Ahmedabad','2025-12-22 19:07:32.369285','rahul.mehta@example.com','Rahul Mehta','9345678123','Rahul@9876','/uploads/profile/rahul.png','STUDENT','Gujarat','2025-12-22 19:07:32.369318'),(7,'Ahmedabad','2025-12-27 08:50:28.220456','rahul.mehta1@example.com','Rahul Mehta','9345678123','Rahul@9876','/uploads/profile/rahul.png','STUDENT','Gujarat','2025-12-27 08:50:28.220476');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
SET FOREIGN_KEY_CHECKS=1;

/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-12-30 10:12:33
