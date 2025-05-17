import { Box, Button, TextField, Typography } from "@mui/material";
import CustomAppBar from "../components/CustomAppBar";
import { useState } from "react";

export default function ChatPage() {
    const [form, setForm] = useState({
        sender: "",
        receiver: "",
        message: ""
    });

    const handleChange = (e) => {
        setForm({
            ...form,
            [e.target.name]: e.target.value
        });
    };

    const handleSendMessage = (e) => {
        e.preventDefault();
        console.log(form);
        setMessages([...messages, { id: messages.length + 1, ...form }]);
        setForm({
            sender: "",
            receiver: "",
            message: ""
        });
    };

    const [messages, setMessages] = useState([
        { id: 1, sender: 'user', message: 'Hello!', receiver: 'agent' },
        { id: 2, sender: 'agent', message: 'Hi! How can I help you?', receiver: 'user' },
    ]);

    return (
        <>
            <CustomAppBar />
            <Box sx={{
                display: 'flex',
                flexDirection: 'column',
                alignItems: 'center',
                justifyContent: 'center',
                height: '100vh',
                width: '100%',
                padding: '20px',
            }}>
                <Typography variant="h2">Chat</Typography>
                <Typography variant="p">Chat with your friends and teammates.</Typography>
                <Box sx={{
                    display: 'flex',
                    flexDirection: 'column',
                    justifyContent: 'center',
                    height: '600px',
                    width: '100%',
                    overflowY: 'scroll',
                    padding: '20px'
                }}>
                    {messages.map((message) => (
                        <Box key={message.id} sx={{
                            display: 'flex',
                            flexDirection: message.sender === 'user' ? 'row-reverse' : 'row',
                            alignItems: 'center',
                            margin: '10px'
                        }}>
                            <Typography variant="p" sx={{
                                backgroundColor: message.sender === 'user' ? '#1976d2' : '#f50057',
                                color: 'white',
                                padding: '10px',
                                borderRadius: '10px'
                            }}>
                                {message.message}
                            </Typography>
                        </Box>
                    ))}
                </Box>
                <form onSubmit={handleSendMessage} style={{ width: '100%' }} autoComplete="off">
                    <Box sx={{
                        width: '100%',
                        gap: '10px',
                        display: 'flex',
                        flexDirection: 'row',
                        justifyContent: 'start',
                        alignItems: 'left',
                        margin: '20px'
                    }}>
                        <TextField
                            label="Sender"
                            name="sender"
                            value={form.sender}
                            onChange={handleChange}
                        />
                        <TextField
                            label="Receiver"
                            name="receiver"
                            value={form.receiver}
                            onChange={handleChange}
                        />
                        <TextField
                            label="Message"
                            name="message"
                            value={form.message}
                            onChange={handleChange}
                            fullWidth
                        />
                        <Button type="submit" variant="contained" color="primary">
                            Send
                        </Button>
                    </Box>
                </form>
            </Box>
        </>
    )
}