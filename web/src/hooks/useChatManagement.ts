import { useEffect, useState } from 'react';
import type { Chat, User } from '@/types';
import UserService from '@/services/UserService';

export default function useChatManagement() {
    const [selectedChat, setSelectedChat] = useState<number | null>(null);
    const [chats, setChats] = useState<Chat[]>([]);
    const [users, setUsers] = useState<User[]>([]);

    const fetchChats = async (token: string) => {
        try {
            const chats = await UserService.getAllUserChats(token);
            const fetchedChats = chats.map((chat: Chat) => ({id: chat.id, user1Id: chat.user1Id, user2Id: chat.user2Id, messages: chat.messages}));
            setChats(fetchedChats);
        } catch (error) {
            console.error('Error fetching chats:', error);
        }
    };

    const fetchUsers = async (token: string) => {
        try {
            const users = await UserService.getAllUsers(token);
            setUsers(users);
        } catch (error) {
            console.error('Error fetching users:', error);
            setUsers([]);
        }
    };

    useEffect(() => {
        const token = localStorage.getItem("token");
        if (!token) {
            console.error('No token found in localStorage');
            return;
        }
        fetchChats(token);
        fetchUsers(token);
    }, [])

    return {
        chats,
        setChats,
        users,
        setUsers,
        selectedChat,
        setSelectedChat,
        fetchChats,
        fetchUsers,
    };

}