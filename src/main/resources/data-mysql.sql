 INSERT INTO  `user`

    (`userid`, `username`, `nickname`,`password`,`enabled`,`issuper`,`create_by`,`create_time`)

    SELECT '402883346fa22571016fa225b4650000',
    'root', 'root',
    '$2a$10$ODxXhwa0xf600yL8MggA1Ou3YwQSCBIaqX6f8ZfSHgYvBSQNN5B4W',1,1,'402883346fa22571016fa225b4650000'
    ,NOW()

    FROM DUAL

    WHERE NOT EXISTS (SELECT * FROM `user`

    WHERE user.`userid` = '402883346fa22571016fa225b4650000');