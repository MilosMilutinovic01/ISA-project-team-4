from confluent_kafka import Producer
import sys
import json
import time

def main():

    forUser = sys.argv[1]
    refreshRate = sys.argv[2]

    json_data = sys.stdin.readline()

    data = json.loads(json_data)

    kafka_config = {
        'bootstrap.servers': 'localhost:9092',
    }

    producer = Producer(kafka_config)

    topic = 'simulator'

    for ind,tmp in enumerate(data):
            tmp["user"] = forUser
            producer.produce(topic, key=None, value=json.dumps(tmp))
            producer.flush()
            time.sleep(float(refreshRate))

    sys.exit(0)


if __name__ == "__main__":
    main()