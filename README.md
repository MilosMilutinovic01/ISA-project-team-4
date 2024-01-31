# ISA Project Team 4 - Medical Equipment Management

## Project Overview
This project is designed for managing medical equipment, utilizing IntelliJ IDE for the backend and Visual Studio Code for the frontend.

## Frontend Setup (Visual Studio Code):
1. **Clone this repository.**
2. **Open the frontend in Visual Studio Code.**
3. Run the following command in the terminal to install necessary dependencies and packages:
    ```bash
    npm i
    ```
4. Start the frontend app with the following command in the terminal:
    ```bash
    ng serve -o
    ```

## Backend Setup (IntelliJ IDE):
1. The backend is configured to run automatically, except for Kafka.
2. **Install and set up Kafka on your computer.**
3. Download Kafka from [https://downloads.apache.org/kafka/3.6.1/kafka_2.12-3.6.1.tgz](https://downloads.apache.org/kafka/3.6.1/kafka_2.12-3.6.1.tgz).
4. Create an empty folder on your C disk named `kafka`, and inside, create two empty folders called `kafka-logs` and `zookeeper-data`.
5. In `kafka/config/zookeeper.properties`, edit `dataDir` to point to the `zookeeper-data` folder. For example:
   ```properties
   dataDir=C:/kafka/zookeeper-data
6. In kafka/config/server.properties, edit log.dirs to point to the kafka-logs folder. For example:
   ```properties
   log.dirs=C:/kafka/kafka-logs
7. Run Kafka with start_kafka.bat.
8. In PowerShell, create three topics: simulator, contract, contractNotif.
9. After setting up Kafka, you can run the backend. The insert script will automatically run for you.
Enjoy your journey with this medical equipment management project! Feel free to contribute or use it for your specific medical purposes.
