// app/dashboard/page.jsx
"use client";

import { useEffect } from "react";
import { useSelector, useDispatch } from "react-redux";
import { useRouter } from "next/navigation";
import { logout } from "../../features/auth/authSlice";
import { Button } from "antd";

export default function Dashboard() {
    const router = useRouter();
    const dispatch = useDispatch();
    const { user } = useSelector((state) => state.auth);

    useEffect(() => {
        if (!user) router.push("/login");
    }, [user]);

    if (!user) return null;

    const handleLogout = () => { dispatch(logout()); router.push("/login"); };

    return (
        <div className="p-6">
            <h1 className="text-3xl font-bold mb-4">Welcome, {user.firstName}</h1>
            <Button type="primary" onClick={handleLogout}>Logout</Button>
        </div>
    );
}
