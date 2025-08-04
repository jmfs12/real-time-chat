import axiosInstance from '@/utils/axiosInstance';

class ChatService {
    static async enterChat(token: string, id: number) {
        try {
            const response = await axiosInstance.post(
                "/api/chat/enter",
                { userId: id }, // body
                {
                    headers: { Authorization: `Bearer ${token}` } // config
                }
            );
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