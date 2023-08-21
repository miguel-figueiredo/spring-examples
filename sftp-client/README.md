# Spring SFTP Client Example

Uses [SFTP Adapters](https://docs.spring.io/spring-integration/docs/current/reference/html/sftp.html) to connect, poll, retrieves, and removes files from an SFTP server.

The polling mechanism stores the processed files in a Redis metastore.

## Running

### Initialize all the development dependencies

```
make dev-dependencies
```

### Start the application

```
make dev
```

This command can be executed in different shells to test concurrency between different application instances.

### Upload files

```
make upload
```

The files are deleted from the SFTP server by the application after being processed.