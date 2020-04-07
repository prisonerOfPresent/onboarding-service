CREATE TABLE IF NOT EXISTS `tenantdbhosts` (
  `HostID` int(20) NOT NULL AUTO_INCREMENT,
  `IsActive` BOOLEAN DEFAULT FALSE,
  `HostName` varchar(255) DEFAULT NULL,
  `HostUrl` varchar(255) DEFAULT NULL,
  `HostPort` varchar(255) DEFAULT NULL,
  `HostUserName` varchar(255) DEFAULT NULL,
  `HostPassword` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`HostID`),
  UNIQUE KEY `UK_HostName` (`HostName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE IF NOT EXISTS `tenant_shard_mapping` (
  `TenantShardMappingID` bigint(20) NOT NULL,
  `ShardKey` varchar(255) NOT NULL,
  `HostID` varchar(255) NOT NULL,
  `Schema` varchar(255) NOT NULL,
  PRIMARY KEY (`TenantShardMappingID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
