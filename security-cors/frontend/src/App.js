import logo from './logo.svg';
import './App.css';

function hello() {
  return fetch('http://localhost:8080/hello', {
    method: 'GET',
    headers: {
        'Content-Type': 'text/plain',
        'pragma': 'no-cache',
        'cache-control': 'no-cache'
    },
    redirect: 'follow',
    credentials: 'include'
  });
}

function login() {
  const parameters = new URLSearchParams({
    username: 'user',
    password: 'pass',
  });

  return fetch("http://localhost:8080/login?" + parameters, {
    method: 'POST',
    headers: {
        'Content-Type': 'text/plain',
        'pragma': 'no-cache',
        'cache-control': 'no-cache'
    },
    redirect: 'follow',
    credentials: 'include'
  });
}

function App() {
  
  login().then(response => {
      hello().then(response => {response.text()
        .then(text => console.log(text))});
  });

  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        Hello world!
      </header>
    </div>
  );
}

export default App;
