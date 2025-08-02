import axiosInstance from '@/utils/axiosInstance';

export interface ChatRequestDTO {
    message: string;
    sender: number;
    receiver: number;
    timestamp: string;
}

class ChatService {
    static async enterChat(chatRequestDTO: ChatRequestDTO) {
        try {
            const response = await axiosInstance.post("/api/chat/enter", chatRequestDTO);
            return response.data;
        } catch (error: any) {
            throw error.response ? error.response.data : error.message;
        }
    }

    static async getChatHistory(chatId: number) {
        try {
            const response = await axiosInstance.get(`/api/chat/history/${chatId}`);
            return response.data;
        } catch (error: any) {
            throw error.response ? error.response.data : error.message;
        }
    }
}

export default ChatService;