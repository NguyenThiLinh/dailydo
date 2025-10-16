// app/(auth)/register/page.jsx
"use client";

import { Form, Input, Button, Alert } from "antd";
import { useDispatch, useSelector } from "react-redux";
import { register } from "../../../features/auth/authSlice";
import { useRouter } from "next/navigation";
import { useEffect } from "react";

export default function RegisterPage() {
    const dispatch = useDispatch();
    const router = useRouter();
    const { user, loading, error } = useSelector((state) => state.auth);

    useEffect(() => { if (user) router.push("/dashboard"); }, [user]);

    const onFinish = (values) => dispatch(register(values));

    return (
        <div className="flex justify-center items-center min-h-screen bg-gradient-to-r from-blue-100 to-blue-200">
            <div className="p-8 max-w-md w-full bg-white rounded-2xl shadow-xl">
                <h1 className="text-3xl font-bold text-center mb-6 text-gray-700">Register</h1>
                <Form layout="vertical" onFinish={onFinish}>
                    <Form.Item label="First Name" name="firstName" rules={[{ required: true }]}>
                        <Input className="rounded-lg" />
                    </Form.Item>
                    <Form.Item label="Last Name" name="lastName" rules={[{ required: true }]}>
                        <Input className="rounded-lg" />
                    </Form.Item>
                    <Form.Item label="Email" name="email" rules={[{ required: true }, { type: "email" }]}>
                        <Input className="rounded-lg" />
                    </Form.Item>
                    <Form.Item label="Password" name="password" rules={[{ required: true }]}>
                        <Input.Password className="rounded-lg" />
                    </Form.Item>
                    <Button
                        type="primary"
                        htmlType="submit"
                        loading={loading}
                        className="w-full bg-blue-500 hover:bg-blue-600 text-white font-semibold rounded-lg"
                    >
                        Register
                    </Button>
                </Form>
                <p className="mt-4 text-center text-gray-600">
                    Đã có tài khoản? <a href="/login" className="text-blue-500 font-medium hover:underline">Login</a>
                </p>
            </div>
        </div>

    );
}
