var app = require('express')();
var server = require('http').Server(app);
var io = require('socket.io')(server);

var dir = __dirname + '/app';



server.listen(80);
// WARNING: app.listen(80) will NOT work here!

app.get('/', function (req, res) {
  res.sendFile(dir + '/index.html');
});

/*
io.on('connection', function (socket) {
  socket.emit('news', { hello: 'world' });
  socket.on('my other event', function (data) {
    console.log(data);
  });
});
*/


io.on('connection', function(socket){
  console.log('a user connected');
  console.log(socket.conn.transport.name);
});
