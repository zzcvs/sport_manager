-- ============================================================
-- 健身房管理系统（sport_manager）数据库初始化脚本
-- 适配 MySQL 5.7 / 8.0，字符集 utf8mb4
--
-- 使用方法（Windows / macOS / Linux 通用）：
--   mysql -u root -p < db/init.sql
--   或在 Navicat / DataGrip 里直接运行本文件
--
-- 默认账号（登录密码均为 123456）：
--   后台管理端 http://localhost:8080  ->  admin
--   用户端     http://localhost:8081  ->  member
-- ============================================================

DROP DATABASE IF EXISTS `project_db`;
CREATE DATABASE `project_db` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `project_db`;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ------------------------------------------------------------
-- 1. 用户账户
-- ------------------------------------------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id`     int(11)      NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `state`       int(11)      DEFAULT 1 COMMENT '账户状态 1可用 2异常 3冻结 4注销',
  `user_group`  varchar(64)  DEFAULT NULL COMMENT '所在用户组',
  `login_time`  datetime     DEFAULT NULL COMMENT '上次登录时间',
  `phone`       varchar(20)  DEFAULT NULL COMMENT '手机号码',
  `phone_state` int(11)      DEFAULT 0 COMMENT '手机认证状态',
  `username`    varchar(64)  DEFAULT NULL COMMENT '用户名',
  `nickname`    varchar(64)  DEFAULT NULL COMMENT '昵称',
  `password`    varchar(255) DEFAULT NULL COMMENT '密码（前端 RSA 加密后由后端解密，库里存明文）',
  `email`       varchar(128) DEFAULT NULL COMMENT '邮箱',
  `email_state` int(11)      DEFAULT 0 COMMENT '邮箱认证状态',
  `avatar`      varchar(255) DEFAULT NULL COMMENT '头像地址',
  `open_id`     varchar(64)  DEFAULT NULL COMMENT '第三方 openId',
  `create_time` datetime     DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `uk_username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户账户';

