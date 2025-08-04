//import Message from '../components/chat/Message';
import Interface from '../components/chat/Interface';
import Talks from '../components/chat/Talks';
import UserService from '@/services/UserService';
import useChatManagement from '@/hooks/useChatManagement';

export default function Chat() {

      const {
            selectedChat,
            setSelectedChat,
            chats,
            setChats,
            users,
            setUsers,
            fetchChats,
            fetchUsers,
      } = useChatManagement();

      return (
            <div className="flex flex-row items-center justify-center h-screen bg-gray-100">
                  <div className="">
                        <Talks chats={chats} setChats={setChats} users={users} selectedChat={selectedChat} setSelectedChat={setSelectedChat} fetchChats={fetchChats} />
                  </div>
                  <div className="">
                        <Interface chat={chats.find(chat => chat.id === selectedChat)} users={users} />
                  </div>
                  
        </div>
      );
}