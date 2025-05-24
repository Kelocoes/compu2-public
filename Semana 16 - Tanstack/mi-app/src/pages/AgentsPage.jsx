import { useEffect, useState } from "react";
import { getAgents } from "../services/ValorantService";
import { Box, Button, Card, CardContent, CardMedia, Chip, Modal, Paper, Typography } from "@mui/material";
import Agent from "../components/Agent";
import CustomAppBar from "../components/CustomAppBar";
import { useParams } from "react-router";
import { useQuery } from "@tanstack/react-query";
import { setSelectedAgent } from "../store/Actions";
import { useStore } from "@tanstack/react-store";
import { store } from "../store/Store";

export default function AgentsPage () {
    const { agentId } = useParams();
    const selectedAgent = useStore(store, (state) => state.selectedAgent);
    const [open, setOpen] = useState(false);
    const queryAgents = useQuery({
        queryKey: ['agents'],
        queryFn: getAgents
    })

    if (queryAgents.error) {
        alert('Error fetching agents');
    }

    useEffect(() => {
        if (agentId && queryAgents.data.length > 0) {
            const agent = queryAgents.data.find((agent) => agent.uuid === agentId);
            if (agent) {
                setSelectedAgent(agent);
            }
        }
    }, [agentId, queryAgents]);

    const handleClickAgent = (agent) => {
        setSelectedAgent(agent);
        handleOpenModal();
    }

    const handleOpenModal = () => {
        setOpen(true);
    }

    const handleCloseModal = () => {
        setOpen(false);
    }

    return (
        <Box>
            <CustomAppBar />
            <Box sx={{ display: 'flex', justifyContent: 'center', alignItems: 'center', flexDirection: 'column'}}>
                <Typography variant="h1">Agents Page</Typography>
                <Typography variant="p">This is the agents page.</Typography>
                <Button variant="contained" onClick={() => queryAgents.refetch()} sx={{ margin: '20px' }}>
                    Refresh Agents
                </Button>
                <Box sx={{
                    display: 'flex',
                    flexDirection: 'row',
                    flexWrap: 'wrap',
                    justifyContent: 'space-around',
                    alignItems: 'center',
                    padding: '50px',
                }}>
                    {queryAgents.isPending && <Typography variant="h4">Loading...</Typography>}
                    {queryAgents.isSuccess && (
                        queryAgents.data.map((agent) => {
                            return <Agent key={agent.uuid} agent={agent} handleClickAgent={handleClickAgent} />
                        })
                    )}
                </Box>
            </Box>
            {selectedAgent && (
                <Modal
                    open={open}
                    onClose={handleCloseModal}
                    aria-labelledby="modal-modal-title"
                    aria-describedby="modal-modal-description"
                >
                    <Card sx={{
                        position: 'absolute',
                        top: '50%',
                        left: '50%',
                        transform: 'translate(-50%, -50%)',
                        width: '50%',
                        display: 'flex',
                        flexDirection: 'row',
                        alignItems: 'center'
                    }}>
                        <CardMedia
                            component="img"
                            image={selectedAgent.fullPortrait}
                            alt={selectedAgent.displayName}
                            sx={{
                                width: '300px',
                                height: '300px',
                            }}
                        />
                        <CardContent>
                            <Typography variant="h4">{selectedAgent.displayName}</Typography>
                            <Typography variant="subtitle1" sx={{ marginBottom: '10px' }}>
                                Role: {selectedAgent.role.displayName}
                            </Typography>
                            <Typography variant="body1" sx={{ marginBottom: '10px' }}>
                                {selectedAgent.description}
                            </Typography>
                            <Typography variant="h6">Abilities:</Typography>
                            <Box sx={{ flexDirection: 'row', flexWrap: 'wrap', marginTop: '10px', display: 'flex' }}>
                                {selectedAgent.abilities.map((ability) => (
                                    <Chip key={ability.displayName} label={ability.displayName} variant="outlined" sx={{ margin: '5px' }} />
                                ))}
                            </Box>
                        </CardContent>
                    </Card>
                </Modal>
            )}
        </Box>
    )
}