-- ------------------------------------------------------------
-- 2. 用户组
-- ------------------------------------------------------------
DROP TABLE IF EXISTS `user_group`;
CREATE TABLE `user_group` (
  `group_id`     int(11)      NOT NULL AUTO_INCREMENT,
  `display`      int(11)      DEFAULT 1 COMMENT '是否显示',
  `name`         varchar(64)  DEFAULT NULL COMMENT '用户组名称',
  `description`  varchar(255) DEFAULT NULL COMMENT '描述',
  `source_table` varchar(64)  DEFAULT NULL COMMENT '关联表（非空则登录时校验审核状态）',
  `source_field` varchar(64)  DEFAULT NULL COMMENT '关联表字段',
  `source_id`    int(11)      DEFAULT NULL COMMENT '关联ID',
  `register`     varchar(10)  DEFAULT NULL COMMENT '注册开放等级',
  `create_time`  datetime     DEFAULT NULL,
  `update_time`  datetime     DEFAULT NULL,
  PRIMARY KEY (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户组';

-- ------------------------------------------------------------
-- 3. 菜单/操作权限
-- ------------------------------------------------------------
DROP TABLE IF EXISTS `auth`;
CREATE TABLE `auth` (
  `auth_id`        int(11)      NOT NULL AUTO_INCREMENT,
  `user_group`     varchar(64)  DEFAULT NULL COMMENT '用户组',
  `mod_name`       varchar(64)  DEFAULT NULL COMMENT '模块名',
  `table_name`     varchar(64)  DEFAULT NULL COMMENT '表名',
  `page_title`     varchar(64)  DEFAULT NULL COMMENT '页面标题',
  `path`           varchar(255) DEFAULT NULL COMMENT '前端路由',
  `position`       varchar(32)  DEFAULT NULL COMMENT '菜单位置 top/left',
  `mode`           varchar(32)  DEFAULT NULL,
  `add`            int(11)      DEFAULT 0 COMMENT '新增权限',
  `del`            int(11)      DEFAULT 0 COMMENT '删除权限',
  `set`            int(11)      DEFAULT 0 COMMENT '修改权限',
  `get`            int(11)      DEFAULT 0 COMMENT '查询权限',
  `field_add`      text,
  `field_set`      text,
  `field_get`      text,
  `table_nav_name` varchar(128) DEFAULT NULL,
  `table_nav`      tinyint(1)   DEFAULT 0,
  `option`         text,
  `parent`         varchar(64)  DEFAULT NULL COMMENT '父级模块名',
  `parent_sort`    int(11)      DEFAULT 0 COMMENT '父级排序',
  `create_time`    datetime     DEFAULT NULL,
  `update_time`    datetime     DEFAULT NULL,
  PRIMARY KEY (`auth_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='菜单与操作权限';

-- ------------------------------------------------------------
-- 4. 文章分类 / 文章
-- ------------------------------------------------------------
DROP TABLE IF EXISTS `article_type`;
CREATE TABLE `article_type` (
  `type_id`     int(11)      NOT NULL AUTO_INCREMENT,
  `display`     int(11)      DEFAULT 1,
  `name`        varchar(64)  DEFAULT NULL,
  `father_id`   int(11)      DEFAULT 0,
  `description` varchar(255) DEFAULT NULL,
  `icon`        varchar(255) DEFAULT NULL,
  `url`         varchar(255) DEFAULT NULL,
  `create_time` datetime     DEFAULT NULL,
  `update_time` datetime     DEFAULT NULL,
  PRIMARY KEY (`type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='文章分类';

DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `article_id`  int(11)      NOT NULL AUTO_INCREMENT,
  `title`       varchar(255) DEFAULT NULL COMMENT '标题',
  `type`        varchar(64)  DEFAULT NULL COMMENT '分类',
  `hits`        int(11)      DEFAULT 0 COMMENT '点击数',
  `praise_len`  int(11)      DEFAULT 0 COMMENT '点赞数',
  `create_time` datetime     DEFAULT NULL,
  `update_time` datetime     DEFAULT NULL,
  `source`      varchar(255) DEFAULT NULL COMMENT '来源',
  `url`         varchar(255) DEFAULT NULL COMMENT '来源地址',
  `tag`         varchar(255) DEFAULT NULL COMMENT '标签',
  `content`     text COMMENT '正文',
  `img`         varchar(255) DEFAULT NULL COMMENT '封面图',
  `description` varchar(500) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`article_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='健身资讯';

-- ------------------------------------------------------------
-- 5. 轮播图 / 公告
-- ------------------------------------------------------------
DROP TABLE IF EXISTS `slides`;
CREATE TABLE `slides` (
  `slides_id`   int(11)      NOT NULL AUTO_INCREMENT,
  `title`       varchar(255) DEFAULT NULL,
  `content`     text,
  `url`         varchar(255) DEFAULT NULL COMMENT '跳转地址',
  `img`         varchar(255) DEFAULT NULL COMMENT '图片',
  `hits`        int(11)      DEFAULT 0,
  `create_time` datetime     DEFAULT NULL,
  `update_time` datetime     DEFAULT NULL,
  PRIMARY KEY (`slides_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='轮播图';

DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice` (
  `notice_id`   int(11)      NOT NULL AUTO_INCREMENT,
  `title`       varchar(255) DEFAULT NULL,
  `content`     text,
  `create_time` datetime     DEFAULT NULL,
  `update_time` datetime     DEFAULT NULL,
  PRIMARY KEY (`notice_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='网站公告';

-- ------------------------------------------------------------
-- 6. 课程信息 / 课程参与
-- ------------------------------------------------------------
DROP TABLE IF EXISTS `course_information`;
CREATE TABLE `course_information` (
  `course_information_id`             int(11)      NOT NULL AUTO_INCREMENT,
  `course_name`                       varchar(255) DEFAULT NULL COMMENT '课程名称',
  `course_type`                       varchar(64)  DEFAULT NULL COMMENT '课程类型',
  `course_time`                       varchar(128) DEFAULT NULL COMMENT '课程时间',
  `course_location`                   varchar(255) DEFAULT NULL COMMENT '课程地点',
  `name_of_coach`                     varchar(64)  DEFAULT NULL COMMENT '教练姓名',
  `course_picture`                    varchar(255) DEFAULT NULL COMMENT '课程图片',
  `course_introduction`               varchar(500) DEFAULT NULL COMMENT '课程介绍',
  `course_details`                    text COMMENT '课程详情',
  `hits`                              int(11)      DEFAULT 0,
  `praise_len`                        int(11)      DEFAULT 0,
  `collect_len`                       int(11)      DEFAULT 0,
  `comment_len`                       int(11)      DEFAULT 0,
  `course_participation_limit_times`  varchar(32)  DEFAULT NULL COMMENT '参与限制次数',
  `update_time`                       datetime     DEFAULT NULL,
  `create_time`                       datetime     DEFAULT NULL,
  PRIMARY KEY (`course_information_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='课程信息';

DROP TABLE IF EXISTS `course_participation`;
CREATE TABLE `course_participation` (
  `course_participation_id`      int(11)      NOT NULL AUTO_INCREMENT,
  `course_name`                  varchar(255) DEFAULT NULL,
  `course_type`                  varchar(64)  DEFAULT NULL,
  `course_time`                  varchar(128) DEFAULT NULL,
  `course_location`              varchar(255) DEFAULT NULL,
  `name_of_coach`                varchar(64)  DEFAULT NULL,
  `member_user`                  int(11)      DEFAULT NULL COMMENT '会员用户ID',
  `member_name`                  varchar(64)  DEFAULT NULL COMMENT '会员姓名',
  `participation_notes`          varchar(500) DEFAULT NULL COMMENT '参与备注',
  `fitness_advice_limit_times`   varchar(32)  DEFAULT NULL,
  `source_table`                 varchar(64)  DEFAULT NULL,
  `source_id`                    int(11)      DEFAULT NULL,
  `source_user_id`               int(11)      DEFAULT NULL,
  `update_time`                  datetime     DEFAULT NULL,
  `create_time`                  datetime     DEFAULT NULL,
  PRIMARY KEY (`course_participation_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='课程参与';

-- ------------------------------------------------------------
-- 7. 会员卡 / 会员用户 / 健身建议
-- ------------------------------------------------------------
DROP TABLE IF EXISTS `membership_card`;
CREATE TABLE `membership_card` (
  `membership_card_id`               int(11)      NOT NULL AUTO_INCREMENT,
  `membership_card_name`             varchar(255) DEFAULT NULL COMMENT '会员卡名称',
  `membership_card_type`             varchar(64)  DEFAULT NULL COMMENT '会员卡类型',
  `membership_card_expiration_date`  varchar(64)  DEFAULT NULL COMMENT '有效期',
  `membership_card_fees`             double       DEFAULT 0 COMMENT '费用',
  `membership_card_discounts`        varchar(255) DEFAULT NULL COMMENT '优惠',
  `membership_card_introduction`     varchar(500) DEFAULT NULL COMMENT '介绍',
  `membership_card_details`          text COMMENT '详情',
  `hits`                             int(11)      DEFAULT 0,
  `praise_len`                       int(11)      DEFAULT 0,
  `collect_len`                      int(11)      DEFAULT 0,
  `comment_len`                      int(11)      DEFAULT 0,
  `update_time`                      datetime     DEFAULT NULL,
  `create_time`                      datetime     DEFAULT NULL,
  PRIMARY KEY (`membership_card_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='会员卡';

DROP TABLE IF EXISTS `member_user`;
CREATE TABLE `member_user` (
  `member_user_id`       int(11)      NOT NULL AUTO_INCREMENT,
  `member_name`          varchar(64)  DEFAULT NULL COMMENT '会员姓名',
  `contact_information`  varchar(64)  DEFAULT NULL COMMENT '联系方式',
  `membership_type`      varchar(64)  DEFAULT NULL COMMENT '会员类型',
  `due_date`             datetime     DEFAULT NULL COMMENT '到期时间',
  `account_balance`      double       DEFAULT 0 COMMENT '账户余额',
  `user_id`              int(11)      DEFAULT NULL COMMENT '关联用户ID',
  `update_time`          datetime     DEFAULT NULL,
  `create_time`          datetime     DEFAULT NULL,
  PRIMARY KEY (`member_user_id`),
  KEY `idx_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='会员用户';

DROP TABLE IF EXISTS `fitness_advice`;
CREATE TABLE `fitness_advice` (
  `fitness_advice_id`   int(11)      NOT NULL AUTO_INCREMENT,
  `member_user`         int(11)      DEFAULT NULL,
  `member_name`         varchar(64)  DEFAULT NULL,
  `suggested_title`     varchar(255) DEFAULT NULL COMMENT '建议标题',
  `recommend_courses`   varchar(255) DEFAULT NULL COMMENT '推荐课程',
  `suggested_content`   text COMMENT '建议内容',
  `source_table`        varchar(64)  DEFAULT NULL,
  `source_id`           int(11)      DEFAULT NULL,
  `source_user_id`      int(11)      DEFAULT NULL,
  `update_time`         datetime     DEFAULT NULL,
  `create_time`         datetime     DEFAULT NULL,
  PRIMARY KEY (`fitness_advice_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='健身建议';

-- ------------------------------------------------------------
-- 8. 设备信息 / 财务报表
-- ------------------------------------------------------------
DROP TABLE IF EXISTS `device_information`;
CREATE TABLE `device_information` (
  `device_information_id`    int(11)      NOT NULL AUTO_INCREMENT,
  `equipment_number`         varchar(64)  DEFAULT NULL COMMENT '设备编号',
  `equipment_name`           varchar(128) DEFAULT NULL COMMENT '设备名称',
  `equipment_type`           varchar(64)  DEFAULT NULL COMMENT '设备类型',
  `equipment_status`         varchar(32)  DEFAULT NULL COMMENT '设备状态',
  `equipment_quantity`       double       DEFAULT 0 COMMENT '数量',
  `equipment_pictures`       varchar(255) DEFAULT NULL COMMENT '设备图片',
  `usage`                    varchar(255) DEFAULT NULL COMMENT '用途',
  `equipment_introduction`   varchar(500) DEFAULT NULL COMMENT '设备介绍',
  `device_details`           text COMMENT '设备详情',
  `update_time`              datetime     DEFAULT NULL,
  `create_time`              datetime     DEFAULT NULL,
  PRIMARY KEY (`device_information_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='设备信息';

DROP TABLE IF EXISTS `financial_statements`;
CREATE TABLE `financial_statements` (
  `financial_statements_id`  int(11)      NOT NULL AUTO_INCREMENT,
  `report_name`              varchar(255) DEFAULT NULL COMMENT '报表名称',
  `report_type`              varchar(64)  DEFAULT NULL COMMENT '报表类型',
  `statistical_date`         datetime     DEFAULT NULL COMMENT '统计日期',
  `statistical_cycle`        varchar(64)  DEFAULT NULL COMMENT '统计周期',
  `statistical_amount`       double       DEFAULT 0 COMMENT '统计金额',
  `statement_details`        text COMMENT '报表详情',
  `update_time`              datetime     DEFAULT NULL,
  `create_time`              datetime     DEFAULT NULL,
  PRIMARY KEY (`financial_statements_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='财务报表';

-- ------------------------------------------------------------
-- 9. 互动：评论 / 收藏 / 点赞 / 浏览
-- ------------------------------------------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `comment_id`    int(11)      NOT NULL AUTO_INCREMENT,
  `user_id`       int(11)      DEFAULT NULL,
  `reply_to_id`   int(11)      DEFAULT NULL,
  `content`       text,
  `nickname`      varchar(64)  DEFAULT NULL,
  `avatar`        varchar(255) DEFAULT NULL,
  `create_time`   datetime     DEFAULT NULL,
  `update_time`   datetime     DEFAULT NULL,
  `source_table`  varchar(64)  DEFAULT NULL,
  `source_field`  varchar(64)  DEFAULT NULL,
  `source_id`     int(11)      DEFAULT NULL,
  PRIMARY KEY (`comment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='评论';

DROP TABLE IF EXISTS `collect`;
CREATE TABLE `collect` (
  `collect_id`    int(11)      NOT NULL AUTO_INCREMENT,
  `user_id`       int(11)      DEFAULT NULL,
  `source_table`  varchar(64)  DEFAULT NULL,
  `source_field`  varchar(64)  DEFAULT NULL,
  `source_id`     int(11)      DEFAULT NULL,
  `title`         varchar(255) DEFAULT NULL,
  `img`           varchar(255) DEFAULT NULL,
  `create_time`   datetime     DEFAULT NULL,
  `update_time`   datetime     DEFAULT NULL,
  PRIMARY KEY (`collect_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='收藏';

DROP TABLE IF EXISTS `praise`;
CREATE TABLE `praise` (
  `praise_id`     int(11)      NOT NULL AUTO_INCREMENT,
  `user_id`       int(11)      DEFAULT NULL,
  `create_time`   datetime     DEFAULT NULL,
  `update_time`   datetime     DEFAULT NULL,
  `source_table`  varchar(64)  DEFAULT NULL,
  `source_field`  varchar(64)  DEFAULT NULL,
  `source_id`     int(11)      DEFAULT NULL,
  `status`        int(11)      DEFAULT 0,
  PRIMARY KEY (`praise_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='点赞';

DROP TABLE IF EXISTS `hits`;
CREATE TABLE `hits` (
  `hits_id`       int(11)      NOT NULL AUTO_INCREMENT,
  `user_id`       int(11)      DEFAULT NULL,
  `create_time`   datetime     DEFAULT NULL,
  `update_time`   datetime     DEFAULT NULL,
  `source_table`  varchar(64)  DEFAULT NULL,
  `source_field`  varchar(64)  DEFAULT NULL,
  `source_id`     int(11)      DEFAULT NULL,
  PRIMARY KEY (`hits_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='浏览记录';

-- ------------------------------------------------------------
-- 10. 文件 / 令牌
-- ------------------------------------------------------------
DROP TABLE IF EXISTS `upload`;
CREATE TABLE `upload` (
  `upload_id` int(11)      NOT NULL AUTO_INCREMENT,
  `name`      varchar(255) DEFAULT NULL,
  `path`      varchar(255) DEFAULT NULL,
  `file`      varchar(255) DEFAULT NULL,
  `display`   int(11)      DEFAULT 1,
  `father_id` int(11)      DEFAULT 0,
  `dir`       varchar(255) DEFAULT NULL,
  `type`      varchar(64)  DEFAULT NULL,
  PRIMARY KEY (`upload_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='上传文件';

DROP TABLE IF EXISTS `access_token`;
CREATE TABLE `access_token` (
  `token_id`    int(11)      NOT NULL AUTO_INCREMENT,
  `token`       varchar(128) DEFAULT NULL,
  `maxage`      int(11)      DEFAULT 7200,
  `create_time` datetime     DEFAULT NULL,
  `update_time` datetime     DEFAULT NULL,
  `user_id`     int(11)      DEFAULT NULL,
  PRIMARY KEY (`token_id`),
  KEY `idx_token` (`token`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='访问令牌';

DROP TABLE IF EXISTS `code_token`;
CREATE TABLE `code_token` (
  `code_token_id` int(11)      NOT NULL AUTO_INCREMENT,
  `token`         varchar(128) DEFAULT NULL,
  `code`          varchar(32)  DEFAULT NULL,
  `expire_time`   datetime     DEFAULT NULL,
  `create_time`   datetime     DEFAULT NULL,
  `update_time`   datetime     DEFAULT NULL,
  PRIMARY KEY (`code_token_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='验证码令牌';

-- ============================================================
-- 演示数据
-- ============================================================

-- 用户组（source_table 留空：登录时无需额外审核）
INSERT INTO `user_group` (`group_id`, `display`, `name`, `description`, `source_table`, `source_field`, `source_id`, `register`, `create_time`, `update_time`) VALUES
(1, 1, '管理员',   '网站管理员，可登录后台管理端', NULL, NULL, NULL, '0', NOW(), NOW()),
(2, 1, '会员用户', '普通会员用户，可登录用户端',   NULL, NULL, NULL, '2', NOW(), NOW());

-- 默认账号：admin / 123456（后台）、member / 123456（前台）
-- 密码由前端 RSA 加密传输、后端解密后与库中明文比较，因此这里直接存明文
INSERT INTO `user` (`user_id`, `state`, `user_group`, `login_time`, `phone`, `phone_state`, `username`, `nickname`, `password`, `email`, `email_state`, `avatar`, `open_id`, `create_time`) VALUES
(1, 1, '管理员',   NULL, '13800138000', 2, 'admin',  '管理员', '123456', 'admin@example.com',  2, '/api/upload/admin_avatar.jpg', NULL, NOW()),
(2, 1, '会员用户', NULL, '13900139000', 2, 'member', '会员小李', '123456', 'member@example.com', 2, '/api/upload/avatar.jpg',       NULL, NOW());

INSERT INTO `member_user` (`member_user_id`, `member_name`, `contact_information`, `membership_type`, `due_date`, `account_balance`, `user_id`, `update_time`, `create_time`) VALUES
(1, '会员小李', '13900139000', '年卡', '2027-12-31 23:59:59', 500.00, 2, NOW(), NOW());

-- 后台菜单权限（管理员）：列表页 + 详情/编辑页
INSERT INTO `auth` (`user_group`, `mod_name`, `table_name`, `page_title`, `path`, `position`, `mode`, `add`, `del`, `set`, `get`, `table_nav_name`, `table_nav`, `parent`, `parent_sort`, `create_time`, `update_time`) VALUES
('管理员', '轮播图',   'slides',              '轮播图',   '/slides/table',              'left', '1', 1, 1, 1, 1, NULL, 0, NULL, 0, NOW(), NOW()),
('管理员', '轮播图',   'slides',              '轮播图',   '/slides/view',               'left', '1', 1, 1, 1, 1, NULL, 0, NULL, 0, NOW(), NOW()),
('管理员', '公告',     'notice',              '网站公告', '/notice/table',              'left', '1', 1, 1, 1, 1, NULL, 0, NULL, 0, NOW(), NOW()),
('管理员', '公告',     'notice',              '网站公告', '/notice/view',               'left', '1', 1, 1, 1, 1, NULL, 0, NULL, 0, NOW(), NOW()),
('管理员', '文章',     'article',             '健身资讯', '/article/table',             'left', '1', 1, 1, 1, 1, NULL, 0, NULL, 0, NOW(), NOW()),
('管理员', '文章',     'article',             '健身资讯', '/article/view',              'left', '1', 1, 1, 1, 1, NULL, 0, NULL, 0, NOW(), NOW()),
('管理员', '文章分类', 'article_type',        '分类',     '/article_type/table',        'left', '1', 1, 1, 1, 1, NULL, 0, NULL, 0, NOW(), NOW()),
('管理员', '文章分类', 'article_type',        '分类',     '/article_type/view',         'left', '1', 1, 1, 1, 1, NULL, 0, NULL, 0, NOW(), NOW()),
('管理员', '评论',     'comment',             '评论',     '/comment/table',             'left', '1', 1, 1, 1, 1, NULL, 0, NULL, 0, NOW(), NOW()),
('管理员', '评论',     'comment',             '评论',     '/comment/view',              'left', '1', 1, 1, 1, 1, NULL, 0, NULL, 0, NOW(), NOW()),
('管理员', '会员用户', 'member_user',         '会员用户', '/member_user/table',         'left', '1', 1, 1, 1, 1, NULL, 0, NULL, 0, NOW(), NOW()),
('管理员', '会员用户', 'member_user',         '会员用户', '/member_user/view',          'left', '1', 1, 1, 1, 1, NULL, 0, NULL, 0, NOW(), NOW()),
('管理员', '课程信息', 'course_information',  '课程信息', '/course_information/table',  'left', '1', 1, 1, 1, 1, NULL, 0, NULL, 0, NOW(), NOW()),
('管理员', '课程信息', 'course_information',  '课程信息', '/course_information/view',   'left', '1', 1, 1, 1, 1, NULL, 0, NULL, 0, NOW(), NOW()),
('管理员', '课程参与', 'course_participation', '课程参与', '/course_participation/table', 'left', '1', 1, 1, 1, 1, NULL, 0, NULL, 0, NOW(), NOW()),
('管理员', '课程参与', 'course_participation', '课程参与', '/course_participation/view',  'left', '1', 1, 1, 1, 1, NULL, 0, NULL, 0, NOW(), NOW()),
('管理员', '健身建议', 'fitness_advice',      '健身建议', '/fitness_advice/table',      'left', '1', 1, 1, 1, 1, NULL, 0, NULL, 0, NOW(), NOW()),
('管理员', '健身建议', 'fitness_advice',      '健身建议', '/fitness_advice/view',       'left', '1', 1, 1, 1, 1, NULL, 0, NULL, 0, NOW(), NOW()),
('管理员', '会员卡',   'membership_card',     '会员卡',   '/membership_card/table',     'left', '1', 1, 1, 1, 1, NULL, 0, NULL, 0, NOW(), NOW()),
('管理员', '会员卡',   'membership_card',     '会员卡',   '/membership_card/view',      'left', '1', 1, 1, 1, 1, NULL, 0, NULL, 0, NOW(), NOW()),
('管理员', '设备信息', 'device_information',  '设备信息', '/device_information/table',  'left', '1', 1, 1, 1, 1, NULL, 0, NULL, 0, NOW(), NOW()),
('管理员', '设备信息', 'device_information',  '设备信息', '/device_information/view',   'left', '1', 1, 1, 1, 1, NULL, 0, NULL, 0, NOW(), NOW()),
('管理员', '财务报表', 'financial_statements', '财务报表', '/financial_statements/table', 'left', '1', 1, 1, 1, 1, NULL, 0, NULL, 0, NOW(), NOW()),
('管理员', '财务报表', 'financial_statements', '财务报表', '/financial_statements/view',  'left', '1', 1, 1, 1, 1, NULL, 0, NULL, 0, NOW(), NOW()),
('管理员', '用户',     'user',                '管理员',   '/user/table',                'left', '1', 1, 1, 1, 1, NULL, 0, NULL, 0, NOW(), NOW()),
('管理员', '用户',     'user',                '管理员',   '/user/view',                 'left', '1', 1, 1, 1, 1, NULL, 0, NULL, 0, NOW(), NOW()),
('管理员', '用户组',   'user_group',          '用户组',   '/user_group/table',          'left', '1', 1, 1, 1, 1, NULL, 0, NULL, 0, NOW(), NOW()),
('管理员', '用户组',   'user_group',          '用户组',   '/user_group/view',           'left', '1', 1, 1, 1, 1, NULL, 0, NULL, 0, NOW(), NOW());

-- 用户端顶部导航（游客 / 会员用户）
INSERT INTO `auth` (`user_group`, `mod_name`, `table_name`, `page_title`, `path`, `position`, `mode`, `add`, `del`, `set`, `get`, `table_nav_name`, `table_nav`, `parent`, `parent_sort`, `create_time`, `update_time`) VALUES
('游客',     '课程信息', 'course_information', '健身课程', '/course_information/list', 'top', '1', 0, 0, 0, 1, NULL, 0, NULL, 1, NOW(), NOW()),
('游客',     '会员卡',   'membership_card',    '会员卡',   '/membership_card/list',    'top', '1', 0, 0, 0, 1, NULL, 0, NULL, 2, NOW(), NOW()),
('游客',     '文章',     'article',            '健身资讯', '/article/list',            'top', '1', 0, 0, 0, 1, NULL, 0, NULL, 3, NOW(), NOW()),
('游客',     '公告',     'notice',             '网站公告', '/notice/list',             'top', '1', 0, 0, 0, 1, NULL, 0, NULL, 4, NOW(), NOW()),
('会员用户', '课程信息', 'course_information', '健身课程', '/course_information/list', 'top', '1', 0, 0, 0, 1, NULL, 0, NULL, 1, NOW(), NOW()),
('会员用户', '会员卡',   'membership_card',    '会员卡',   '/membership_card/list',    'top', '1', 0, 0, 0, 1, NULL, 0, NULL, 2, NOW(), NOW()),
('会员用户', '文章',     'article',            '健身资讯', '/article/list',            'top', '1', 0, 0, 0, 1, NULL, 0, NULL, 3, NOW(), NOW()),
('会员用户', '公告',     'notice',             '网站公告', '/notice/list',             'top', '1', 0, 0, 0, 1, NULL, 0, NULL, 4, NOW(), NOW());

-- 文章分类
INSERT INTO `article_type` (`type_id`, `display`, `name`, `father_id`, `description`, `icon`, `url`, `create_time`, `update_time`) VALUES
(1, 1, '健身资讯', 0, '健身行业新闻与动态', NULL, NULL, NOW(), NOW()),
(2, 1, '训练技巧', 0, '动作与训练方法',     NULL, NULL, NOW(), NOW());

-- 轮播图（图片文件已随项目放在 src/main/resources/static 下）
INSERT INTO `slides` (`slides_id`, `title`, `content`, `url`, `img`, `hits`, `create_time`, `update_time`) VALUES
(1, '健身房会员招募中', '专业器械、专业教练，欢迎加入', '/course_information/list', '~/api/upload/img_1.jpg', 0, NOW(), NOW()),
(2, '私教体验课',       '一对一私教体验，限时开放',     '/course_information/list', '~/api/upload/img_2.jpg', 0, NOW(), NOW()),
(3, '健身课程上新',     '更多团课与训练营等你来',       '/course_information/list', '~/api/upload/img_3.jpg', 0, NOW(), NOW());

-- 网站公告
INSERT INTO `notice` (`notice_id`, `title`, `content`, `create_time`, `update_time`) VALUES
(1, '系统上线通知', '健身房管理系统正式上线，欢迎使用。', NOW(), NOW()),
(2, '国庆营业时间调整', '节假日期间营业时间调整为 10:00-21:00。', NOW(), NOW()),
(3, '器械维护通知', '三楼有氧区器械将于本周六上午维护，请合理安排训练。', NOW(), NOW());

-- 健身资讯
INSERT INTO `article` (`article_id`, `title`, `type`, `hits`, `praise_len`, `create_time`, `update_time`, `source`, `url`, `tag`, `content`, `img`, `description`) VALUES
(1, '新手健身入门指南', '健身资讯', 128, 12, NOW(), NOW(), '本站', NULL, '新手 入门', '初次进入健身房时，建议先从有氧和基础力量训练开始，循序渐进，注意热身与拉伸。', '~/api/upload/forum_1.jpg', '新手第一次去健身房该练什么'),
(2, '力量训练的五个要点', '训练技巧', 96, 8, NOW(), NOW(), '本站', NULL, '力量 训练', '保持核心稳定、控制动作节奏、选择合适的重量、保证组间休息、重视恢复。', '~/api/upload/forum_2.jpg', '力量训练要点总结'),
(3, '如何制定减脂计划', '训练技巧', 210, 25, NOW(), NOW(), '本站', NULL, '减脂 计划', '减脂的核心是热量缺口，配合每周 3-5 次有氧与力量训练，同时保证蛋白质摄入。', '~/api/upload/forum_3.jpg', '科学减脂从计划开始');

-- 课程信息
INSERT INTO `course_information` (`course_information_id`, `course_name`, `course_type`, `course_time`, `course_location`, `name_of_coach`, `course_picture`, `course_introduction`, `course_details`, `hits`, `praise_len`, `collect_len`, `comment_len`, `course_participation_limit_times`, `update_time`, `create_time`) VALUES
(1, '燃脂动感单车', '团课', '周一/周三/周五 19:00-20:00', '三楼单车房', '王教练', '~/api/upload/goods.jpg', '高强度有氧，快速燃脂', '适合有一定运动基础的会员，课程包含热身、骑行冲刺与拉伸。', 58, 6, 2, 1, '1', NOW(), NOW()),
(2, '基础力量训练', '私教', '周二/周四 10:00-11:00', '二楼力量区', '李教练', '~/api/upload/img_4.jpg', '从零学习器械与自由重量', '包含深蹲、硬拉、卧推等基础动作教学与纠错。', 43, 4, 1, 0, '1', NOW(), NOW()),
(3, '瑜伽舒缓', '团课', '周六/周日 09:00-10:00', '四楼瑜伽室', '赵教练', '~/api/upload/img_5.jpg', '放松身心，改善柔韧性', '呼吸练习、体式串联与冥想放松。', 77, 9, 3, 2, '1', NOW(), NOW());

-- 课程参与
INSERT INTO `course_participation` (`course_participation_id`, `course_name`, `course_type`, `course_time`, `course_location`, `name_of_coach`, `member_user`, `member_name`, `participation_notes`, `fitness_advice_limit_times`, `source_table`, `source_id`, `source_user_id`, `update_time`, `create_time`) VALUES
(1, '燃脂动感单车', '团课', '周一/周三/周五 19:00-20:00', '三楼单车房', '王教练', 1, '会员小李', '首次参加，请安排教练指导', '1', 'course_information', 1, 2, NOW(), NOW());

-- 会员卡
INSERT INTO `membership_card` (`membership_card_id`, `membership_card_name`, `membership_card_type`, `membership_card_expiration_date`, `membership_card_fees`, `membership_card_discounts`, `membership_card_introduction`, `membership_card_details`, `hits`, `praise_len`, `collect_len`, `comment_len`, `update_time`, `create_time`) VALUES
(1, '年卡', '时限卡', '365天', 2999.00, '私教课 9 折', '一年内不限次数使用器械与团课', '包含体测、训练计划与团课预约。', 156, 18, 9, 3, NOW(), NOW()),
(2, '季卡', '时限卡', '90天',  999.00, '团课免费',     '三个月健身权益，适合短期训练',   '包含器械区、有氧区与常规团课。', 88, 7, 4, 1, NOW(), NOW());

-- 设备信息
INSERT INTO `device_information` (`device_information_id`, `equipment_number`, `equipment_name`, `equipment_type`, `equipment_status`, `equipment_quantity`, `equipment_pictures`, `usage`, `equipment_introduction`, `device_details`, `update_time`, `create_time`) VALUES
(1, 'EQ-001', '跑步机', '有氧器械', '正常', 10, '~/api/upload/img_1.jpg', '有氧热身、减脂', '商用级跑步机，支持坡度调节', '使用前请检查安全锁扣。', NOW(), NOW()),
(2, 'EQ-002', '史密斯机', '力量器械', '正常', 2, '~/api/upload/img_2.jpg', '深蹲、卧推', '带保护装置的史密斯架', '建议在教练指导下使用。', NOW(), NOW()),
(3, 'EQ-003', '动感单车', '有氧器械', '维护中', 20, '~/api/upload/img_3.jpg', '团课骑行', '商用动感单车', '3 号单车近期检修。', NOW(), NOW());

-- 财务报表
INSERT INTO `financial_statements` (`financial_statements_id`, `report_name`, `report_type`, `statistical_date`, `statistical_cycle`, `statistical_amount`, `statement_details`, `update_time`, `create_time`) VALUES
(1, '2026年10月会员卡收入', '收入', '2026-10-31 00:00:00', '按月', 58600.00, '年卡 15 张、季卡 13 张。', NOW(), NOW()),
(2, '2026年10月私教课收入', '收入', '2026-10-31 00:00:00', '按月', 23400.00, '私教课 156 节。', NOW(), NOW()),
(3, '2026年10月器械维护支出', '支出', '2026-10-31 00:00:00', '按月', 8200.00, '跑步机保养与配件更换。', NOW(), NOW());

-- 健身建议
INSERT INTO `fitness_advice` (`fitness_advice_id`, `member_user`, `member_name`, `suggested_title`, `recommend_courses`, `suggested_content`, `source_table`, `source_id`, `source_user_id`, `update_time`, `create_time`) VALUES
(1, 1, '会员小李', '减脂期训练建议', '燃脂动感单车、基础力量训练', '每周 3 次有氧 + 2 次力量训练，控制饮食并保证睡眠。', 'member_user', 1, 2, NOW(), NOW());

SET FOREIGN_KEY_CHECKS = 1;
