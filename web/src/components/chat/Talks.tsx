import type { TalkProps } from "@/types";
import { BiSearch } from "react-icons/bi";
import { Input } from "../ui/input";

export default function Talks(
      {
            talks,
            setSelectedTalk
      }: 
      {
            talks: TalkProps[]
            setSelectedTalk: (talk: TalkProps) => void;
      }
) {
      return (
            <div className="bg-white h-150 w-70 shadow-md rounded-lg flex flex-col items-start justify-start overflow-y-auto">
                  <div className='flex flex-row items-center justify-start w-full p-2 bg-sky-100'>
                        <BiSearch />
                        <Input className='border-none shadow-none' placeholder="Search"/>
                  </div>
                  {talks.map((talk, index) => (
                        <div className="flex flex-col items-start w-full p-2 hover:bg-gray-100 cursor-pointer ring ring-gray-100 text-sky-700" key={index} onClick={() => setSelectedTalk(talk)}>
                              <h1>{talk.name}</h1>
                              <div className='flex flex-row items-start'>
                                    <span className='mx-2 text-xs  text-gray-500'>{ talk.lastMessage }</span>
                                    <span className="text-xs text-gray-500 ml-auto">
                                          {new Date(talk.lastMessageDate).toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' })}
                                    </span>
                              </div>
                        </div>
                  )
                  )}
            </div>
      );
}