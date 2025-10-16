import axiosClient from "../../api/axiosClient";

const authApi = {
    login: (data) => axiosClient.post("/auth/login", data),
    register: (data) => axiosClient.post("/auth/register", data),
};

export default authApi;