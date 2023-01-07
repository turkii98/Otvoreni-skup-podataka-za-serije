//imports
const express = require("express")
const fs = require('fs')
const app = express()
var LocalStorage = require('node-localstorage').LocalStorage;
   localStorage = new LocalStorage('./scratch');

var requestt = require("request");

//localStorage.setItem("request", requestt)

        var options = { method: 'POST',
        url: 'https://dev-r26m6dyuuwt3qi1d.us.auth0.com/oauth/token',
        headers: { 'content-type': 'application/json' },
        body: '{"client_id":"UPMRAUXyMCqlpCNQhhhcuPJ81K62DFRm","client_secret":"hf8rgvNi1Aqv1PAVUfyJINi0Hzhp66-8DWHjY13Rx60qCtVTYrfY2DNHer6pgdV3","audience":"https://orlab","grant_type":"client_credentials"}' };

        requestt(options, function (error, response, body) {
        if (error) throw new Error(error);
        console.log(body);
        localStorage.setItem("token", body)
        });
        

const { auth } = require('express-openid-connect');

const config = {
  authRequired: false,
  auth0Logout: true,
  secret: 'a long, randomly-generated string stored in env',
  baseURL: 'http://localhost:3000',
  clientID: 'UPMRAUXyMCqlpCNQhhhcuPJ81K62DFRm',
  issuerBaseURL: 'https://dev-r26m6dyuuwt3qi1d.us.auth0.com'
};

// auth router attaches /login, /logout, and /callback routes to the baseURL
app.use(auth(config));

// req.isAuthenticated is provided from the auth router
app.get('/', (req, res) => {
  res.send(req.oidc.isAuthenticated() ? res.render('loggedin', {checked: true}) : res.render('index', {checked: true})  );
});

app.get('/main', (req, res) => {
    res.send(req.oidc.isAuthenticated() ? res.render('loggedin', {checked: true}) : res.render('index', {checked: true})  );
  });

  
app.get('/logout', (req, res) => {
    res.send(req.oidc.isAuthenticated() ? "logged in" : "logged out" );
  });
//asdasdas
app.use(function (req, res, next) {
    res.locals.user = req.oidc.user;
    next();
  });


const { requiresAuth } = require('express-openid-connect');

app.get('/profile', requiresAuth(), (req, res, next) => {
  //res.send(JSON.stringify(req.oidc.user));
  console.log(req.oidc.refreshToken)
  res.render('profile', {
    userProfile: JSON.stringify(req.oidc.user, null, 2),
    title: 'Profile page',
    a: req.oidc.idToken,
    b: req.oidc.accessToken
  });
});



//asdadas




const port = 3000

var $       = require( 'jquery' );
const { request } = require("http");


//static files
app.use(express.static('public'))

app.use('/css', express.static(__dirname + 'public/css'))



app.set('views', './')
app.set('view engine', 'ejs')

//app.get('', (req, res) => {
  //  res.render('index', {checked: true})
//})

//listen on port
app.listen(port, () => console.info(`Listening on port ${port}`))