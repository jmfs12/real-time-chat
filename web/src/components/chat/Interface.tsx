import { Textarea } from "@/components/ui/textarea"
import type { Chat, User } from "@/types";


export default function Interface({
      chat,
      users,
}: {
      chat?: Chat | undefined;
      users: User[];
}){

      return chat ? (
        <div className="bg-white h-150 w-250 shadow-md rounded-lg mx-5">
          <div className="h-15 w-full p-5">
            <div className="flex items-center justify-between mx-7">
              <p className="text-sky-800">{users.find(user => user.id === chat.user2Id)?.username}</p>
            </div>
          </div>
          <div className="w-full h-0.5 bg-gray-100 mx-7" /> {/* Divider line */}
          <div className="mt-115">
            <div className="bg-gray-100 w-full h-0.5 mx-7" />
            <Textarea
              placeholder="Type your Message here"
              className="border-none shadow-none resize-none mx-7 mt-2 w-242 focus-visible:border-ring focus-visible:ring focus-visible:ring-[1px] focus-visible:ring-gray-200 rounded-lg"
            />
          </div>
        </div>
      ) : (
        <div className="bg-white h-150 w-250 shadow-md rounded-lg mx-5 flex items-center justify-center">
            <span className='text-gray-500'>Start a chat</span>
        </div>
      );
          
}