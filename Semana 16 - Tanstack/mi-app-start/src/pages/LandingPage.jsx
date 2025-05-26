import { Box, Link, Typography } from "@mui/material";
import CustomAppBar from "../components/CustomAppBar";

export default function LandingPage() {
    return (
        <Box>
            <CustomAppBar />
            <Box sx={{
                display: 'flex',
                flexDirection: 'column',
                justifyContent: 'center',
                alignItems: 'center',
                height: '100vh'
            }}>
                <Typography variant="h1">Welcome to Valorant</Typography>
                <Typography variant="p">Discover the latest news and updates about your favorite game.</Typography>
            </Box>
        </Box>
    )
}