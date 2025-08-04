export interface ChatRequestDTO {
    message: string;
    sender: number;
    receiver: number;
    timestamp: string;
}

export interface User{
    id: number;
    username: string;
    email: string;
}

export interface Chat{
    id: number;
    user1Id: number;
    user2Id: number;
    messages: Message[];
}

export interface Message{
    content: string;
    sender: User;
    receiver: User;
    timestamp: string;
}