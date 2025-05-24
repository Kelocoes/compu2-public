import { Box, Link, Typography } from "@mui/material";
import CustomAppBar from "../components/CustomAppBar";
import { useStore } from "@tanstack/react-store";
import { store } from "../store/Store";

export default function LandingPage() {
    const selectedAgent = useStore(store, (state) => state.selectedAgent);
    return (
        <Box>
            <CustomAppBar />
            <Box sx={{
                display: 'flex',
                flexDirection: 'column',
                justifyContent: 'center',
                alignItems: 'center',
            }}>
                <Typography variant="h1">Welcome to Valorant</Typography>
                <Typography variant="p">Discover the latest news and updates about your favorite game.</Typography>
            </Box>
            <Box sx={{
                display: 'flex',
                flexDirection: 'column',
                justifyContent: 'center',
                alignItems: 'center',
            }}>
                <Typography variant="h2">Selected Agent</Typography>
                {selectedAgent ? (
                    <Typography variant="p">{selectedAgent.displayName}</Typography>
                ) : (
                    <Typography variant="p">No agent selected</Typography>
                )}
            </Box>
        </Box>
    )
}