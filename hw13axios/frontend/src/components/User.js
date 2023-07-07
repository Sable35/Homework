import { Button, Col, Input, Row } from 'antd';
import { useSelector } from 'react-redux';
import React from 'react';
import { Card, Avatar } from 'antd';
import AuthService from "../services/AuthService";
import {Link} from "react-router-dom";

const User = () => {
    const {user: currentUser} = useSelector((state) => state.auth);
    const { Meta } = Card;

    return (
        <>
        {currentUser ? (<Card>
                <Meta
                    avatar={
                        <Avatar src="https://64.media.tumblr.com/ed8cde31597bdd00446a5997d709cbb1/5873019d7a463354-56/s500x750/7b0a010f79af94e67c5aba53c9c30399c60e41e7.jpg" />
                    }
                    title={currentUser.username}
                />
            <Link to="/">
                <Button onClick={() => AuthService.logout()}type="text" size="large" danger style={{margin:"30"}}>
                    Выйти из аккаунта
                </Button>
                </Link>
            </Card> ) : ( <h3>Вам нужно cume inside, чтобы посмотреть профиль</h3>)}
            </>
    );
};


export default User;