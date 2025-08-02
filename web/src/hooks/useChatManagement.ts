import { useState } from 'react';
import type { Chat, User } from '@/types';
import UserService from '@/services/UserService';

export default function useChatManagement() {
    const [selectedChat, setSelectedChat] = useState<Number | null>(null);
    const [chats, setChats] = useState<Chat[]>([]);
    const [users, setUsers] = useState<User[]>([]);

    const fetchChats = async (token: string) => {
        try {
            const chats = await UserService.getAllUserChats(token);
            const fetchedChats = chats.map((chat: Chat) => ({id: chat.id, user1: chat.user1, user2: chat.user2, messages: chat.messages}));
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

    fetchChats(localStorage.getItem('token') || '');
    fetchUsers(localStorage.getItem('token') || '');

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