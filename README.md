# testdo
This is use for my project test command generate.
Story:
I want to run maven command to test my Junit test case.The command is long and the cases is more than 20.
I also want to use tcpdump to capture the logs on the machine of my clusters, find the log file's new log
and put them in a new file.In order to review sometimes future.This job is done by every test case.That means,
If I run test case A. I have to get a log about the maven test output named A.log,  a tcpdump log named A.cap that on one node
of the cluster which the messges recieved. a traffic log named A.traffic which the node server'log about this new message.

So,I try to write all the test method name under a txt file.by reading this file I know all the test methods. Indeed,I need some others
information such as the cluster IP ,the router interface name and others. All of that work is just a String joint.Yeah! I'm a litter fool 
to do that work.

At now time,I think it's nouse for all of you!
