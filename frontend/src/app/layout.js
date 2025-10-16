"use client";

import { Provider } from "react-redux";
import { store, persistor } from "../store/store";
import { PersistGate } from "redux-persist/integration/react";
import './globals.css';

export default function RootLayout({ children }) {
    return (
        <html>
        <body>
        <Provider store={store}>
            <PersistGate loading={null} persistor={persistor}>
                {children}
            </PersistGate>
        </Provider>
        </body>
        </html>
    );
}
