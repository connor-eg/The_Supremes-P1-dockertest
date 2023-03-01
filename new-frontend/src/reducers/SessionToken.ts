const SessionToken= (state="", action: any) => {
    switch(action.type){
        case "SET":
            return action.payload.newToken;
        case "RESET":
            return "";
        default:
            return state;
    }
}

export default SessionToken;