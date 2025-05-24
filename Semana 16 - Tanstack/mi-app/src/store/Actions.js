import { store } from "./Store";

export const setSelectedAgent = (agent) => {
    store.setState((state) => ({
        ...state,
        selectedAgent: agent
    }));
}

export const clearSelectedAgent = () => {
    store.setState((state) => ({
        ...state,
        selectedAgent: null
    }));
}