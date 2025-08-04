import type { Chat, User } from "@/types";
import ChatService from "@/services/ChatService";

export default function Talks(
      {
            chats,
            users,
            setSelectedChat,
            fetchChats,
      }: 
      {
            chats: Chat[],
            setChats: (chats: Chat[]) => void;
            users: User[];
            selectedChat: number | null;
            setSelectedChat: (chat: number | null) => void;
            fetchChats: (token: string) => Promise<void>;
      }
) {
      const token = localStorage.getItem("token");

      return (
            <div className="bg-white h-150 w-70 shadow-md rounded-lg flex flex-col items-start justify-start overflow-y-auto">
                  {users.map((user, index) => (
                        <div 
                              className="flex flex-col items-start w-full p-2 hover:bg-gray-100 cursor-pointer ring ring-gray-100 text-sky-700" key={index} onClick={async () => {
                                    if (!token) return;
                                    const response = await ChatService.enterChat(token, user.id);
                                    console.log(response, chats);
                                    setSelectedChat(response);
                                    await fetchChats(token);
                              }}
                        >
                              <h1>{user.username}</h1>
                        </div>
                  )
                  )}
            </div>
      );
}