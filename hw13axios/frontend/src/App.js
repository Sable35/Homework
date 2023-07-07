import { Button, Layout } from 'antd';
import React, {useEffect, useState} from 'react';
import { Content, Header } from 'antd/es/layout/layout';
import { Link, Route, Routes } from 'react-router-dom';
import { ProductsPage } from './pages/ProductsPage';
import { NotFoundPage } from './pages/NotFoundPage';
import { CartPage } from './pages/CartPage';
import Login from './components/Login';
import {AdminPage} from "./pages/AdminPage";
import {ProfilePage} from "./pages/ProfilePage";
import {useSelector} from "react-redux";
import Logup from "./components/Logup";
function App() {
    const [isAuth, setIsAuth] = useState(false);
    const [isLogUp, setIsLogUp] = useState(false);
    const {user: currentUser} = useSelector((state) => state.auth);
    const openAuth = () => {
        setIsAuth(true);
    };
    const openLogUP = () => {
        setIsLogUp(true);
    };
    return (
        <Layout className="layout">
            <Header
                style={{
                    display: 'flex',
                    alignItems: 'center',
                    justifyContent: 'space-between',
                    backgroundColor: '#228B22',
                }}
            >
                <h1 style={{ color: 'white', fontSize: '30px' }}>Зеленочка</h1>
                <Link to="/">
                    <Button type="link" style={{ backgroundColor: 'white', color: 'green' }}>
                        Продукты
                    </Button>
                </Link>
                <Link to="/Cart">
                    <Button type="link" style={{ backgroundColor: 'white', color: 'green' }}>
                        Корзина
                    </Button>
                </Link>
                { currentUser ? (currentUser.roles.includes("ROLE_ADMIN") ? (<Link to="/Admin">
                    <Button type="link" style={{ backgroundColor: 'white', color: 'green' }}>
                        Редактирование товаров
                    </Button>
                </Link>) : null) : null}
                {currentUser ? (
                    <Link to="/User">
                        <Button type="link" style={{ backgroundColor: 'white', color: 'green' }}>
                            Профиль
                        </Button>
                    </Link>
                ) : (
                    <>
                        <Button type="primary" onClick={openLogUP} style={{ backgroundColor: 'white', color: 'green' }}>
                            Регистрация
                        </Button>
                    <Button type="primary" onClick={openAuth} style={{ backgroundColor: 'white', color: 'green' }}>
                        Войти
                    </Button>
                    </>
                )}
                {isAuth ? <Login setIsAuth={setIsAuth} /> : null}
                {isLogUp ? <Logup setIsLogUp={setIsLogUp} /> : null}
            </Header>
            <Content style={{ padding: '20px 20px', backgroundColor: 'rgba(220,253,220,0.62)'}}>
                <Routes>
                    <Route index element={<ProductsPage />} />
                    <Route path="/Cart" element={<CartPage />} />
                    <Route path="/Admin" element={<AdminPage />} />
                    <Route path="/User" element={<ProfilePage />} />
                    <Route path="*" element={<NotFoundPage />} />
                </Routes>
            </Content>
        </Layout>
    );
}

export default App;

