import { createSlice, createAsyncThunk } from "@reduxjs/toolkit";
import authApi from "./authApi";

export const login = createAsyncThunk("auth/login", async (data, { rejectWithValue }) => {
    try {
        const res = await authApi.login(data);
        return res.data;
    } catch (err) {
        return rejectWithValue(err.response?.data || { message: "Login failed" });
    }
});

export const register = createAsyncThunk("auth/register", async (data, { rejectWithValue }) => {
    try {
        const res = await authApi.register(data);
        return res.data;
    } catch (err) {
        return rejectWithValue(err.response?.data || { message: "Register failed" });
    }
});

const authSlice = createSlice({
    name: "auth",
    initialState: { user: null, token: null, loading: false, error: null },
    reducers: {
        logout: (state) => {
            state.user = null;
            state.token = null;
            localStorage.removeItem("token");
        },
    },
    extraReducers: (builder) => {
        builder
            // Login
            .addCase(login.pending, (state) => { state.loading = true; state.error = null; })
            .addCase(login.fulfilled, (state, action) => {
                state.loading = false;
                state.user = action.payload.data;
                state.token = action.payload.token;
                localStorage.setItem("token", action.payload.token);
            })
            .addCase(login.rejected, (state, action) => { state.loading = false; state.error = action.payload.message; })
            // Register
            .addCase(register.pending, (state) => { state.loading = true; state.error = null; })
            .addCase(register.fulfilled, (state, action) => {
                state.loading = false;
                state.user = action.payload.data;
                state.token = action.payload.token;
                localStorage.setItem("token", action.payload.token);
            })
            .addCase(register.rejected, (state, action) => { state.loading = false; state.error = action.payload.message; });
    },
});

export const { logout } = authSlice.actions;
export default authSlice.reducer;
