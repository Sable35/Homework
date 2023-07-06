import { Button, Form, Input, Modal } from 'antd';
import { useState } from 'react';
import {useDispatch, useSelector} from "react-redux";
import userService from "../services/UserService";

const AuthModal = ({ setIsAuthorized, setIsAuth}) => {
    const [visible, setVisible] = useState(true);
    const dispatch = useDispatch()
    const user = useSelector((state) => state.User.User);

    const handleCancel = () => {
        setVisible(false);
        setIsAuth(false);
        console.log('Отмена формы', visible);
    };

    const handleSubmit = (values) => {
        console.log('Форма отправлена:', values);
        userService.getUser(values.login, values.password, dispatch);
        console.log('пользователь:', user[0]);
        if (user[0] !== undefined) {
            setIsAuthorized(true);
            setVisible(false);
        }
    };

    return (
        <>
            <Modal title="Авторизация" open={visible} onCancel={handleCancel} footer={null}>
                <Form onFinish={handleSubmit}>
                    <Form.Item
                        name="login"
                        label="Login"
                        rules={[
                            {
                                required: true,
                                message: 'Введите ваш email!',
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
                        <Button type="primary" htmlType="submit">
                            Войти
                        </Button>
                    </Form.Item>
                </Form>
            </Modal>
        </>
    );
};

export default AuthModal;