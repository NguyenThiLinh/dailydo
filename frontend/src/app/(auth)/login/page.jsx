// app/(auth)/login/page.jsx
"use client";

import { Form, Input, Button, Alert } from "antd";
import { useDispatch, useSelector } from "react-redux";
import { login } from "../../../features/auth/authSlice";
import { useRouter } from "next/navigation";
import { useEffect } from "react";

export default function LoginPage() {
    const dispatch = useDispatch();
    const router = useRouter();
    const { user, loading, error } = useSelector((state) => state.auth);

    useEffect(() => { if (user) router.push("/dashboard"); }, [user]);

    const onFinish = (values) => dispatch(login(values));

    return (
        <div className="p-6 max-w-md mx-auto bg-white rounded-xl shadow-lg">
            <h1 className="text-2xl font-bold text-center mb-6 text-gray-700 ">Login</h1>
            {error && <Alert message={error} type="error" className="mb-4" />}
            <Form layout="vertical" onFinish={onFinish}>
                <Form.Item label="Email" name="email" rules={[{ required: true }, { type: "email" }]}>
                    <Input />
                </Form.Item>
                <Form.Item label="Password" name="password" rules={[{ required: true }]}>
                    <Input.Password />
                </Form.Item>
                <Button type="primary" htmlType="submit" loading={loading} className="w-full">Login</Button>
            </Form>
            <p className="mt-4 text-center text-gray-600">
                Chưa có tài khoản? <a href="/register" className="text-blue-500">Register</a>
            </p>
        </div>
    );
}
