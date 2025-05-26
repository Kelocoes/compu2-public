import { Box, Button, TextField, Typography } from "@mui/material";
import CustomAppBar from "../components/CustomAppBar";
import { useEffect, useState } from "react";
import SockJS from 'sockjs-client';
import { over } from 'stompjs';
import { useRef } from "react";

export default function ChatPage() {
    const [userLogged, setUserLogged] = useState("");
    const [form, setForm] = useState({
        sender: "",
        receiver: "",
        message: ""
    });
    const [messages, setMessages] = useState([]);
    const stompClient = useRef(null);
    const [isConnected, setIsConnected] = useState(false);

    const handleChange = (e) => {
        setForm({
            ...form,
            [e.target.name]: e.target.value
        });
    };

    const handleSendMessage = (e) => {
        e.preventDefault();
        setMessages([...messages, { id: messages.length + 1, ...form }]);
        setForm({
            sender: "",
            receiver: "",
            message: ""
        });

        if (stompClient.current) {
            stompClient.current.send(
                `/app/chat/sendMessage`,
                {},
                JSON.stringify({
                    sender: form.sender,
                    receiver: form.receiver,
                    message: form.message
                })
            );
        }
    };

    const connect = () => {
        const socket = new SockJS('http://localhost:8080/ws');
        stompClient.current = over(socket);

        stompClient.current.connect({}, () => {
            console.log('Conectado como:', userLogged);
            setIsConnected(true);

            stompClient.current.subscribe(`/subscribeTo/${userLogged}`, (msg) => {
                const body = JSON.parse(msg.body);
                console.log('Recibí un mensajeee')
                setMessages(prev => [...prev, { id: prev.length + 1, ...body }]);
            });
        });
    };

    useEffect(() => {

        return () => {
            if (stompClient.current) {
                stompClient.current.disconnect();
                console.log('Desconectado');
            }
        }
    }, []);

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
                <TextField
                    label="Username"
                    name="username"
                    value={userLogged}
                    onChange={(e) => setUserLogged(e.target.value)}
                    sx={{ margin: '20px' }}
                />
                <Button variant="contained" color="primary" onClick={connect} sx={{ margin: '20px' }}>
                    Connect
                </Button>
                {isConnected ? (
                    <Typography variant="p" sx={{ margin: '5px' }}>
                        Connected as: {userLogged}
                    </Typography>
                )
                : (
                    <Typography variant="p" sx={{ margin: '5px' }}>
                        Not connected
                    </Typography>
                )}
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
                            flexDirection: message.sender === userLogged ? 'row-reverse' : 'row',
                            alignItems: 'center',
                            margin: '10px'
                        }}>
                            <Typography variant="p" sx={{
                                backgroundColor: message.sender === userLogged ? '#1976d2' : '#f50057',
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