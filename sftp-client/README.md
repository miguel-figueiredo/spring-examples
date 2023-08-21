# Spring SFTP Client Example

Uses [SFTP Adapters](https://docs.spring.io/spring-integration/docs/current/reference/html/sftp.html) to connect, poll and retrieve data from SFTP.

The polling mechanism stores the processed files in a Redis metastore.

## Running

```
make dev-dependencies
make dev
```

Files can be "uploaded" to the SFTP server, simply by copying them to the `upload` folder which is created by the `make dev-dependencies` in the root of the project.

After a file is uploaded to the SFTP server the application detects it and shows its contents in the console.