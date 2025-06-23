export default function Message(
      {
            text,
            username,
            date,
            
      }: 
      {
            text: string;
            username: string;
            date?: Date;
      }
) {
      return (
            <div className='flex flex-col items-center justify-center h-screen bg-gray-100'>
                  {text} {username} {date ? date.toLocaleString() : new Date().toLocaleString()}
            </div>
      );
}