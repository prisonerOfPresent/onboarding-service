CREATE TABLE IF NOT EXISTS tenant_db_hosts (
  HostID bigint NOT NULL GENERATED ALWAYS AS IDENTITY ,
  IsActive BOOLEAN DEFAULT FALSE,
  HostName varchar(255) DEFAULT NULL,
  HostUrl varchar(255) DEFAULT NULL,
  HostPort varchar(255) DEFAULT NULL,
  HostUserName varchar(255) DEFAULT NULL,
  HostPassword varchar(255) DEFAULT NULL,
  PRIMARY KEY (HostID),
  UNIQUE(HostName)
);


CREATE TABLE IF NOT EXISTS tenant_shard_mapping (
  TenantShardMappingID bigint NOT NULL,
  ShardKey varchar(255) NOT NULL,
  HostID varchar(255) NOT NULL,
  Schema varchar(255) NOT NULL,
  PRIMARY KEY(TenantShardMappingID)
);
