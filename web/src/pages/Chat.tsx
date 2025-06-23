//import Message from '../components/chat/Message';
import Interface from '../components/chat/Interface';
import Talks from '../components/chat/Talks';
import type { TalkProps } from '@/types';
import { useState } from 'react';

export default function Chat() {
      const [selectedTalk, setSelectedTalk] = useState<TalkProps | null>(null);
      const talks: TalkProps[] = [
            {
                  name: 'Alice',
                  lastMessage: 'Hello, how are you?',
                  lastMessageDate: new Date(),
            },
            {
                  name: 'Bob',
                  lastMessage: 'Are you coming to the party?',
                  lastMessageDate: new Date(),
            },
      ];
      return (
            <div className="flex flex-row items-center justify-center h-screen bg-gray-100">
                  <div className="">
                        <Talks talks={talks} setSelectedTalk={setSelectedTalk}/>
                  </div>
                  <div className="">
                        <Interface name={ selectedTalk?.name } />
                  </div>
                  
        </div>
      );
}