 INSERT INTO  `user`

    (`userid`, `username`, `nickname`,`enabled`,`issuper`,`create_by`,`create_time`)

    SELECT '402883346fa22571016fa225b4650000',
    'root', 'root',1,1,'402883346fa22571016fa225b4650000'
    ,NOW()

    FROM DUAL

    WHERE NOT EXISTS (SELECT * FROM `user`

    WHERE user.`userid` = '402883346fa22571016fa225b4650000');