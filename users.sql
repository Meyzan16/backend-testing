/*
 Navicat Premium Dump SQL

 Source Server         : 10.220.60.80,1435 DB DEV DWH
 Source Server Type    : SQL Server
 Source Server Version : 16001000 (16.00.1000)
 Source Host           : 10.220.60.80:1435
 Source Catalog        : db_testing
 Source Schema         : dbo

 Target Server Type    : SQL Server
 Target Server Version : 16001000 (16.00.1000)
 File Encoding         : 65001

 Date: 12/05/2026 16:53:26
*/


-- ----------------------------
-- Table structure for users
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[users]') AND type IN ('U'))
	DROP TABLE [dbo].[users]
GO

CREATE TABLE [dbo].[users] (
  [id] bigint  IDENTITY(1,1) NOT NULL,
  [user_uid] uniqueidentifier DEFAULT newid() NOT NULL,
  [email] varchar(255) COLLATE Latin1_General_CI_AS  NOT NULL,
  [password_hash] varchar(255) COLLATE Latin1_General_CI_AS  NOT NULL,
  [full_name] varchar(150) COLLATE Latin1_General_CI_AS  NOT NULL,
  [role] varchar(50) COLLATE Latin1_General_CI_AS DEFAULT 'USER' NOT NULL,
  [is_active] bit DEFAULT 1 NOT NULL,
  [created_at] datetime2(7) DEFAULT sysdatetime() NOT NULL,
  [updated_at] datetime2(7) DEFAULT sysdatetime() NOT NULL
)
GO

ALTER TABLE [dbo].[users] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Auto increment value for users
-- ----------------------------
DBCC CHECKIDENT ('[dbo].[users]', RESEED, 31)
GO


-- ----------------------------
-- Uniques structure for table users
-- ----------------------------
ALTER TABLE [dbo].[users] ADD CONSTRAINT [uq_users_email] UNIQUE NONCLUSTERED ([email] ASC)
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO

ALTER TABLE [dbo].[users] ADD CONSTRAINT [uq_users_uid] UNIQUE NONCLUSTERED ([user_uid] ASC)
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Checks structure for table users
-- ----------------------------
ALTER TABLE [dbo].[users] ADD CONSTRAINT [chk_users_role] CHECK ([role]='ADMIN' OR [role]='USER')
GO


-- ----------------------------
-- Primary Key structure for table users
-- ----------------------------
ALTER TABLE [dbo].[users] ADD CONSTRAINT [PK__users__3213E83F50E1DA68] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO

