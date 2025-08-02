import { BiSearch } from "react-icons/bi";
import { Input } from "../ui/input";
import type { Chat, User } from "@/types";

export default function Talks(
      {
            chats,
            setChats,
            users,
            selectedChat,
            setSelectedChat,
            fetchChats,
      }: 
      {
            chats: Chat[],
            setChats: (chats: Chat[]) => void;
            users: User[];
            selectedChat: Number | null;
            setSelectedChat: (chat: Number | null) => void;
            fetchChats: (token: string) => Promise<void>;
      }
) {
      return (
            <div className="bg-white h-150 w-70 shadow-md rounded-lg flex flex-col items-start justify-start overflow-y-auto">
                  <div className='flex flex-row items-center justify-start w-full p-2 bg-sky-100'>
                        <BiSearch />
                        <Input className='border-none shadow-none' placeholder="Search"/>
                  </div>
                  {chats.map((chat, index) => (
                        <div className="flex flex-col items-start w-full p-2 hover:bg-gray-100 cursor-pointer ring ring-gray-100 text-sky-700" key={index} onClick={() => setSelectedChat(chat.id)}>
                              <h1>{users.find(user => user.id === chat.user2)?.username}</h1>
                              {/* <div className='flex flex-row items-start'>
                                    <span className='mx-2 text-xs  text-gray-500'>{ talk.lastMessage }</span>
                                    <span className="text-xs text-gray-500 ml-auto">
                                          {new Date(talk.lastMessageDate).toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' })}
                                    </span>
                              </div> */}
                        </div>
                  )
                  )}
            </div>
      );
}