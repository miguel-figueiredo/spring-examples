import http from 'k6/http';

const url = 'http://localhost:8080/person';

export const options = {
    duration: '30s',
    vus: 50
};

export default function () {
    const response = http.post(url);
    http.put(url + '/' + response.body)
}