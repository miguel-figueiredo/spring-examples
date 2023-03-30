import http from 'k6/http';
import { randomIntBetween } from 'https://jslib.k6.io/k6-utils/1.2.0/index.js';

export const options = {
    duration: '30s',
    vus: 50
};
export default function () {
    const url = 'http://localhost:8080/fishes/' + randomIntBetween(1, 10);
    http.get(url);
}