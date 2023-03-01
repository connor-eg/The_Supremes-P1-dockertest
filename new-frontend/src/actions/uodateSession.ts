const updateSession = (token: string) => {
    return {
        type: "SET",
        payload: {
            newToken: token
        }
    }
}

export default updateSession;