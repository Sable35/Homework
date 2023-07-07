import React from "react";
import EditProducts from "../components/EditProducts";

export const AdminPage = () => {
    return (
        <>
            <h2 style={{color: "#228B22", fontSize:'30px'}}>Изменение товаров</h2>
            <EditProducts/>
        </>
    )
}