import {Button, Form, Input, Modal, notification} from 'antd';
import { useState } from 'react';
import {useDispatch, useSelector} from "react-redux";
import authService from "../services/AuthService";
import {login} from "../slices/AuthSlice";

const Login = ({setIsAuth}) => {
    const [visible, setVisible] = useState(true);
    const dispatch = useDispatch()
    const [api, contextHolder] = notification.useNotification();
    const openNotification = () => {
        api.info({
            message: 'Ошибка входа',
            description:
                'Вероятно, вы ввели неверный пароль или имя пользователя.',
        });
    }

    const handleCancel = () => {
        setVisible(false);
        setIsAuth(false);
        console.log('Отмена формы', visible);
    };

    const handleSubmit = (values) => {
        console.log('Форма отправлена:', values);
        authService.login(values).then((user) => {
            console.log(user)
            dispatch(login(user))
            setVisible(false)
        },
            (error) => {
            openNotification()
            })
    };

    return (
        <>
            <Modal title="Вход в аккаунт" open={visible} onCancel={handleCancel} footer={null}>
                <Form onFinish={handleSubmit}>
                    <Form.Item
                        name="username"
                        label="Username"
                        rules={[
                            {
                                required: true,
                                message: 'Введите ваше имя пользователя!',
                            },
                        ]}
                    >
                        <Input />
                    </Form.Item>
                    <Form.Item
                        name="password"
                        label="Пароль"
                        rules={[
                            {
                                required: true,
                                message: 'Введите ваш пароль!',
                            },
                        ]}
                    >
                        <Input.Password />
                    </Form.Item>
                    <Form.Item>
                        <Button type="primary" htmlType="submit" style={{ backgroundColor: 'green' }}>
                            Войти
                        </Button>
                    </Form.Item>
                </Form>
            </Modal>
        </>
    );
};

export default Login;