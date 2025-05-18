import { Box } from "@mui/material";
import { useEffect } from "react";
import { Outlet } from "react-router";

export default function AuthComponent () {

    useEffect(() => {
        console.log("AuthComponent mounted");
    }, [])

    return (
        <>
            <Outlet />
        </>
    )
}