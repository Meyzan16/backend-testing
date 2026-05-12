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

 Date: 12/05/2026 16:53:12
*/


-- ----------------------------
-- Table structure for notification_logs
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[notification_logs]') AND type IN ('U'))
	DROP TABLE [dbo].[notification_logs]
GO

CREATE TABLE [dbo].[notification_logs] (
  [id] bigint  IDENTITY(1,1) NOT NULL,
  [notification_uid] uniqueidentifier DEFAULT newid() NOT NULL,
  [user_uid] uniqueidentifier  NOT NULL,
  [channel] varchar(20) COLLATE Latin1_General_CI_AS  NOT NULL,
  [recipient] varchar(255) COLLATE Latin1_General_CI_AS  NOT NULL,
  [subject] varchar(500) COLLATE Latin1_General_CI_AS  NULL,
  [body] varchar(max) COLLATE Latin1_General_CI_AS  NULL,
  [status] varchar(20) COLLATE Latin1_General_CI_AS DEFAULT 'PENDING' NOT NULL,
  [error_message] varchar(500) COLLATE Latin1_General_CI_AS  NULL,
  [sent_at] datetime2(7)  NULL,
  [created_at] datetime2(7) DEFAULT sysdatetime() NOT NULL
)
GO

ALTER TABLE [dbo].[notification_logs] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Auto increment value for notification_logs
-- ----------------------------
DBCC CHECKIDENT ('[dbo].[notification_logs]', RESEED, 29)
GO


-- ----------------------------
-- Indexes structure for table notification_logs
-- ----------------------------
CREATE NONCLUSTERED INDEX [idx_notif_user]
ON [dbo].[notification_logs] (
  [user_uid] ASC
)
GO

CREATE NONCLUSTERED INDEX [idx_notif_status]
ON [dbo].[notification_logs] (
  [status] ASC
)
GO

CREATE NONCLUSTERED INDEX [idx_notif_created]
ON [dbo].[notification_logs] (
  [created_at] DESC
)
GO


-- ----------------------------
-- Checks structure for table notification_logs
-- ----------------------------
ALTER TABLE [dbo].[notification_logs] ADD CONSTRAINT [chk_notif_channel] CHECK ([channel]='PUSH' OR [channel]='SMS' OR [channel]='EMAIL')
GO

ALTER TABLE [dbo].[notification_logs] ADD CONSTRAINT [chk_notif_status] CHECK ([status]='RETRY' OR [status]='FAILED' OR [status]='SENT' OR [status]='PENDING')
GO


-- ----------------------------
-- Primary Key structure for table notification_logs
-- ----------------------------
ALTER TABLE [dbo].[notification_logs] ADD CONSTRAINT [PK__notifica__3213E83F89FE2829] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO

