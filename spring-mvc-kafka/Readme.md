In app.prop you can specify the bootstrap properties. There doesn't seem to be an option  
to add zookeeper host. kafka.bootstrapAddress=10.16.14.18:9092

## **Producer Configuration properties**

### bootstrap.server
* A list of host/port pairs to use for establishing the initial connection to the Kafka cluster. The client will make
 use of all servers irrespective of which servers are specified here for bootstrapping—this list only impacts the initial 
 hosts used to discover the full set of servers. This list should be in the form host1:port1,host2:port2,.... **Since these 
 servers are just used for the initial connection to discover the full cluster membership (which may change dynamically), 
 this list need not contain the full set of servers** (you may want more than one, though, in case a server is down).

        Type: List  
        Default: ""  
        Valid Values: non-null String

### acks
* The number of acknowledgments the producer requires the leader to have received before considering a request
 complete. This controls the durability of records that are sent. The following settings are allowed:

    * **acks=0** If set to zero then the producer will not wait for any acknowledgment from the server at all. The record will
     be immediately added to the socket buffer and considered sent. No guarantee can be made that the server has received the 
     record in this case, and the retries configuration will not take effect (as the client won't generally know of any failures). 
     The offset given back for each record will always be set to -1.
    * **acks=1** This will mean the leader will write the record to its local log but will respond without awaiting full
     acknowledgement from all followers. In this case should the leader fail immediately after acknowledging the record 
     but before the followers have replicated it then the record will be lost.
    * **acks=all** This means the leader will wait for the full set of in-sync replicas to acknowledge the record. This
     guarantees that the record will not be lost as long as at least one in-sync replica remains alive. This is the
      strongest available guarantee. This is equivalent to the acks=-1 setting.  
  
            Type: String  
            Default: 1  
            Valid Values:	[all, -1, 0, 1]  
          
### retries  
* Setting a value greater than zero will cause the client to resend any record whose send fails with a potentially
 transient error. Note that this retry is no different than if the client resent the record upon receiving the error. Allowing retries without setting max.in.flight.requests.per.connection to 1 will potentially change the ordering of records because if two batches are sent to a single partition, and the first fails and is retried but the second succeeds, then the records in the second batch may appear first. Note additionally that produce requests will be failed before the number of retries has been exhausted if the timeout configured by delivery.timeout.ms expires first before successful acknowledgement. Users should generally prefer to leave this config unset and instead use delivery.timeout.ms to control retry behavior.

        Type:	int  
        Default:	2147483647  
        Valid Values:	[0,...,2147483647]  
