import {createSlice, createAsyncThunk} from "@reduxjs/toolkit";
import authApi from "../../api/authApi";

export const login = createAsyncThunk("auth/login", async (data, {rejectWithValue}) => {
    try {
        const response = await authApi.login(data);
        const resData = response.data;
        if (resData.success) {
            return resData.data;
        } else {
            return rejectWithValue(resData.message);
        }
    } catch (error) {
        return rejectWithValue(error.response?.data?.message || "Login failed");
    }
})

export const register = createAsyncThunk("auth/register",
    async (data, {rejectWithValue}) => {
        try {
            const response = await authApi.register(data);
            const resData = response.data;
            if (resData.success) {
                return resData.data;
            } else {
                return rejectWithValue(resData.message);
            }

        } catch (error) {
            return rejectWithValue(error.response?.data?.message || "Register failed");
        }
    }
);

const initialState = {
    user : null,
    accessToken: localStorage.getItem("accessToken") || null,
    refreshToken: localStorage.getItem("refreshToken") || null,
    isAuthenticated: !!localStorage.getItem("accessToken"),
    loading: false,
    error : null,
}

const authSlice = createSlice({
    name: "auth",
    initialState: initialState,
    reducers: {
        logout: (state) => {
            state.user = null;
            state.accessToken = null;
            state.refreshToken = null;
            state.isAuthenticated = false;
            state.error = null;
            localStorage.removeItem("accessToken");
            localStorage.removeItem("refreshToken");
        }
    },
    extraReducers: (builder) => {
        builder
            // --- LOGIN ---
            .addCase(login.pending, (state) => {
                state.loading = true;
                state.error = null;
            })
            .addCase(login.fulfilled, (state, action) => {
                state.loading = false;
                state.accessToken = action.payload.accessToken;
                state.refreshToken = action.payload.refreshToken;
                state.user = action.payload.user;
                state.isAuthenticated = true;

                localStorage.setItem("accessToken", action.payload.accessToken);
                localStorage.setItem("refreshToken", action.payload.refreshToken);
            })
            .addCase(login.rejected, (state, action) => {
                state.loading = false;
                state.error = action.payload;
            })
            // --- REGISTER ---
            .addCase(register.pending, (state) => {
                state.loading = true;
                state.error = null;
            })
            .addCase(register.fulfilled, (state, action) => {
                state.loading = false;
                state.user = action.payload;
            })
            .addCase(register.rejected, (state, action) => {
                state.loading = false;
                state.error = action.payload;
            })

    },
});

export const { logout } = authSlice.actions;
export  default authSlice.reducer;