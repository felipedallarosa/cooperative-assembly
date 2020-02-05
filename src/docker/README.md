### Inicializar o ambiente local

```shell
# start
docker-compose up -d

# stop
docker-compose stop

# logs
docker-compose logs -f

# remove all
docker-compose down
```

#### Oracle
```
uri: jdbc:oracle:thin:@localhost:1521:XE
sysdba: sys / oracle
ASSEMBLY_OWNER / 123456
ASSEMBLY_RUN / 123456
```

#### RabbitMQ
```
RABBIT_ADDRESS: localhost:5672
RABBIT_USERNAME: guest
RABBIT_PASSWORD: guest
RABBIT_VIRTUAL_HOST: assembly
```

#### Redis
```
REDIS_HOST: redis
REDIS_PORT: 6379
REDIS_PASSWORD: 123456
```

#### Redis Commander
- http://localhost:16379

### Administração Web
#### RabbitMQ
- http://localhost:15672
- user: guest
- pass: guest

### Referências
* https://docs.docker.com/compose/reference/
* https://hub.docker.com/_/rabbitmq
* https://github.com/wnameless/docker-oracle-xe-11g
