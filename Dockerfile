FROM amazoncorretto:11

RUN yum install -y shadow-utils

RUN mkdir -p /realtime-server/scripts
WORKDIR /realtime-server

ADD . /realtime-server
RUN chmod +x /realtime-server/*.jar
RUN chmod +x /realtime-server/*.sh

RUN useradd --uid 1000 appl
RUN chown -R appl /realtime-server

USER appl

ENV scriptsPath=/realtime-server/scripts/
ENV SYNC_RELEASE_CONFIGURATION=release

CMD ["java", "-jar", "server.jar"]
