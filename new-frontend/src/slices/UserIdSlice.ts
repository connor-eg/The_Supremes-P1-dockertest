import { createSlice, PayloadAction } from "@reduxjs/toolkit";
import { RootState } from "../shared/Redux/store";
import { UserIdHolder } from "./SliceInterfaces";

const initialState: UserIdHolder = {
    userId: -1
};

const userIdSlice = createSlice({
    name:"userId",
    initialState,
    reducers: {
        setUserId: (state, action: PayloadAction<UserIdHolder>) => {
            state.userId = action.payload.userId;
        },
        resetUserId: (state) => {
            state.userId = -1;
        }
    }
})

export const {setUserId, resetUserId} = userIdSlice.actions;

export default userIdSlice.reducer;

export const SelectUserId = (state: RootState) => state.userId;