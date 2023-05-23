FROM clojure:openjdk-11-tools-deps-1.11.1.1149 AS cljs

WORKDIR /work

COPY . .

RUN apt-get update && apt-get install -y curl

RUN apt install leiningen -y

RUN curl -SL https://deb.nodesource.com/setup_20.x | bash - \
    && apt-get install -y nodejs

RUN echo "Node: " && node -v

RUN echo "NPM: " && npm -v

CMD ["/bin/bash"]
