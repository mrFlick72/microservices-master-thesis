# microservices-master-thesis


Account-Service Cassandra keyspace configuration:

    CREATE KEYSPACE accountEventKeyspace WITH replication = {'class':'SimpleStrategy', 'replication_factor':1};
    CREATE KEYSPACE productCatalogEventKeyspace WITH replication = {'class':'SimpleStrategy', 'replication_factor':1};
    CREATE KEYSPACE purchaseOrderEventKeyspace WITH replication = {'class':'SimpleStrategy', 'replication_factor':1};
