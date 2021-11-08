server 跟client都是一个DatagramSocket
然后server端的socket是一个指定了port 跟address的socket
server:
mSocket = new DatagramSocket(mPort,mInetAddress);

client端是一个默认的DatagragmSocket
client:
mSocket = new DatagramSocket();

然后后面的流程就是一样的 定义一个DatagramPacket
DatagramPacket clientPacket = new DatagramPacket(clientMsgBytes,
clientMsgBytes.length, mServerAddress, mServerPort);
mSocket.send(clientPacket);