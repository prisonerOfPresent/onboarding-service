CREATE TABLE IF NOT EXISTS `tenant` (
  `TenantID` bigint(20) NOT NULL,
  `IsActive` BOOLEAN DEFAULT FALSE,
  `TenantName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`TenantID`),
  UNIQUE KEY `UK_TenantName` (`TenantName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE IF NOT EXISTS `registration_metadata` (
  `TenantRegistrationID` bigint(20) NOT NULL,
  `UserName` varchar(255) NOT NULL,
  `FirstName` varchar(255) NOT NULL,
  `LastName` varchar(255) NOT NULL,
  `Password` varchar(255) NOT NULL,
  `AccountType` varchar(255) NOT NULL,
  `PlanName` varchar(255) NOT NULL,
  `TenantID` bigint(20) NOT NULL,
  PRIMARY KEY (`TenantRegistrationID`),
  KEY `FK_TenantID` (`TenantID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;