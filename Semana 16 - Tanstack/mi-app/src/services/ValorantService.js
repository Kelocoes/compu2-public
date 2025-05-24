
const getAgents = async () => {
    try {
        await new Promise((resolve) => setTimeout(resolve, 2000));
        const response = await fetch('https://valorant-api.com/v1/agents');
        const data = await response.json();
        return data.data;
    } catch (error) {
        console.error('Error fetching agents:', error);
        throw error;
    }
}

export { getAgents };