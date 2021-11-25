# JAVA COVID-19 News APIs

Provide SQL CRUD service for other COVID-19 application, e.q., web crawler

# COVID-19 Information Website

<img src="docs/covid19-demo.png" height=200 alt>

Scan this QR code by mobile phone.

The News and video part is provide by this API.

**Notice:** This service only support mobile device and zh-tw language system.

# Build Development

1. build java app (.jar)

2. build image

	docker build -f docker/Dockerfile -t covid19-api-dev .

3. build container
```bash
$ docker-compose [COMMAND]
# start      Start services
# stop       Stop services
# up         Create and start containers
# down       Stop and remove containers, networks, images, and volumes

# RUN
$ docker-compose -f docker/docker-compose-dev.yml up -d
# STOP
$ docker-compose stop
# DELETE
$ docker-compose down
```


## Acknowledgements

Thanks to my buddies for building this project together
* UI/UX design, [Greens Chen](https://github.com/GreensChen)
* Frontend, [Jim Liu](https://github.com/Jim963)
