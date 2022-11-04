/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80024
 Source Host           : localhost:3306
 Source Schema         : jade

 Target Server Type    : MySQL
 Target Server Version : 80024
 File Encoding         : 65001

 Date: 04/11/2022 12:04:53
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_group
-- ----------------------------
DROP TABLE IF EXISTS `sys_group`;
CREATE TABLE `sys_group`  (
  `group_id` bigint NOT NULL AUTO_INCREMENT,
  `group_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
  `group_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
  `group_desc` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
  `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
  `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`group_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_group
-- ----------------------------
INSERT INTO `sys_group` VALUES (1, '管理', 'ADMIN', '', '0', 'admin', 'admin', '2022-07-23 19:42:51', '2022-07-23 19:42:51');
INSERT INTO `sys_group` VALUES (2, '测试', '', '', '0', '', 'admin', '2022-07-23 19:43:02', '2022-07-23 19:50:09');

-- ----------------------------
-- Table structure for sys_group_file
-- ----------------------------
DROP TABLE IF EXISTS `sys_group_file`;
CREATE TABLE `sys_group_file`  (
  `group_id` bigint NOT NULL,
  `file_id` bigint NOT NULL,
  PRIMARY KEY (`group_id`, `file_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_group_file
-- ----------------------------
INSERT INTO `sys_group_file` VALUES (1, 7);
INSERT INTO `sys_group_file` VALUES (1, 8);
INSERT INTO `sys_group_file` VALUES (1, 9);

-- ----------------------------
-- Table structure for sys_log_login
-- ----------------------------
DROP TABLE IF EXISTS `sys_log_login`;
CREATE TABLE `sys_log_login`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
  `msg` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
  `login_ip` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
  `login_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_log_login
-- ----------------------------
INSERT INTO `sys_log_login` VALUES (1, 'admin', '0', '登录成功', '127.0.0.1', NULL);
INSERT INTO `sys_log_login` VALUES (2, 'admin', '0', '登录成功', '127.0.0.1', NULL);
INSERT INTO `sys_log_login` VALUES (3, 'songyx', '1', '登录失败', '127.0.0.1', '2022-07-23 19:55:54');
INSERT INTO `sys_log_login` VALUES (4, 'songyx', '0', '登录成功', '127.0.0.1', '2022-07-23 19:56:35');

-- ----------------------------
-- Table structure for sys_log_oper
-- ----------------------------
DROP TABLE IF EXISTS `sys_log_oper`;
CREATE TABLE `sys_log_oper`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `title` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
  `status` tinyint(1) NULL DEFAULT NULL,
  `business_type` tinyint(1) NULL DEFAULT NULL,
  `method` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
  `request_method` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
  `oper_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
  `oper_url` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
  `oper_ip` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
  `oper_param` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
  `json_result` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
  `error_msg` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
  `oper_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_log_oper
-- ----------------------------
INSERT INTO `sys_log_oper` VALUES (1, '添加角色', 0, 0, 'com.jade.system.biz.controller.RoleController.save()', 'POST', 'admin', '/role', '127.0.0.1', '{\"createBy\":\"admin\",\"roleDesc\":\"\",\"createTime\":\"2022-07-23T19:41:51.694\",\"updateBy\":\"admin\",\"roleId\":1,\"roleCode\":\"ADMIN\",\"roleName\":\"管理员\",\"updateTime\":\"2022-07-23T19:41:51.694\",\"delFlag\":\"\"}', '{\"code\":200,\"data\":true}', '', NULL);
INSERT INTO `sys_log_oper` VALUES (2, '添加角色', 0, 0, 'com.jade.system.biz.controller.RoleController.save()', 'POST', 'admin', '/role', '127.0.0.1', '{\"createBy\":\"admin\",\"roleDesc\":\"\",\"createTime\":\"2022-07-23T19:42:06.394\",\"updateBy\":\"admin\",\"roleId\":2,\"roleCode\":\"TEST\",\"roleName\":\"测试\",\"updateTime\":\"2022-07-23T19:42:06.394\",\"delFlag\":\"\"}', '{\"code\":200,\"data\":true}', '', NULL);
INSERT INTO `sys_log_oper` VALUES (3, '添加角色', 0, 0, 'com.jade.system.biz.controller.RoleController.save()', 'POST', 'admin', '/role', '127.0.0.1', '{\"createBy\":\"admin\",\"roleDesc\":\"\",\"createTime\":\"2022-07-23T19:42:22.460\",\"updateBy\":\"admin\",\"roleId\":3,\"roleCode\":\"COMMON\",\"roleName\":\"普通用户\",\"updateTime\":\"2022-07-23T19:42:22.460\",\"delFlag\":\"\"}', '{\"code\":200,\"data\":true}', '', NULL);
INSERT INTO `sys_log_oper` VALUES (4, '添加角色', 0, 0, 'com.jade.system.biz.controller.RoleController.save()', 'POST', 'admin', '/role', '127.0.0.1', '{\"createBy\":\"admin\",\"roleDesc\":\"\",\"createTime\":\"2022-07-23T19:57:54.634\",\"updateBy\":\"admin\",\"roleId\":4,\"roleCode\":\"rr\",\"roleName\":\"rr户\",\"updateTime\":\"2022-07-23T19:57:54.634\",\"delFlag\":\"\"}', '{\"code\":200,\"data\":true}', '', '2022-07-23 19:57:54');
INSERT INTO `sys_log_oper` VALUES (5, '修改角色', 0, 0, 'com.jade.system.biz.controller.RoleController.update()', 'PUT', 'admin', '/role', '127.0.0.1', '{\"createBy\":\"\",\"roleDesc\":\"\",\"updateBy\":\"admin\",\"roleId\":4,\"roleCode\":\"ee\",\"roleName\":\"ee\",\"updateTime\":\"2022-07-23T19:58:43.571\",\"delFlag\":\"\"}', '{\"code\":200,\"data\":true}', '', '2022-07-23 19:58:43');
INSERT INTO `sys_log_oper` VALUES (6, '删除角色', 0, 0, 'com.jade.system.biz.controller.RoleController.removeById()', 'DELETE', 'admin', '/role/4', '127.0.0.1', '', '{\"code\":200,\"data\":true}', '', '2022-07-23 19:59:08');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `menu_id` bigint NOT NULL AUTO_INCREMENT,
  `parent_id` bigint NULL DEFAULT NULL,
  `menu_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
  `permission` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
  `route` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
  `sort_num` int NULL DEFAULT 0,
  `type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
  `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
  `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9999 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1000, -1, '权限管理', NULL, '/admin', 1, '0', '0', '0', ' ', ' ', '2018-09-28 08:29:53', '2020-03-11 23:58:18');
INSERT INTO `sys_menu` VALUES (1100, 1000, '用户管理', NULL, '/admin/user/index', 0, '0', '0', '0', ' ', ' ', '2017-11-02 22:24:37', '2020-03-12 00:12:57');
INSERT INTO `sys_menu` VALUES (1101, 1100, '用户新增', 'sys_user_add', NULL, 0, '1', '0', '0', ' ', ' ', '2017-11-08 09:52:09', '2021-05-25 06:48:34');
INSERT INTO `sys_menu` VALUES (1102, 1100, '用户修改', 'sys_user_edit', NULL, 0, '1', '0', '0', ' ', ' ', '2017-11-08 09:52:48', '2021-05-25 06:48:34');
INSERT INTO `sys_menu` VALUES (1103, 1100, '用户删除', 'sys_user_del', NULL, 0, '1', '0', '0', ' ', ' ', '2017-11-08 09:54:01', '2021-05-25 06:48:34');
INSERT INTO `sys_menu` VALUES (1104, 1100, '导入导出', 'sys_user_import_export', NULL, 0, '1', '0', '0', ' ', ' ', '2017-11-08 09:54:01', '2021-05-25 06:48:34');
INSERT INTO `sys_menu` VALUES (1200, 1000, '菜单管理', NULL, '/admin/menu/index', 1, '0', '0', '0', ' ', ' ', '2017-11-08 09:57:27', '2020-03-12 00:13:52');
INSERT INTO `sys_menu` VALUES (1201, 1200, '菜单新增', 'sys_menu_add', NULL, 0, '1', '0', '0', ' ', ' ', '2017-11-08 10:15:53', '2021-05-25 06:48:34');
INSERT INTO `sys_menu` VALUES (1202, 1200, '菜单修改', 'sys_menu_edit', NULL, 0, '1', '0', '0', ' ', ' ', '2017-11-08 10:16:23', '2021-05-25 06:48:34');
INSERT INTO `sys_menu` VALUES (1203, 1200, '菜单删除', 'sys_menu_del', NULL, 0, '1', '0', '0', ' ', ' ', '2017-11-08 10:16:43', '2021-05-25 06:48:34');
INSERT INTO `sys_menu` VALUES (1300, 1000, '角色管理', NULL, '/admin/role/index', 2, '0', '0', '0', ' ', ' ', '2017-11-08 10:13:37', '2020-03-12 00:15:40');
INSERT INTO `sys_menu` VALUES (1301, 1300, '角色新增', 'sys_role_add', NULL, 0, '1', '0', '0', ' ', ' ', '2017-11-08 10:14:18', '2021-05-25 06:48:34');
INSERT INTO `sys_menu` VALUES (1302, 1300, '角色修改', 'sys_role_edit', NULL, 0, '1', '0', '0', ' ', ' ', '2017-11-08 10:14:41', '2021-05-25 06:48:34');
INSERT INTO `sys_menu` VALUES (1303, 1300, '角色删除', 'sys_role_del', NULL, 0, '1', '0', '0', ' ', ' ', '2017-11-08 10:14:59', '2021-05-25 06:48:34');
INSERT INTO `sys_menu` VALUES (1304, 1300, '分配权限', 'sys_role_perm', NULL, 0, '1', '0', '0', ' ', ' ', '2018-04-20 07:22:55', '2021-05-25 06:48:34');
INSERT INTO `sys_menu` VALUES (1305, 1300, '导入导出', 'sys_role_import_export', NULL, 0, '1', '0', '0', 'admin', 'admin', '2022-03-21 11:14:52', '2022-03-21 11:15:07');
INSERT INTO `sys_menu` VALUES (1400, 1000, '部门管理', NULL, '/admin/dept/index', 3, '0', '0', '0', ' ', ' ', '2018-01-20 13:17:19', '2020-03-12 00:15:44');
INSERT INTO `sys_menu` VALUES (1401, 1400, '部门新增', 'sys_dept_add', NULL, 0, '1', '0', '0', ' ', ' ', '2018-01-20 14:56:16', '2021-05-25 06:48:34');
INSERT INTO `sys_menu` VALUES (1402, 1400, '部门修改', 'sys_dept_edit', NULL, 0, '1', '0', '0', ' ', ' ', '2018-01-20 14:56:59', '2021-05-25 06:48:34');
INSERT INTO `sys_menu` VALUES (1403, 1400, '部门删除', 'sys_dept_del', NULL, 0, '1', '0', '0', ' ', ' ', '2018-01-20 14:57:28', '2021-05-25 06:48:34');
INSERT INTO `sys_menu` VALUES (1500, 1000, '岗位管理', NULL, '/admin/post/index', 4, '0', '0', '0', NULL, 'admin', '2018-01-20 13:17:19', '2022-03-15 17:32:06');
INSERT INTO `sys_menu` VALUES (1501, 1500, '岗位查看', 'sys_post_get', NULL, 0, '1', '0', '0', NULL, 'admin', '2018-05-15 21:35:18', '2022-03-15 17:32:54');
INSERT INTO `sys_menu` VALUES (1502, 1500, '岗位新增', 'sys_post_add', NULL, 1, '1', '0', '0', NULL, 'admin', '2018-05-15 21:35:18', '2022-03-15 17:32:48');
INSERT INTO `sys_menu` VALUES (1503, 1500, '岗位修改', 'sys_post_edit', NULL, 2, '1', '0', '0', NULL, 'admin', '2018-05-15 21:35:18', '2022-03-15 17:33:10');
INSERT INTO `sys_menu` VALUES (1504, 1500, '岗位删除', 'sys_post_del', NULL, 3, '1', '0', '0', NULL, 'admin', '2018-05-15 21:35:18', '2022-03-15 17:33:27');
INSERT INTO `sys_menu` VALUES (1505, 1500, '导入导出', 'sys_post_import_export', NULL, 4, '1', '0', '0', 'admin', 'admin', '2022-03-21 12:53:05', '2022-03-21 12:53:05');
INSERT INTO `sys_menu` VALUES (2000, -1, '系统管理', NULL, '/setting', 2, '0', '0', '0', ' ', ' ', '2017-11-07 20:56:00', '2020-03-11 23:52:53');
INSERT INTO `sys_menu` VALUES (2100, 2000, '日志管理', NULL, '/admin/log/index', 3, '0', '0', '0', ' ', ' ', '2017-11-20 14:06:22', '2020-03-12 00:15:49');
INSERT INTO `sys_menu` VALUES (2101, 2100, '日志删除', 'sys_log_del', NULL, 0, '1', '0', '0', ' ', ' ', '2017-11-20 20:37:37', '2021-05-25 06:48:34');
INSERT INTO `sys_menu` VALUES (2102, 2100, '导入导出', 'sys_log_import_export', NULL, 0, '1', '0', '0', ' ', ' ', '2017-11-08 09:54:01', '2021-05-25 06:48:34');
INSERT INTO `sys_menu` VALUES (2200, 2000, '字典管理', NULL, '/admin/dict/index', 2, '0', '0', '0', ' ', ' ', '2017-11-29 11:30:52', '2020-03-12 00:15:58');
INSERT INTO `sys_menu` VALUES (2201, 2200, '字典删除', 'sys_dict_del', NULL, 0, '1', '0', '0', ' ', ' ', '2017-11-29 11:30:11', '2021-05-25 06:48:34');
INSERT INTO `sys_menu` VALUES (2202, 2200, '字典新增', 'sys_dict_add', NULL, 0, '1', '0', '0', ' ', ' ', '2018-05-11 22:34:55', '2021-05-25 06:48:34');
INSERT INTO `sys_menu` VALUES (2203, 2200, '字典修改', 'sys_dict_edit', NULL, 0, '1', '0', '0', ' ', ' ', '2018-05-11 22:36:03', '2021-05-25 06:48:34');
INSERT INTO `sys_menu` VALUES (2300, 2000, '令牌管理', NULL, '/admin/token/index', 4, '0', '0', '0', ' ', ' ', '2018-09-04 05:58:41', '2020-03-13 12:57:25');
INSERT INTO `sys_menu` VALUES (2301, 2300, '令牌删除', 'sys_token_del', NULL, 0, '1', '0', '0', ' ', ' ', '2018-09-04 05:59:50', '2020-03-13 12:57:34');
INSERT INTO `sys_menu` VALUES (2400, 2000, '终端管理', NULL, '/admin/client/index', 0, '0', '0', '0', ' ', ' ', '2018-01-20 13:17:19', '2020-03-12 00:15:54');
INSERT INTO `sys_menu` VALUES (2401, 2400, '客户端新增', 'sys_client_add', NULL, 0, '1', '0', '0', ' ', ' ', '2018-05-15 21:35:18', '2021-05-25 06:48:34');
INSERT INTO `sys_menu` VALUES (2402, 2400, '客户端修改', 'sys_client_edit', NULL, 0, '1', '0', '0', ' ', ' ', '2018-05-15 21:37:06', '2021-05-25 06:48:34');
INSERT INTO `sys_menu` VALUES (2403, 2400, '客户端删除', 'sys_client_del', NULL, 0, '1', '0', '0', ' ', ' ', '2018-05-15 21:39:16', '2021-05-25 06:48:34');
INSERT INTO `sys_menu` VALUES (2600, 2000, '文件管理', NULL, '/admin/file/index', 1, '0', '0', '0', ' ', ' ', '2018-06-26 10:50:32', '2019-02-01 20:41:30');
INSERT INTO `sys_menu` VALUES (2601, 2600, '文件删除', 'sys_file_del', NULL, 0, '1', '0', '0', ' ', ' ', '2017-11-29 11:30:11', '2021-05-25 06:48:34');
INSERT INTO `sys_menu` VALUES (2602, 2600, '文件新增', 'sys_file_add', NULL, 0, '1', '0', '0', ' ', ' ', '2018-05-11 22:34:55', '2021-05-25 06:48:34');
INSERT INTO `sys_menu` VALUES (2603, 2600, '文件修改', 'sys_file_edit', NULL, 0, '1', '0', '0', ' ', ' ', '2018-05-11 22:36:03', '2021-05-25 06:48:34');
INSERT INTO `sys_menu` VALUES (2700, 2000, '参数管理', NULL, '/admin/param/index', 5, '0', '0', '0', 'admin', 'admin', '2022-03-25 20:40:27', '2022-03-25 20:40:35');
INSERT INTO `sys_menu` VALUES (2701, 2700, '参数新增', 'sys_publicparam_add', NULL, 0, '1', '0', '0', 'admin', 'admin', '2022-03-25 20:45:05', '2022-03-25 20:45:05');
INSERT INTO `sys_menu` VALUES (2702, 2700, '参数删除', 'sys_publicparam_del', NULL, 1, '1', '0', '0', 'admin', 'admin', '2022-03-25 20:45:43', '2022-03-25 20:45:43');
INSERT INTO `sys_menu` VALUES (2703, 2700, '参数修改', 'sys_publicparam_edit', NULL, 3, '1', '0', '0', 'admin', 'admin', '2022-03-25 20:46:04', '2022-03-25 20:46:04');
INSERT INTO `sys_menu` VALUES (3000, -1, '开发平台', NULL, '/gen', 3, '0', '1', '0', ' ', ' ', '2020-03-11 22:15:40', '2020-03-11 23:52:54');
INSERT INTO `sys_menu` VALUES (3100, 3000, '数据源管理', NULL, '/gen/datasource', 3, '0', '1', '0', ' ', ' ', '2020-03-11 22:17:05', '2020-03-12 00:16:09');
INSERT INTO `sys_menu` VALUES (3200, 3000, '代码生成', NULL, '/gen/index', 0, '0', '0', '0', ' ', ' ', '2020-03-11 22:23:42', '2020-03-12 00:16:14');
INSERT INTO `sys_menu` VALUES (3300, 3000, '表单管理', NULL, '/gen/form', 1, '0', '1', '0', ' ', ' ', '2020-03-11 22:19:32', '2020-03-12 00:16:18');
INSERT INTO `sys_menu` VALUES (3301, 3300, '表单新增', 'gen_form_add', NULL, 0, '1', '0', '0', ' ', ' ', '2018-05-15 21:35:18', '2020-03-11 22:39:08');
INSERT INTO `sys_menu` VALUES (3302, 3300, '表单修改', 'gen_form_edit', NULL, 0, '1', '0', '0', ' ', ' ', '2018-05-15 21:35:18', '2020-03-11 22:39:09');
INSERT INTO `sys_menu` VALUES (3303, 3300, '表单删除', 'gen_form_del', NULL, 0, '1', '0', '0', ' ', ' ', '2018-05-15 21:35:18', '2020-03-11 22:39:11');
INSERT INTO `sys_menu` VALUES (3400, 3000, '表单设计', NULL, '/gen/design', 2, '0', '1', '0', ' ', ' ', '2020-03-11 22:18:05', '2020-03-12 00:16:25');
INSERT INTO `sys_menu` VALUES (4000, -1, '服务监控', NULL, 'http://localhost:5001/login', 4, '0', '0', '0', 'admin', 'admin', '2022-03-21 09:44:50', '2022-03-21 09:47:06');
INSERT INTO `sys_menu` VALUES (9999, -1, '系统官网', NULL, 'https://pig4cloud.com/#/', 999, '0', '0', '0', ' ', 'admin', '2019-01-17 17:05:19', '2020-03-11 23:52:57');

-- ----------------------------
-- Table structure for sys_oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `sys_oauth_client_details`;
CREATE TABLE `sys_oauth_client_details`  (
  `client_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '终端编号',
  `resource_ids` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资源ID标识',
  `client_secret` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '终端安全码',
  `scope` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '终端授权范围',
  `authorized_grant_types` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '终端授权类型',
  `web_server_redirect_uri` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '服务器回调地址',
  `authorities` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '访问资源所需权限',
  `access_token_validity` int NULL DEFAULT NULL COMMENT '设定终端的access_token的有效时间值（秒）',
  `refresh_token_validity` int NULL DEFAULT NULL COMMENT '设定终端的refresh_token的有效时间值（秒）',
  `additional_information` varchar(4096) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '附加信息',
  `autoapprove` tinyint NULL DEFAULT NULL COMMENT '是否登录时跳过授权',
  `origin_secret` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '终端明文安全码',
  PRIMARY KEY (`client_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '终端配置表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_oauth_client_details
-- ----------------------------
INSERT INTO `sys_oauth_client_details` VALUES ('admin', '', '$2a$10$y2hKeELx.z3Sbz.kjQ4wmuiIsv5ZSbUQ1ov4BwFH6ccirP8Knp1uq', 'server', 'password,client_credentials,refresh_token', '', NULL, 3600, 7200, NULL, NULL, '123456');
INSERT INTO `sys_oauth_client_details` VALUES ('web', '', '$2a$10$y2hKeELx.z3Sbz.kjQ4wmuiIsv5ZSbUQ1ov4BwFH6ccirP8Knp1uq', 'server', 'password,refresh_token', '', NULL, 3600, 7200, NULL, NULL, '123456');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `role_id` bigint NOT NULL AUTO_INCREMENT,
  `role_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
  `role_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
  `role_desc` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
  `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
  `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '管理员', 'ADMIN', '', '0', 'admin', 'admin', '2022-07-23 19:41:52', '2022-07-23 19:41:52');
INSERT INTO `sys_role` VALUES (2, '测试', 'TEST', '', '0', 'admin', 'admin', '2022-07-23 19:42:06', '2022-07-23 19:42:06');
INSERT INTO `sys_role` VALUES (3, '普通用户', 'COMMON', '', '0', 'admin', 'admin', '2022-07-23 19:42:22', '2022-07-23 19:42:22');
INSERT INTO `sys_role` VALUES (4, 'ee', 'ee', '', '1', '', 'admin', '2022-07-23 19:57:55', '2022-07-23 19:58:44');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `role_id` bigint NOT NULL,
  `menu_id` bigint NOT NULL,
  PRIMARY KEY (`role_id`, `menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (1, 1000);
INSERT INTO `sys_role_menu` VALUES (1, 1100);
INSERT INTO `sys_role_menu` VALUES (1, 1101);
INSERT INTO `sys_role_menu` VALUES (1, 1102);
INSERT INTO `sys_role_menu` VALUES (1, 1103);
INSERT INTO `sys_role_menu` VALUES (1, 1104);
INSERT INTO `sys_role_menu` VALUES (1, 1200);
INSERT INTO `sys_role_menu` VALUES (1, 1201);
INSERT INTO `sys_role_menu` VALUES (1, 1202);
INSERT INTO `sys_role_menu` VALUES (1, 1203);
INSERT INTO `sys_role_menu` VALUES (1, 1300);
INSERT INTO `sys_role_menu` VALUES (1, 1301);
INSERT INTO `sys_role_menu` VALUES (1, 1302);
INSERT INTO `sys_role_menu` VALUES (1, 1303);
INSERT INTO `sys_role_menu` VALUES (1, 1304);
INSERT INTO `sys_role_menu` VALUES (1, 1305);
INSERT INTO `sys_role_menu` VALUES (1, 1400);
INSERT INTO `sys_role_menu` VALUES (1, 1401);
INSERT INTO `sys_role_menu` VALUES (1, 1402);
INSERT INTO `sys_role_menu` VALUES (1, 1403);
INSERT INTO `sys_role_menu` VALUES (1, 1500);
INSERT INTO `sys_role_menu` VALUES (1, 1501);
INSERT INTO `sys_role_menu` VALUES (1, 1502);
INSERT INTO `sys_role_menu` VALUES (1, 1503);
INSERT INTO `sys_role_menu` VALUES (1, 1504);
INSERT INTO `sys_role_menu` VALUES (1, 1505);
INSERT INTO `sys_role_menu` VALUES (1, 2000);
INSERT INTO `sys_role_menu` VALUES (1, 2100);
INSERT INTO `sys_role_menu` VALUES (1, 2101);
INSERT INTO `sys_role_menu` VALUES (1, 2102);
INSERT INTO `sys_role_menu` VALUES (1, 2200);
INSERT INTO `sys_role_menu` VALUES (1, 2201);
INSERT INTO `sys_role_menu` VALUES (1, 2202);
INSERT INTO `sys_role_menu` VALUES (1, 2203);
INSERT INTO `sys_role_menu` VALUES (1, 2300);
INSERT INTO `sys_role_menu` VALUES (1, 2301);
INSERT INTO `sys_role_menu` VALUES (1, 2400);
INSERT INTO `sys_role_menu` VALUES (1, 2401);
INSERT INTO `sys_role_menu` VALUES (1, 2402);
INSERT INTO `sys_role_menu` VALUES (1, 2403);
INSERT INTO `sys_role_menu` VALUES (1, 2600);
INSERT INTO `sys_role_menu` VALUES (1, 2601);
INSERT INTO `sys_role_menu` VALUES (1, 2602);
INSERT INTO `sys_role_menu` VALUES (1, 2603);
INSERT INTO `sys_role_menu` VALUES (1, 2700);
INSERT INTO `sys_role_menu` VALUES (1, 2701);
INSERT INTO `sys_role_menu` VALUES (1, 2702);
INSERT INTO `sys_role_menu` VALUES (1, 2703);
INSERT INTO `sys_role_menu` VALUES (1, 3000);
INSERT INTO `sys_role_menu` VALUES (1, 3100);
INSERT INTO `sys_role_menu` VALUES (1, 3200);
INSERT INTO `sys_role_menu` VALUES (1, 3300);
INSERT INTO `sys_role_menu` VALUES (1, 3301);
INSERT INTO `sys_role_menu` VALUES (1, 3302);
INSERT INTO `sys_role_menu` VALUES (1, 3303);
INSERT INTO `sys_role_menu` VALUES (1, 3400);
INSERT INTO `sys_role_menu` VALUES (1, 4000);
INSERT INTO `sys_role_menu` VALUES (1, 9999);
INSERT INTO `sys_role_menu` VALUES (2, 4000);
INSERT INTO `sys_role_menu` VALUES (2, 9999);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `user_id` bigint NOT NULL AUTO_INCREMENT,
  `user_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
  `real_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
  `password` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
  `avatar` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
  `email` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
  `dept` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0',
  `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
  `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', 'admin', '$2a$10$Z1qxp8rprrUeMg4AJOtJUOqedw4KjJmLpw6LabL9L62xJnKlL08uK', '', '', '', '', '0', '', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (2, 'songyx', 'songyx', '$2a$10$5u965Ai33NF45IEuIkqzv.2elddr6G15OWGcXUIzaDSOX7zxc41km', '', '', '', '', '0', 'admin', 'admin', '2022-07-23 19:40:41', '2022-07-23 19:40:41');
INSERT INTO `sys_user` VALUES (3, 'test', 'test', '$2a$10$ytzUxtBOF/MesX9MD72slOR.zPdhu05.3BiWpegH2VWfHoNqauuOS', '', '', '', '', '0', 'admin', 'admin', '2022-07-23 19:41:19', '2022-07-23 19:41:19');

-- ----------------------------
-- Table structure for sys_user_group
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_group`;
CREATE TABLE `sys_user_group`  (
  `user_id` bigint NOT NULL,
  `group_id` bigint NOT NULL,
  PRIMARY KEY (`user_id`, `group_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_group
-- ----------------------------
INSERT INTO `sys_user_group` VALUES (1, 1);
INSERT INTO `sys_user_group` VALUES (2, 1);
INSERT INTO `sys_user_group` VALUES (3, 1);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `user_id` bigint NOT NULL,
  `role_id` bigint NOT NULL,
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 1);
INSERT INTO `sys_user_role` VALUES (2, 1);

SET FOREIGN_KEY_CHECKS = 1;
