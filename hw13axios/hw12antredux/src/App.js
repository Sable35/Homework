import { Button, Layout } from 'antd';
import React, {useEffect, useState} from 'react';
import { Content, Header } from 'antd/es/layout/layout';
import { Link, Route, Routes } from 'react-router-dom';
import { ProductsPage } from './pages/ProductsPage';
import { NotFoundPage } from './pages/NotFoundPage';
import { CartPage } from './pages/CartPage';
import AuthModal from './components/Authorize';
import {AdminPage} from "./pages/AdminPage";
import {ProfilePage} from "./pages/ProfilePage";
function App() {
    const [isAuth, setIsAuth] = useState(false);
    const [isAuthorized, setIsAuthorized] = useState(false);
    const openAuth = () => {
        setIsAuth(true);
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
                <Link to="/Admin">
                    <Button type="link" style={{ backgroundColor: 'white', color: 'green' }}>
                        Редактирование товаров
                    </Button>
                </Link>
                {isAuthorized ? (
                    <Link to="/User">
                        <Button type="link" style={{ backgroundColor: 'white', color: 'green' }}>
                            Профиль
                        </Button>
                    </Link>
                ) : (
                    <Button type="primary" onClick={openAuth} style={{ backgroundColor: 'white', color: 'green' }}>
                        Войти
                    </Button>
                )}
                {isAuth ? <AuthModal setIsAuthorized={setIsAuthorized} setIsAuth={setIsAuth} /> : ''}
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

