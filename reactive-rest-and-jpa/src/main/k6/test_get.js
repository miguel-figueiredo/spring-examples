import http from 'k6/http';
import { randomString } from 'https://jslib.k6.io/k6-utils/1.2.0/index.js';

export const options = {
    vus: 5,
    duration: '30s',
};
export default function () {
    const url = 'http://localhost:8080/fishes/1';
    http.get(url);
}