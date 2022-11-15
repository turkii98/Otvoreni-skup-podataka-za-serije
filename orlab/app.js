//imports
const express = require("express")
const fs = require('fs')
const app = express()
const port = 3000

var $       = require( 'jquery' );


//static files
app.use(express.static('public'))

app.use('/css', express.static(__dirname + 'public/css'))



app.set('views', './')
app.set('view engine', 'ejs')

app.get('', (req, res) => {
    res.render('index', {checked: true})
})

//listen on port
app.listen(port, () => console.info(`Listening on port ${port}`))