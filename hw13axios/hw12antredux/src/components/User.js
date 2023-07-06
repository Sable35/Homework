import { Button, Col, Input, Row } from 'antd';
import { useState } from 'react';
import { useSelector } from 'react-redux';
import React from 'react';
import { Card, Avatar } from 'antd';

const User = () => {
    const user = useSelector((state) => state.User);
    const { Meta } = Card;


    return (
            <Card>
            <Meta
            avatar={
            <Avatar src="https://static.wikia.nocookie.net/disco-elysium3537/images/f/f1/%D0%93%D0%B0%D1%80%D1%80%D1%8C%D0%B5_%D0%94%D1%8E%D0%B1%D1%83%D0%B0_%D1%81_%D0%B1%D0%BE%D1%80%D0%BE%D0%B4%D0%BE%D0%B9.png/revision/latest/scale-to-width-down/350?cb=20210914084449&path-prefix=ru" />
        }
            title={user.login}
            />
            </Card>
    );
};


export default User;