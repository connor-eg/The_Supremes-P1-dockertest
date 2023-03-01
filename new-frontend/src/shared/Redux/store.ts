import { configureStore } from "@reduxjs/toolkit";
import SessionToken from "../../reducers/SessionToken";

export const store = configureStore({
    reducer: {
        sessionReducer: SessionToken
    }
});

// React Redux doesn't fully support TS, we need extra configuration to get intellisense back
export type AppDispatch = typeof store.dispatch;
export type RootState = ReturnType<typeof store.getState>;