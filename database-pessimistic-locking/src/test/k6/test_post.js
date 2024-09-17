import http from 'k6/http';

export const options = {
    duration: '30s',
    vus: 50
};
export default function () {
    const url = 'http://localhost:8080/person';
    http.post(url);
}