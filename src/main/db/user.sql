CREATE TABLE `sys_users`.`sys_user`
(
    `id`          int(11)      NOT NULL AUTO_INCREMENT COMMENT '用户主键ID',
    `password`    varchar(50)  NOT NULL COMMENT '密码',
    `name`        varchar(50)  NOT NULL COMMENT '用户昵称',
    `tel`         varchar(20)  NULL COMMENT '手机号',
    `create_time` timestamp(3) NULL DEFAULT CURRENT_TIMESTAMP(3),
    `modify_time` timestamp(3) NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
    PRIMARY KEY (`id`)
);