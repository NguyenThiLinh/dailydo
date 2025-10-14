import axios from "axios";

const baseURL = "http://localhost:9090/api";
const axiosClient = axios.create({
    baseURL,
    headers : {
        "Content-type" : "application/json",
    },
});

axiosClient.interceptors.request.use((config) => {
    const token = localStorage.getItem("token");
    if (token) {
        config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
});


export default axiosClient;