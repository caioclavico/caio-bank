## 1 - Construindo as Imagens

    ```
    docker build -t caiobank/front -f front/Dockerfile .
    ```

### 2 - Front

    ```
    docker run --rm -it --name front-cljs -p 8280:8280 -p 8290:8290 -p 8777:8777 -p 9630:9630 -v $(pwd):/work caiobank/front bash
    ```
