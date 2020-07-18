CREATE TABLE IF NOT EXISTS tenant (
  TenantID bigint NOT NULL,
  IsActive BOOLEAN DEFAULT FALSE,
  TenantName varchar(255) DEFAULT NULL UNIQUE,
  PRIMARY KEY (TenantID));

CREATE TABLE IF NOT EXISTS registration_metadata (
  TenantRegistrationID bigint NOT NULL,
  UserName varchar(255) NOT NULL,
  FirstName varchar(255) NOT NULL,
  LastName varchar(255) NOT NULL,
  Password varchar(255) NOT NULL,
  AccountType varchar(255) NOT NULL,
  PlanName varchar(255) NOT NULL,
  TenantID bigint NOT NULL,
  PRIMARY KEY(TenantRegistrationID)
  );