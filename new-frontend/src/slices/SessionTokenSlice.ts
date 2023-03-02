import { createSlice, PayloadAction } from "@reduxjs/toolkit";
import { RootState } from "../shared/Redux/store";
import { SessionTokenHolder } from "./SliceInterfaces";

const initialState: SessionTokenHolder = {
    token: ""
};

const sessionTokenSlice = createSlice({
    name:"sessionToken",
    initialState,
    reducers: {
        setSessionToken: (state, action: PayloadAction<SessionTokenHolder>) => {
            state.token = action.payload.token;
        },
        resetSessionToken: (state) => {
            state.token = ""
        }
    }
})

export const {setSessionToken, resetSessionToken} = sessionTokenSlice.actions;

export default sessionTokenSlice.reducer;

export const selectSessionToken = (state: RootState) => state.session;