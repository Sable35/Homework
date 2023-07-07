import {Button, Form, Input, Modal, notification} from 'antd';
import { useState } from 'react';
import {useDispatch, useSelector} from "react-redux";
import authService from "../services/AuthService";
import {login} from "../slices/AuthSlice";

const Login = ({setIsLogUp}) => {
    const [visible, setVisible] = useState(true);
    const dispatch = useDispatch()
    const [api, contextHolder] = notification.useNotification();
    const openNotification = () => {
        api.info({
            message: 'Notification',
            description:
                'This is the content of the notification. This is the content of the notification. This is the content of the notification.',
        });
    }

    const handleCancel = () => {
        setVisible(false);
        setIsLogUp(false);
        console.log('Отмена формы', visible);
    };

    const handleSubmit = (values) => {
        console.log('Форма отправлена:', values);
        authService.register(values)
        setVisible(false);
        setIsLogUp(false);
    };

    return (
        <>
            <Modal title="Регистрация" open={visible} onCancel={handleCancel} footer={null}>
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
                        name="email"
                        label="Email"
                        rules={[
                            {
                                required: true,
                                message: 'Введите вашу почту!',
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
                            Зарегистрироваться
                        </Button>
                    </Form.Item>
                </Form>
            </Modal>
        </>
    );
};

export default Login;