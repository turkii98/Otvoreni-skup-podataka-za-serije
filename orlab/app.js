//imports
const express = require("express")
const fs = require('fs')
const app = express()



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
  res.render('profile', {
    userProfile: JSON.stringify(req.oidc.user, null, 2),
    title: 'Profile page'
  });
});



//asdadas




const port = 3000

var $       = require( 'jquery' );


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