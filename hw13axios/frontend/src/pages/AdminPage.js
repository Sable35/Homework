import React from "react";
import EditProducts from "../components/EditProducts";
import {Link} from "react-router-dom";
import {Button} from "antd";
import {useSelector} from "react-redux";

export const AdminPage = () => {
    const {user: currentUser} = useSelector((state) => state.auth);
    return (
        <>
            { currentUser ? (currentUser.roles.includes("ROLE_ADMIN") ? (<><h2 style={{color: "#228B22", fontSize:'30px'}}>Изменение товаров</h2>
                <EditProducts/></>) : <h3>Вы не авторизованы, чтобы видеть эту страницу</h3>) : null}

        </>
    )
}