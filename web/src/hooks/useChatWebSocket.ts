import {Client} from '@stomp/stompjs';
import SockJS from 'sockjs-client';
import {useEffect, useRef, useState} from 'react';
import {type ChatRequestDTO} from '@/types';

interface MessageDTO{
    content: string;
    userId: number;
    chatId: number;
    timestamp: string;
}

export function useChatWebSocket(userId: number, onMessageReceived: (msg: MessageDTO) => void) {
    const [connected, setConnected] = useState(false);
    const clientRef = useRef<Client | null>(null);

    useEffect(() => {
        const client = new Client({
            webSocketFactory: () => new SockJS('http://localhost:8080/ws'),
            debug: (str) => console.log(str),
            onConnect: () => {
                setConnected(true);
                client.subscribe('/user/queue/messages', (message) => {
                    if (message.body) {
                        const msg: MessageDTO = JSON.parse(message.body);
                        onMessageReceived(msg);
                    }
                });
            },
            onStompError: (frame) => {
                console.error('Broker reported error:', frame.headers['message']);
            },
        });

        clientRef.current = client;
        client.activate();

        return () => {
            client.deactivate();
        };
    }, [userId, onMessageReceived]);

    const sendMessage = (chatRequestDTO: ChatRequestDTO) => {
        if (clientRef.current && connected) {
            clientRef.current.publish({
                destination: `/app/chat/chat.send`,
                body: JSON.stringify(chatRequestDTO),
            });
        }
    };

    return {connected, sendMessage};
}