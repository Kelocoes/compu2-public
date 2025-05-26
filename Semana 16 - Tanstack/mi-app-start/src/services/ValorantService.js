import { axiosInstance } from "./axios";

const getAgents = async () => {
    try {
        const response = await axiosInstance('/v1/agents');
        return response.data.data;
    } catch (error) {
        console.error('Error fetching agents:', error);
        throw error;
    }
}

export { getAgents };