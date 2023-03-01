import { useDispatch } from "react-redux"
import { useSelector } from "react-redux/es/exports";
import { TypedUseSelectorHook } from "react-redux/es/types";
import { AppDispatch, RootState } from "./store"

//This configures our own typescript version of the hooks
//Use the hooks from React Redux and add strict typing
export const useAppDispatch: () => AppDispatch = useDispatch;
export const useAppSelector: TypedUseSelectorHook<RootState> = useSelector;