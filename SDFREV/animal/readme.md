## Trying to Send Objects over a Server/Client TCPIP connection

Animal Classes and Methods all establised
Managed to establish IO and use objectoutput stream
Reading CSVs into hashmap of objects
Accessing objects in hashmap
Client Server request and sending objects over TCP/IP

java.io.NotSerializableException
java.io.WriteAbortedException: writing aborted; java.io.NotSerializableException: rev.animal.server.Animal
at java.base/java.io.ObjectInputStream.readObject0(ObjectInputStream.java:1767)
at java.base/java.io.ObjectInputStream.readObject(ObjectInputStream.java:538)
at java.base/java.io.ObjectInputStream.readObject(ObjectInputStream.java:496)
at rev.animal.client.Client.main(Client.java:43)
Caused by: java.io.NotSerializableException: rev.animal.server.Animal
at java.base/java.io.ObjectOutputStream.writeObject0(ObjectOutputStream.java:1198)
at java.base/java.io.ObjectOutputStream.writeObject(ObjectOutputStream.java:358)
at rev.animal.server.NetworkIO.writeOOS(NetworkIO.java:26)
at rev.animal.server.Server.<init>(Server.java:36)
at rev.animal.server.Main.main(Main.java:17)
