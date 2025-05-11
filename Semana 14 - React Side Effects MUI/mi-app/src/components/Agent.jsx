import { Card, CardActionArea, CardContent, CardMedia, Typography } from "@mui/material";

export default function Agent({ agent, handleClickAgent }) {
    return (
        <Card sx={{
                display: 'flex',
                flexDirection: 'column',
                alignItems: 'center',
                justifyContent: 'center',
                margin: '20px',
                borderRadius: '20px'
            }}
            onClick={() => { handleClickAgent(agent)}}
        >
            <CardActionArea>
                <CardMedia
                    component="img"
                    image={agent.fullPortrait}
                    alt={agent.displayName}
                    sx={{
                        width: '300px',
                        height: '300px',
                    }}
                />
            </CardActionArea>
            <CardContent>
                <Typography variant="h2">
                    {agent.displayName}
                    </Typography>
            </CardContent>
        </Card>
    );
}