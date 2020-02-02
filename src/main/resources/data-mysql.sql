 INSERT INTO  `user`

    (`userid`, `username`, `nickname`,`password`,`enabled`,`issuper`,`create_by`,`create_time`)

    SELECT '402883346fa22571016fa225b4650000',
    'root', 'root',
    '$2a$10$ODxXhwa0xf600yL8MggA1Ou3YwQSCBIaqX6f8ZfSHgYvBSQNN5B4W',1,1,'402883346fa22571016fa225b4650000'
    ,NOW()

    FROM DUAL

    WHERE NOT EXISTS (SELECT * FROM `user`

    WHERE user.`userid` = '402883346fa22571016fa225b4650000');


     INSERT INTO  `role`
    (`roleid`, `rolename`, `ps`,`create_by`,`create_time`)
     SELECT '403883346fa22571016aa225b4650000',
    'admin', '初始化新增',
    '402883346fa22571016fa225b4650000'
    ,NOW()
     FROM DUAL
     WHERE NOT EXISTS (SELECT * FROM `role`
    WHERE role.`roleid` = '403883346fa22571016aa225b4650000');

 INSERT INTO  `role`
    (`roleid`, `rolename`, `ps`,`create_by`,`create_time`)
     SELECT '403783346fa92576016aa225b4650000',
    'user', '初始化新增',
    '402883346fa22571016fa225b4650000'
    ,NOW()
     FROM DUAL
     WHERE NOT EXISTS (SELECT * FROM `role`
    WHERE role.`roleid` = '403783346fa92576016aa225b4650000');


     INSERT INTO  `user_roles`
    (`userid`, `roleid`)
     SELECT '402883346fa22571016fa225b4650000',
    '403883346fa22571016aa225b4650000'
     FROM DUAL
     WHERE NOT EXISTS (SELECT * FROM `user_roles`
    WHERE userid = '402883346fa22571016fa225b4650000');