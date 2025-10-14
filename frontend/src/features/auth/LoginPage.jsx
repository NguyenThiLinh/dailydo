import {useState, useEffect} from "react";
import {useDispatch, useSelector} from "react-redux";
import {useNavigate} from "react-router-dom";
import {login} from "./authSlice";

const LoginPage = () => {
    const dispatch = useDispatch();
    const navigate = useNavigate();

    const {loading, error, isAuthenticated} = useSelector((state) => state.auth);
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");

    useEffect(() => {
        console.log("isAuthenticated =", isAuthenticated);
        if (isAuthenticated) {
            console.log("Redirecting to /dashboard");
            navigate("/dashboard");
        }
    }, [isAuthenticated, navigate]);

    const handleSubmit = (e) => {
        e.preventDefault();
        console.log("Dispatch login");
        dispatch(login({email, password}))
            .unwrap()
            .then(res => console.log("Login fulfilled", res))
            .catch(err => console.log("Login rejected", err));
    }

    return (
        <div className="container mt-5" style={{maxWidth: "400px"}}>
            <h2 className="mb-4 text-center">Login</h2>
            <form onSubmit={handleSubmit}>
                <div className="mb-3">
                    <label>Email</label>
                    <input type="email" className="form-control"
                           value={email} onChange={(e) => setEmail(e.target.value)}
                           required placeholder="Enter your email"/>
                </div>
                <div className="mb-3">
                    <label>Password</label>
                    <input type="password" className="form-control"
                           value={password} onChange={(e) => setPassword(e.target.value)}
                           required placeholder="Enter your password"/>
                </div>
                {error && <div className="alert alert-danger"> {error}</div>}
                <button type="submit" className="btn btn-primary w-100" disabled={loading}>
                    {loading ? "Logging in..." : "Login"}
                </button>
            </form>
            <div className="mt-3 text-center">
                <span>Don't have an account ?</span>
                <button
                className="btn btn-link p-0"
                onClick={() => navigate("/register")}>
                    Register
                </button>
            </div>
        </div>
    );

}

export default LoginPage;