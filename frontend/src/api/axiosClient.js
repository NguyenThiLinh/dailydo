import axios from "axios";

const axiosClient = axios.create({
    baseURL: "http://localhost:9090/api",
    headers: { "Content-Type": "application/json" },
});

export const getToken = (req) => {
    if (typeof window !== "undefined") return localStorage.getItem("token");
    if (req) return req.cookies?.token || null;
    return null;
};

// Request interceptor
axiosClient.interceptors.request.use((config) => {
    const token = typeof window !== "undefined" ? localStorage.getItem("token") : null;
    if (token) config.headers.Authorization = `Bearer ${token}`;
    return config;
});

// Response interceptor
axiosClient.interceptors.response.use(
    (response) => response,
    (error) => {
        if (error.response?.status === 401 && typeof window !== "undefined") {
            localStorage.removeItem("token");
            window.location.href = "/login";
        }
        return Promise.reject(error);
    }
);

export default axiosClient;
