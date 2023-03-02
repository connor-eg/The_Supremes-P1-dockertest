import { configureStore } from "@reduxjs/toolkit";
import SessionTokenSlice from "../../slices/SessionTokenSlice";
import UserIdSlice from "../../slices/UserIdSlice";

export const store = configureStore({
    reducer: {
        session: SessionTokenSlice,
        userId: UserIdSlice
    }
});

// React Redux doesn't fully support TS, we need extra configuration to get intellisense back
export type AppDispatch = typeof store.dispatch;
export type RootState = ReturnType<typeof store.getState>;